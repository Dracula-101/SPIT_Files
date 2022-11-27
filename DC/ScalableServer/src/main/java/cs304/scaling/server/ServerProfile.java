package cs304.scaling.server;

import cs304.scaling.utils.AppConstants;

import java.util.Timer;
import java.util.TimerTask;


public class ServerProfile extends TimerTask {
    private Timer timer;
    private Server server;

    ServerProfile(Server server) {
        this.server = server;
        this.timer = new Timer("ServerStatsDisplayer");
    }

    @Override
    public void run() {
        server.printStats(); //Invoke print statistics method in Server class
    }

    void startExecution() {
        timer.scheduleAtFixedRate(this, AppConstants.STATS_LOGGER_START_DELAY_MILLIS, AppConstants.STATS_LOGGER_INTERVAL_MILLIS);
    }
}
