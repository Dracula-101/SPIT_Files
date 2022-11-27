package cs304.scaling.client;

import cs304.scaling.utils.AppConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


public class Client {
    private Selector selector;
    private volatile AtomicInteger messageSentCount;
    private volatile AtomicInteger messageReceivedCount;
    private SocketChannel clientSocket;
    private LinkedList<String> hashList;

    private Client(String hostname, int port, int messagingRate) {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageSentCount = new AtomicInteger(0);
        messageReceivedCount = new AtomicInteger(0);
        hashList = new LinkedList<>();

        new ClientProfile(this).startExecution(); //Start the Timer Task that prints Client statistics

        try {
            clientSocket = SocketChannel.open(new InetSocketAddress(hostname, port)); //Open socket channel with server
            clientSocket.configureBlocking(false); //Configure blocking to false, thus making I/O non-blocking
            clientSocket.register(selector, SelectionKey.OP_READ);
            System.out.println("Client connected to server at " + hostname + ":" + port);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread t = new Thread(new AsyncMessage(this, messagingRate, clientSocket));
        t.start(); //Start thread for sending message to server at specified regular intervals

        try {
            blockAndReadMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void blockAndReadMessages() throws IOException {
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys(); //Some channel has activity, get the keys
            Iterator<SelectionKey> it = selectedKeys.iterator(); //Get an iterator over the keys to make life easier

            while (it.hasNext()) {
                SelectionKey selectionKey = it.next();
                it.remove(); //Remove key immediately to avoid processing this key again.

                if (selectionKey.isReadable()) {
                    ByteBuffer readBuffer = ByteBuffer.allocate(AppConstants.BYTES_PER_HASH); //Allocate 40 bytes to ByteBuffer to read hash
                    int numBytesRead = 0;
                    //SocketChannel channel = (SocketChannel) selectionKey.channel();
                    while (readBuffer.hasRemaining() && numBytesRead != -1) {
                        numBytesRead = clientSocket.read(readBuffer); //Read data(hashcode) from socket channel
                    }

                    messageReceivedCount.incrementAndGet(); //Atomically increment the receive message count

                    byte[] hashBytes = readBuffer.array();
                    String hash = new String(hashBytes);
                    synchronized (hashList) {
                        String foundHash = containsHash(hash); //Indicator for the presence of hash in HashList
                        if (foundHash != null) {
                            if (AppConstants.DEBUG) {
                                System.out.println("Hashes Matched. Removing hash " + hash + " from linked list.");
                            }
                            hashList.remove(foundHash);
                        } else {
                            if (AppConstants.DEBUG) {
                                System.out.println(hash + " not found in hashlist");
                            }
                        }
                    }
                }

            }
        }
    }

    private String containsHash(String hash) {
        for (String h : hashList) {
            if (h.equals(hash)) {
                return h;
            }
        }
        return null;
    }


    void incrementSentCount() {
        messageSentCount.incrementAndGet();
    }


    synchronized void printStats() {
        System.out.println("------------------------------------------------------------------\n");
        System.out.println("(" + System.currentTimeMillis() + ")");
        System.out.println("Total Sent Count:\t\t" + messageSentCount + "\n"
                + "Total Received Count:\t\t" + messageReceivedCount + "\n");
        messageSentCount.set(0);
        messageReceivedCount.set(0);
    }

    synchronized void updateHashes(String hashOfPayload) {
        if(AppConstants.DEBUG) {
            System.out.println(hashOfPayload + " to hashlist");
        }
        hashList.add(hashOfPayload);
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please provide 3 arguments.\nUsage: "
                    + "java cs304.scaling.client.Client <server-host> <server-port> <message-rate>\n"
                    + "Exiting");
            System.exit(1);
        }
        new Client(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    }
}
