package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobjects.EvOttawaRecord;
import transferobjects.QueryResult;

/**
 * JDBC implementation of EvReportDao.
 */
public class EvReportDaoImpl implements EvReportDao {

    private static final String SQL_ALL = """
            SELECT o.Fsa, o.City, e.Bev, e.Phev, e.TotalEv
            FROM ottawapostalcodes o
            INNER JOIN evcounts e ON o.Fsa = e.Fsa
            ORDER BY o.Fsa
            """;

    private static final String SQL_BY_FSA = """
            SELECT o.Fsa, o.City, e.Bev, e.Phev, e.TotalEv
            FROM ottawapostalcodes o
            INNER JOIN evcounts e ON o.Fsa = e.Fsa
            WHERE o.Fsa = ?
            ORDER BY o.Fsa
            """;

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryResult<EvOttawaRecord> getAllByFsa() throws DaoException {
        return executeQuery(SQL_ALL, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryResult<EvOttawaRecord> getByFsa(String fsa) throws DaoException {
        return executeQuery(SQL_BY_FSA, fsa);
    }

    private QueryResult<EvOttawaRecord> executeQuery(String sql, String fsa) throws DaoException {
        List<EvOttawaRecord> rows = new ArrayList<>();
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
                    EvOttawaRecord row = new EvOttawaRecord();
                    row.setFsa(resultSet.getString("Fsa"));
                    row.setCity(resultSet.getString("City"));
                    row.setBev(resultSet.getInt("Bev"));
                    row.setPhev(resultSet.getInt("Phev"));
                    row.setTotalEv(resultSet.getInt("TotalEv"));
                    rows.add(row);
                }
            }
        } catch (SQLException ex) {
            throw new DaoException("Unable to execute EV join query.", ex);
        }

        return new QueryResult<>(columnLabels, rows);
    }
}