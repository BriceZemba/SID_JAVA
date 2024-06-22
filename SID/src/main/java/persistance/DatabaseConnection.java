package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Classe utilitaire pour gérer la connexion à la base de donnée
 */
public class DatabaseConnection {

    /*
     * Paramètres de connexion à la base de donnée
     */
    private static String host = "localhost";
    private static String sid = "XE";
    private static String user = "mediatek";
    private static String password = "root";

    /*
     * Obtiens une instance de classe Connection à la base de donnée
     */
    public static Connection getConnection() {
        try {
            // Pour Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String connectionURL = "jdbc:oracle:thin:@" + host + ":1521:" + sid;
            return DriverManager.getConnection(connectionURL, user, password);
        } catch (Exception ignored) {}
        return null;
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (Exception ignored) {}
    }
}
