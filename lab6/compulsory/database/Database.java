package compulsory.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{
    private static final String URL = "jdbc:postgresql://localhost:5432/lab6";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Zxcv12345@&";

    private static Connection connection = null;

    private Database() {
    }

    public static Connection getConnection(){
        if (connection == null){
            createConnection();
        }
        return connection;
    }

    private static void createConnection(){
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        }
        catch (SQLException e){
            System.err.println("eroare la conectare la baza de date: " + e.getMessage());
        }
    }

    public static void commit(){
        try{
            if (connection != null){
                connection.commit();
            }
        }
        catch (SQLException e){
            System.err.println("eroare la commit: " + e.getMessage());
        }
    }

    //anuleaza modificarile nesalvate
    public static void rollback(){
        try {
            if (connection != null){
                connection.rollback();
            }
        }
        catch (SQLException e){
            System.err.println("eroare la rollback: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Eroare la inchiderea conexiunii: " + e.getMessage());
        }
    }
}
