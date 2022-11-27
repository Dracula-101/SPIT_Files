package cs304.scaling.utils;

// * Class encapsulating implementation-level constants
public class AppConstants {
    public static final boolean DEBUG = false; // Set DEBUG mode

    public static final int PER_SECOND = 1000; // This is divided by MessageRate to set up an interval between
                                               // successive client messages

    public static final int BYTES_PER_MESSAGE = 8192; // Randomly generated Payload's Size

    public static final int STATS_LOGGER_START_DELAY_MILLIS = 20000; // Start Delay for printing statistics

    public static final int STATS_LOGGER_INTERVAL_MILLIS = 20000; // Interval Duration for printing statistics

    public static final int STATS_LOGGER_INTERVAL_SECS = STATS_LOGGER_INTERVAL_MILLIS / 1000; // Interval Duration in
                                                                                              // seconds

    public static final int BYTES_PER_HASH = 40; // Size of payload from Server to Client (Hash Length)
}
