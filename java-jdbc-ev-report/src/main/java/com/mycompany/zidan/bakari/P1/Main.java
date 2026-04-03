package com.mycompany.zidan.bakari;
/*Zidan Bakari
*/
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/**
 * Main class  program Connects to MySQL, runs a join query, prints metadata,
 * prints rows, and totals BEV/PHEV/TotalEV in one pass.
 */
public class Main {
   /**
     * Program entry point.
     * @param args not used
     */
    public static void main(String[] args) {

        // Load database.properties from src/main/resources
        Properties props = new Properties();
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (in == null) {
                System.out.println("ERROR: database.properties not found in src/main/resources");
                return;
            }
            props.load(in);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String pass = props.getProperty("db.password");

        // SQL JOIN query
        String sql = """
            SELECT p.City, e.Fsa, e.Bev, e.Phev, e.TotalEv
            FROM ottawapostalcodes p
            JOIN evcounts e ON e.Fsa = p.Fsa
            ORDER BY e.Fsa
        """;

        long totalBev = 0;
        long totalPhev = 0;
        long totalEv = 0;

        // Connect + query using try-with-resources
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Connected to DB!");

            // Print ResultSetMetaData (column attributes)
            ResultSetMetaData md = rs.getMetaData();
            int colCount = md.getColumnCount();

            System.out.println();
            System.out.println("Table: evontario - Column Attributes:");
            for (int i = 1; i <= colCount; i++) {
                String colName = md.getColumnLabel(i);
                String sqlType = md.getColumnTypeName(i);     // MySQL type name
                String javaClass = md.getColumnClassName(i);  // corresponding Java class
                System.out.printf("%-10s %-15s %s%n", colName, sqlType, javaClass);
            }

            // Print table header (formatted)
            System.out.println();
            System.out.printf("%-55s %-5s %8s %8s %8s%n", "City", "Fsa", "Bev", "Phev", "TotalEv");
            System.out.println("---------------------------------------------------------------------------------------");

            // 6) Print rows + compute totals 
            while (rs.next()) {
                String city = rs.getString("City");
                String fsa = rs.getString("Fsa");
                int bev = rs.getInt("Bev");
                int phev = rs.getInt("Phev");
                int tot = rs.getInt("TotalEv");

                totalBev += bev;
                totalPhev += phev;
                totalEv += tot;

                System.out.printf("%-55s %-5s %8d %8d %8d%n", city, fsa, bev, phev, tot);
            }

            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("%-61s %8d %8d %8d%n", "Total", totalBev, totalPhev, totalEv);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
}
