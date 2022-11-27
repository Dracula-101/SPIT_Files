package cs304.scaling.server;

import cs304.scaling.functions.ConnectionAcceptor;
import cs304.scaling.utils.AppConstants;
import cs304.scaling.functions.ReadWriter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


public class Server {
    private Selector selector;
    private volatile AtomicInteger tasksServedByServer;
    private HashMap<SocketChannel, Integer> perClientStatsMap;
    private ServerSocketChannel serverSocketChannel;
    private ThreadPool threadPool;

    private Server(int port, int poolSize, int batchSize, double batchTime) throws IOException {
        tasksServedByServer = new AtomicInteger(0);
        perClientStatsMap = new HashMap<>();

        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false); //enable non-blocking I/O
        serverSocketChannel.socket().bind(new InetSocketAddress(port)); //bind serversocket to the designated port
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //register intent to accept connections
        System.out.println("Server listening on port " + port);

        new ServerProfile(this).startExecution();
        threadPool = new ThreadPool(batchSize, batchTime * 1000, poolSize); //Initialize the thread pool class with user-defined constraints
        threadPool.initiateThreads(); //Start each thread the thread pool and make them subscribe to the task queue
        startKeyWiseMultiplexing();
    }

    private void startKeyWiseMultiplexing() {
        while (true) {
            try {
                selector.selectNow();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys(); //Some channel has activity, get the keys
            Iterator<SelectionKey> it = selectedKeys.iterator(); //Get an iterator over the keys

            while (it.hasNext()) {
                SelectionKey selectionKey = it.next();
                it.remove();
                if (selectionKey.isAcceptable()) {
                    selectionKey.interestOps(selectionKey.interestOps() & ~SelectionKey.OP_ACCEPT);
                    ConnectionAcceptor task = new ConnectionAcceptor(this, selectionKey); //create an accept connections task
                    //threadPool.addTaskToBatch(task);
                    threadPool.notifyAndExecuteImmediate(task); // Add accept connections task to immediate list and notify available threads of this slightly prioritised task
                    if (AppConstants.DEBUG) {
                        System.out.println("Adding Accept Task to tasklist in Server");
                    }
                } else if (selectionKey.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                    tasksServedByServer.incrementAndGet(); //Increment server tasks served

                    if (AppConstants.DEBUG) {
                        System.out.println("Adding Read/Write Task to tasklist in Server");
                    }

                    synchronized (perClientStatsMap) {
                        int count = perClientStatsMap.get(clientChannel);
                        perClientStatsMap.put(clientChannel, ++count); //Increment message received count for particular client(indexed by their channel)
                    }

                    ReadWriter readWriteTask = new ReadWriter(selectionKey); //Create read-write task
                    selectionKey.interestOps(selectionKey.interestOps() & SelectionKey.OP_READ);
                    threadPool.addTaskToBatch(readWriteTask); //Add read-write task to task list to be batched
                }
            }
        }
    }

    public void acceptConnections(SelectionKey key) {
        try {
            if (AppConstants.DEBUG) {
                System.out.println("Adding new connection in Server");
            }
            SocketChannel clientChannel = serverSocketChannel.accept();
            clientChannel.configureBlocking(false); //enable non-blocking I/O on channel
            clientChannel.register(selector, SelectionKey.OP_READ);

            synchronized (perClientStatsMap) {
                perClientStatsMap.put(clientChannel, 0); //Add entry for this client in the client message count map.
            }

            key.interestOps(key.interestOps() | SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized void printStats() {
        synchronized (perClientStatsMap) {
            System.out.println("--------------------------------------------------------");
            System.out.println("\n(" + System.currentTimeMillis() + ")");
            System.out.println(
                    "Server Throughtput\t\t" + (tasksServedByServer.get()) / AppConstants.STATS_LOGGER_INTERVAL_SECS);
            System.out.println("Active Client Connections\t" + perClientStatsMap.size());
            System.out.println("Mean Per-Client Throughput\t"
                    + (meanPerClientThroughput()) / AppConstants.STATS_LOGGER_INTERVAL_SECS + " messages");
            System.out.println("Std-Dev of Per-Client \t\t" + (stdDevPerClientThroughput(
                    meanPerClientThroughput()) / AppConstants.STATS_LOGGER_INTERVAL_SECS) + " messages");
            tasksServedByServer.set(0);
        }
    }

    private double meanPerClientThroughput() {
        int sum = 0;
        for (SocketChannel channel : perClientStatsMap.keySet()) {
            sum += perClientStatsMap.get(channel);
        }
        return (double) sum / perClientStatsMap.size();
    }

    private double stdDevPerClientThroughput(double mean) {
        double sd = 0.0;

        for (SocketChannel channel : perClientStatsMap.keySet()) {
            sd += Math.pow(perClientStatsMap.get(channel) - mean, 2);
            perClientStatsMap.put(channel, 0);
        }

        return Math.sqrt(sd / perClientStatsMap.size()) / AppConstants.STATS_LOGGER_INTERVAL_SECS;
    }


    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Please provide 4 arguments.\nUsage: "
                    + "java cs304.scaling.server.Server <portNum> <thread-pool-size> <batch-size> <batch-time>\n"
                    + "Exiting");
            System.exit(1);
        }
        try {
            new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                    Double.parseDouble(args[3]));
        } catch (IOException e) {
            if (AppConstants.DEBUG) {
                System.out.println("Server Constructor threw error");
            }
            e.printStackTrace();
        }
    }
}
