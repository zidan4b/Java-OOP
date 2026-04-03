package businesslayer;

/**
 * Entry point.
 */
public class Main {

    /**
     * Starts the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        EvReportManager manager = new EvReportManager();
        manager.runRequiredReports();
    }
}