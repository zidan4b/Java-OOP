package dataaccesslayer;

import transferobjects.OttawaPostalCode;
import transferobjects.QueryResult;

/**
 * DAO interface for ottawapostalcodes table.
 */
public interface OttawaPostalCodeDao {

    /**
     * Returns all rows from ottawapostalcodes.
     *
     * @return query result containing all rows
     * @throws DaoException data access error
     */
    QueryResult<OttawaPostalCode> getAllByFsa() throws DaoException;

    /**
     * Returns a specific row from ottawapostalcodes by FSA.
     *
     * @param fsa FSA key
     * @return query result with matching rows
     * @throws DaoException data access error
     */
    QueryResult<OttawaPostalCode> getByFsa(String fsa) throws DaoException;
}