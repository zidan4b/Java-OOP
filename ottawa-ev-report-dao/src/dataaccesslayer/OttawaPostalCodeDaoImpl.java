package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobjects.OttawaPostalCode;
import transferobjects.QueryResult;

/**
 * JDBC implementation of OttawaPostalCodeDao.
 */
public class OttawaPostalCodeDaoImpl implements OttawaPostalCodeDao {

    private static final String SQL_ALL = """
            SELECT Fsa, City, Province, Latitude, Longitude
            FROM ottawapostalcodes
            ORDER BY Fsa
            """;

    private static final String SQL_BY_FSA = """
            SELECT Fsa, City, Province, Latitude, Longitude
            FROM ottawapostalcodes
            WHERE Fsa = ?
            ORDER BY Fsa
            """;

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryResult<OttawaPostalCode> getAllByFsa() throws DaoException {
        return executeQuery(SQL_ALL, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryResult<OttawaPostalCode> getByFsa(String fsa) throws DaoException {
        return executeQuery(SQL_BY_FSA, fsa);
    }

    private QueryResult<OttawaPostalCode> executeQuery(String sql, String fsa) throws DaoException {
        List<OttawaPostalCode> rows = new ArrayList<>();
        List<String> columnLabels = new ArrayList<>();

        try (Connection connection = DataSource.getInstance().createConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            if (fsa != null) {
                statement.setString(1, fsa);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                ResultSetMetaData meta = resultSet.getMetaData();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    columnLabels.add(meta.getColumnLabel(i));
                }

                while (resultSet.next()) {
                    OttawaPostalCode postalCode = new OttawaPostalCode();
                    postalCode.setFsa(resultSet.getString("Fsa"));
                    postalCode.setCity(resultSet.getString("City"));
                    postalCode.setProvince(resultSet.getString("Province"));
                    postalCode.setLatitude(resultSet.getDouble("Latitude"));
                    postalCode.setLongitude(resultSet.getDouble("Longitude"));
                    rows.add(postalCode);
                }
            }
        } catch (SQLException ex) {
            throw new DaoException("Unable to query ottawapostalcodes.", ex);
        }

        return new QueryResult<>(columnLabels, rows);
    }
}