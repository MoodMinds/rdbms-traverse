package org.moodminds.rdbms.traverse;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A {@link Connection} supplying interface.
 */
@FunctionalInterface
public interface ConnectionSource {

    /**
     * Return a {@link Connection}.
     *
     * @return a {@link Connection}
     * @throws SQLException an exception in case of database access error occurs
     */
    Connection getConnection() throws SQLException;
}
