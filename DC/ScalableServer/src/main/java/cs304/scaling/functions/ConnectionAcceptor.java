package cs304.scaling.functions;

import cs304.scaling.server.Server;

import java.nio.channels.SelectionKey;


public class ConnectionAcceptor implements TaskInterface {
    private final Server server;
    private final SelectionKey selectionKey;

    public ConnectionAcceptor(Server server, SelectionKey selectionKey) {
        this.server = server;
        this.selectionKey = selectionKey;
    }

    @Override
    public void onTask() {
        server.acceptConnections(selectionKey);
    }
}
