package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConnection() throws SQLException {
    	
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/biblioteca",
            "usuariobiblio",
            "1234"
        );
    }
    
}
