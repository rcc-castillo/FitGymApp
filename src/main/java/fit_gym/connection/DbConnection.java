package fit_gym.connection;

import java.sql.Connection;

public class DbConnection {
    public static Connection getConnection() {
        Connection connection = null;
        String dbName = System.getenv("DB_NAME");
        String url = "jdbc:postgresql://localhost:5432/" + dbName;
        String user = "postgres";
        String password = System.getenv("DB_PASSWORD");
        try {
            Class.forName("org.postgresql.Driver");
            connection = java.sql.DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }
}
