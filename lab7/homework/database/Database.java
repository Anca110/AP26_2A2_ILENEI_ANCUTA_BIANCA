package lab7.homework.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/lab6";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Zxcv12345@&";

    private static HikariDataSource dataSource;

    private Database() {
    }

    static {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        config.setAutoCommit(false);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("Eroare la obtinerea conexiunii din pool: " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    public static void commit(Connection connection) {
        try {
            if (connection != null) {
                connection.commit();
            }
        } catch (SQLException e) {
            System.err.println("Eroare la commit: " + e.getMessage());
        }
    }

    public static void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            System.err.println("Eroare la rollback: " + e.getMessage());
        }
    }
}