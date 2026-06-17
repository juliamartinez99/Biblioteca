package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Representa la conexion con la base de datos biblioteca
 * 
 * @author Julia Martinez
 * 
 * @version 1.0
 * @since 1.0
 * */
public class Conexion {

	/**
	 * Clase que devuelve la conexion con la base de datos
	 * 
	 * @throws SQLException
	 * @return Connection
	 */
    public static Connection getConnection() throws SQLException {
    	
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/biblioteca",
            "usuariobiblio",
            "1234"
        );
    }
    
}
