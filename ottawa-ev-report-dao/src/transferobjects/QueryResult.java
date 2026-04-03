package transferobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generic container that holds ResultSet column labels and mapped rows.
 *
 * @param <T> DTO row type
 */
public class QueryResult<T> {

    private final List<String> columnLabels;
    private final List<T> rows;

    /**
     * Constructs a query result object.
     *
     * @param columnLabels column labels from ResultSetMetaData
     * @param rows mapped rows
     */
    public QueryResult(List<String> columnLabels, List<T> rows) {
        this.columnLabels = new ArrayList<>(columnLabels);
        this.rows = new ArrayList<>(rows);
    }

    /**
     * Returns immutable column labels.
     *
     * @return column labels
     */
    public List<String> getColumnLabels() {
        return Collections.unmodifiableList(columnLabels);
    }

    /**
     * Returns immutable rows.
     *
     * @return mapped rows
     */
    public List<T> getRows() {
        return Collections.unmodifiableList(rows);
    }

    /**
     * Indicates whether there are no rows.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return rows.isEmpty();
    }
}