package cs304.scaling.client;

import cs304.scaling.utils.AppConstants;

import java.util.Timer;
import java.util.TimerTask;


public class ClientProfile extends TimerTask {
    private Timer timer;
    private Client client;

    ClientProfile(Client client) {
        this.client = client;
        this.timer = new Timer("ClientStatsDisplayer");
    }

    @Override
    public void run() {
        client.printStats(); //Invoke print statistics method in Client class
    }

    void startExecution() {
        timer.scheduleAtFixedRate(this, AppConstants.STATS_LOGGER_START_DELAY_MILLIS, AppConstants.STATS_LOGGER_INTERVAL_MILLIS);
    }
}
