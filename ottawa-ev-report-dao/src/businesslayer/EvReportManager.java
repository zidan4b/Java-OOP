package businesslayer;

import dataaccesslayer.DaoException;
import dataaccesslayer.EvReportDao;
import dataaccesslayer.EvReportDaoImpl;
import dataaccesslayer.OttawaPostalCodeDao;
import dataaccesslayer.OttawaPostalCodeDaoImpl;
import java.util.List;
import transferobjects.EvOttawaRecord;
import transferobjects.OttawaPostalCode;
import transferobjects.QueryResult;

/**
 * Business layer for report generation and output formatting.
 */
public class EvReportManager {

    private static final String ALGONQUIN_FSA = "K2G";

    private final EvReportDao evReportDao;
    private final OttawaPostalCodeDao postalCodeDao;

    /**
     * Creates manager with default DAO implementations.
     */
    public EvReportManager() {
        this.evReportDao = new EvReportDaoImpl();
        this.postalCodeDao = new OttawaPostalCodeDaoImpl();
    }

    /**
     * Runs both required reports and prints output.
     */
    public void runRequiredReports() {
        try {
            printEvCountsForOttawaFsas();
            System.out.println();
            printAlgonquinFsa();
        } catch (DaoException ex) {
            System.err.println("Error: " + ex.getMessage());
            if (ex.getCause() != null) {
                System.err.println("Cause: " + ex.getCause().getMessage());
            }
        }
    }

    /**
     * Prints joined EV counts report and totals 
     *
     * @throws DaoException if DAO fails
     */
    public void printEvCountsForOttawaFsas() throws DaoException {
        QueryResult<EvOttawaRecord> result = evReportDao.getAllByFsa();

        System.out.println("EV Counts for Ontario:");

        // Column labels are retrieved from ResultSetMetaData in DAO.
        List<String> labels = result.getColumnLabels();
        if (labels.size() >= 5) {
            System.out.printf("%-4s %-62s %6s %6s %8s%n",
                    labels.get(0), labels.get(1), labels.get(2), labels.get(3), labels.get(4));
        } else {
            // Fallback header if metadata is unexpectedly missing.
            System.out.printf("%-4s %-62s %6s %6s %8s%n", "FSA", "City", "BEV", "PHEV", "TotalEV");
        }

        if (result.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }

        int totalBev = 0;
        int totalPhev = 0;
        int grandTotalEv = 0;

        // One pass through the retrieved rows to print and calculate totals.
        for (EvOttawaRecord row : result.getRows()) {
            System.out.printf("%-4s %-62s %6d %6d %8d%n",
                    row.getFsa(), row.getCity(), row.getBev(), row.getPhev(), row.getTotalEv());
            totalBev += row.getBev();
            totalPhev += row.getPhev();
            grandTotalEv += row.getTotalEv();
        }

        System.out.println();
        System.out.printf("%-67s %6d %6d %8d%n", "Total", totalBev, totalPhev, grandTotalEv);
    }

    /**
     * Prints the ottawapostalcodes row for Algonquin's FSA (K2G).
     *
     * @throws DaoException if DAO fails
     */
    public void printAlgonquinFsa() throws DaoException {
        QueryResult<OttawaPostalCode> result = postalCodeDao.getByFsa(ALGONQUIN_FSA);

        System.out.println("FSA for Algonquin College:");

        List<String> labels = result.getColumnLabels();
        if (labels.size() >= 5) {
            System.out.printf("%-4s %-62s %-10s %-10s %-10s%n",
                    labels.get(0), labels.get(1), labels.get(2), labels.get(3), labels.get(4));
        } else {
            System.out.printf("%-4s %-62s %-10s %-10s %-10s%n",
                    "FSA", "City", "Province", "Latitude", "Longitude");
        }

        if (result.isEmpty()) {
            System.out.printf("No matches found for FSA %s.%n", ALGONQUIN_FSA);
            return;
        }

        for (OttawaPostalCode row : result.getRows()) {
            System.out.printf("%-4s %-62s %-10s %-10.4f %-10.4f%n",
                    row.getFsa(), row.getCity(), row.getProvince(), row.getLatitude(), row.getLongitude());
        }
    }
}