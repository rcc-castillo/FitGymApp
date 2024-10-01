package fit_gym.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public static Connection getConnection() {
        String url = System.getenv("DB_URL") + System.getenv("DB_NAME");
        String user = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}
