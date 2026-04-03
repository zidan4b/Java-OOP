package dataaccesslayer;

/**
 * Checked exception used by DAO classes.
 */
public class DaoException extends Exception {

    /**
     * Constructs a DaoException with message.
     *
     * @param message message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Constructs a DaoException with message and cause.
     *
     * @param message message
     * @param cause original cause
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}