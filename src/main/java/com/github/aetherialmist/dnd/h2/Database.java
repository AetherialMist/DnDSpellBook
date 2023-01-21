package com.github.aetherialmist.dnd.h2;

import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcConnectionPool;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class Database implements Closeable {

    private static final String URL = "jdbc:h2:file:./data/test";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private final JdbcConnectionPool pool;

    public Database() {
        pool = JdbcConnectionPool.create(URL, USERNAME, PASSWORD);
    }

    public void executeSingleStatement(String rawStatement) {
        Statement statement = null;
        try (Connection conn = pool.getConnection()) {
            statement = conn.createStatement();
            statement.execute(rawStatement);
        } catch (SQLException e) {
            log.error("Failed to execute command.", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.error("Error closing statement.", e);
                }
            }
        }
    }

    @Override
    public void close() {
        shutdownDatabase();
        closeConnectionPool();
    }

    private void shutdownDatabase() {
        log.info("Issuing Shutdown command to H2 database.");
        Statement statement = null;
        try (Connection conn = pool.getConnection()) {
            statement = conn.createStatement();
            statement.execute("SHUTDOWN");
        } catch (SQLException e) {
            log.error("Error shutting down H2 database.", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.error("Error closing Statement.", e);
                }
            }
        }
    }

    private void closeConnectionPool() {
        log.info("Disposing Connection Pool.");
        pool.dispose();
    }

}
