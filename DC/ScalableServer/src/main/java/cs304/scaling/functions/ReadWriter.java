package cs304.scaling.functions;

import cs304.scaling.utils.AppConstants;
import cs304.scaling.utils.SHA1Hasher;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;


public class ReadWriter implements TaskInterface {
    private SelectionKey selectionKey;

    public ReadWriter(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }

    @Override
    public void onTask() {
        SocketChannel channel = (SocketChannel) selectionKey.channel(); //Extract channel from selection key
        ByteBuffer readBuffer = ByteBuffer.allocate(AppConstants.BYTES_PER_MESSAGE);

        try {
            int numBytesRead = 0;
            while (readBuffer.hasRemaining() && numBytesRead != -1) {
                numBytesRead = channel.read(readBuffer); //Read bytes into a byte buffer
            }
            selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_READ);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String hashOfPayload = SHA1Hasher.SHA1FromBytes(readBuffer.array());
        hashOfPayload = String.format("%40s", hashOfPayload).replace(" ", "-"); //Pad hash to make it length 40. Essential for synchronizing sizes of messages
        byte[] hashBytes = hashOfPayload.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(hashBytes); //Wrap computed hash into a byte buffer
        while (byteBuffer.hasRemaining()) {
            try {
                if (AppConstants.DEBUG) {
                    System.out.println("Writing hash to Client " + hashOfPayload);
                }
                channel.write(byteBuffer); //Write hash to the client that sent the message
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
