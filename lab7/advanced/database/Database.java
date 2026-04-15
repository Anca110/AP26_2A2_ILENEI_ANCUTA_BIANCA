package lab7.advanced.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


//conection pool HikariCP
//configureaza pool ul de conexiuni, ofera conexiuni cand ai nevoie, commit, rollback, inchide conexiunea -pool ul la final;

//inlocuieste DriverManager.getConnection(...) cu dataSource.getConnection()
public class Database {

    //datele pt conectare la PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/lab6";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Zxcv12345@&";

    private static HikariDataSource dataSource;
    //pool ul- un obiect acre creaza conexiuni, le pastreaza, le reutilizeaza, le da cand ceri una, le primeste inapoi cand faci close

    private Database() {
    }

    //bloc static pt ca se executa o singura data cand calasa Database e incarcata prima data in memorie; inainte sa folosesti prima data Database.getConnection();
    static {
        HikariConfig config = new HikariConfig();//creez un obiect de configurare
        //aici spun cum sa se conecteze obiectul la baza de date
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);

        config.setMaximumPoolSize(10);//pool ul poate avea maxim 10 conexiuni active(in acelasi timp)
        config.setMinimumIdle(2);//incearca sa pastreze macar 2 conexiuni gata de folosit chiar daca momentan nu le foloseste nimeni
        config.setIdleTimeout(30000);//daca o conexiune sta degeaba prea mult, poate fi eliminata din pool dupa 30000ms
        config.setConnectionTimeout(30000);//cand ceri o conexiune si nu e disponibila imediat, se asteapta max 30000 secunde
        config.setAutoCommit(false);//cand fac o comanda nu se salveaza automat pana nu fac commit; pot sa fac rollback daca apare o eroare

        dataSource = new HikariDataSource(config);//aici chiar creez pool ul folosind configuratia aia
    }

    //cer conexiune
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

    //se anuleaza modificarile facute de la ultimul commit
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