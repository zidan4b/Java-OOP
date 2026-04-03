package dataaccesslayer;

import transferobjects.EvOttawaRecord;
import transferobjects.QueryResult;

/**
 * DAO interface for joined EV/Ottawa report queries.
 */
public interface EvReportDao {

    /**
     * Returns all rows from the join of ottawapostalcodes and evcounts.
     *
     * @return query result with metadata and rows
     * @throws DaoException data access error
     */
    QueryResult<EvOttawaRecord> getAllByFsa() throws DaoException;

    /**
     * Returns a single row for a specific FSA from the same join query.
     *
     * @param fsa FSA key
     * @return query result with metadata and rows
     * @throws DaoException data access error
     */
    QueryResult<EvOttawaRecord> getByFsa(String fsa) throws DaoException;
}