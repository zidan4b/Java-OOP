package dataaccesslayer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton data source responsible for creating JDBC connections.
 */
public final class DataSource {

    private static volatile DataSource instance;

    private final String connectionString;
    private final String userId;
    private final String password;

    private DataSource() {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new IllegalStateException("database.properties not found in src root/classpath.");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to load database.properties", ex);
        }

        // Supports either connectionString or url key.
        this.connectionString = firstNonBlank(
                properties.getProperty("connectionString"),
                properties.getProperty("url"));

        this.userId = firstNonBlank(
                properties.getProperty("userid"),
                properties.getProperty("user"),
                properties.getProperty("username"));

        this.password = properties.getProperty("password", "");

        if (connectionString == null || userId == null) {
            throw new IllegalStateException(
                    "database.properties must include connectionString (or url) and userid.");
        }
    }

    /**
     * Returns the single DataSource instance.
     *
     * @return singleton instance
     */
    public static DataSource getInstance() {
        if (instance == null) {
            synchronized (DataSource.class) {
                if (instance == null) {
                    instance = new DataSource();
                }
            }
        }
        return instance;
    }

    /**
     * Opens and returns a JDBC connection.
     *
     * @return database connection
     * @throws SQLException if connection cannot be opened
     */
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, userId, password);
    }

    private static String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
        }
        return null;
    }
}