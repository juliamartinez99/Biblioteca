package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Principal {

	
	private static Scanner sc = new Scanner(System.in);
	public static ArrayList<Libro> libros = new ArrayList<>();
	
	
	public static void main(String[] args) {

		Ventana v = new Ventana();

		
	}
	
	private static void modificar(int id, String valor, int col) {
		
		String cambio = "UPDATE libros set ? = ? where id = ?";
		switch(col) {
		case 1:
			cambio = "UPDATE libros set titulo = ? where id = ?";
			break;
		case 2:
			cambio = "UPDATE libros set autor = ? where id = ?";
			break;
		case 3:
			cambio = "UPDATE libros set anio_publicacion = ? where id = ?";
			break;
		}
		
		
        try (Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(cambio)) {

        	//introducimos valor en la tabla o cambio, valor equivalente a '?'
               ps.setString(1, valor);
               ps.setInt(2, id);
               
            //ejecutamos cambio
               ps.executeUpdate();


           } catch (Exception e) {
               e.printStackTrace();
           }
      		
	}
	
	
//	private static void eliminarLibro() {
//		
//		int id = 0;
//		mostrarLibros();
//		System.out.println("Introduce ID a eliminar");
//		id = sc.nextInt();
//		
//		try {
//			
//			Connection con = Conexion.getConnection();
//			PreparedStatement ps = con.prepareStatement("delete from libros where id = ?");
//			
//			ps.setInt(1, id);
//			ps.executeUpdate();
//			
//		}catch(Exception e) {
//			
//			System.out.println("error al eliminar id");
//			
//		}
//		
//	}


//	private static void prestarLibro() {
//		
//		int id = 0;
//		
//		System.out.println("Introduce ID de libro a prestar");
//		id = sc.nextInt();
//		
//		String cambio = "UPDATE libros set disponible = 0 where id = ?";
//		
//        try (Connection con = Conexion.getConnection();
//                PreparedStatement ps = con.prepareStatement(cambio)) {
//
//        	//introducimos valor en la tabla o cambio, valor equivalente a '?'
//               ps.setInt(1, id);
//               
//            //ejecutamos cambio
//               ps.executeUpdate();
//
//
//           } catch (Exception e) {
//               e.printStackTrace();
//           }
//		
//	}
//
//	private static void pedirDatosLibro(){
//		
//		String titulo = "";
//		String autor = "";
//		String aniopu = "";
//
//		System.out.println("titulo?");
//		titulo = sc.nextLine();
//		
//		System.out.println("autor?");
//		autor = sc.nextLine();
//		
//		System.out.println("Año Publicacion?");
//		aniopu = sc.next();
//		
//		anhadirLibros(titulo,autor,aniopu);
//		
//	}
//	
//	
//	
//	public static void anhadirLibros(String t, String a, String an) {
//		
//        String sql = "INSERT INTO libros (titulo,autor,anio_publicacion,disponible,falta) VALUES (?,?, ?,?, now())";
//
//        try (Connection con = Conexion.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, t);
//            ps.setString(2, a);
//            ps.setString(3, an);
//            ps.setString(4, "1");
//
//            int filas = ps.executeUpdate();
//
//            System.out.println("Filas insertadas: " + filas);
//            System.out.println("Insercion correcta");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		
//	}
//
//
//	public static void mostrarLibros() {
//		
//		 String sql = "SELECT id, titulo, autor, anio_publicacion, disponible, falta FROM libros";
//		 
//		 libros.clear();
//
//	        try (Connection con = Conexion.getConnection();
//	             PreparedStatement ps = con.prepareStatement(sql);
//	             ResultSet rs = ps.executeQuery()) {
//	        	
//	        	if(!rs.next()) {
//	        		
//	        		System.out.println("No hay elementos en la tabla");
//	        	}else {
//	        		
//	        		System.out.println("ID\tTITULO\tAUTOR\tAÑO\tDISPONIBLE\tFECHA ALTA");
//	        		
//		            do {
//		            	
//		                int id = rs.getInt("id");
//		                String titulo = rs.getString("titulo");
//		                String autor = rs.getString("autor");
//		                String year = rs.getString("anio_publicacion");
//		                String disponible = rs.getString("disponible");
//		                String alta = rs.getString("falta");
//		                System.out.println(id + "\t" + titulo + "\t" + autor + "\t" + year + "\t" + disponible + "\t" + alta);
//		                libros.add(new Libro(String.valueOf(id),titulo,autor,year,disponible,alta));
//		           
//		            }while (rs.next());
//	        	}
//
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        
//	}
//	
//	public static String[][] traducir(){
//		
//		String [][] t = new String[libros.size()][6];
//		int i = 0;
//		int j = 0;
//		
//		for (Libro libro : libros) {
//			
//			t[i][j++] = libro.getId();
//			t[i][j++] = libro.getTitulo();
//			t[i][j++] = libro.getAutor();
//			t[i][j++] = libro.getAnio();
//			t[i][j++] = libro.getDisponible();
//			t[i++][j++] = libro.getFalta();
//			j = 0;
//		}
//		
//		return t;
//		
//	}
	

	


	private static int menu() {
		
		int opcion = 0;
		
		//comprobamos rango
		do {
			
			try {
				//mostramos menu
				System.out.println("1. Mostrar Libros");
				System.out.println("2. Añadir Libro");
				System.out.println("3. Marcar Libro como prestado");
				System.out.println("4. Eliminar Libro");
				System.out.println("5. Salir");
				System.out.print("Introduce un numero: ");
				opcion = sc.nextInt();
				//Informamos al usuario de error
				if(opcion < 1 || opcion > 5) {
					
					System.out.println("Valor fuera de rango, vuelve a introducir un numero...");
					
				}
				
			}catch(InputMismatchException e) {
				//informamos de valor incorrecto
				System.out.println("Valor introducido no valido");
				sc.next(); //limpiamos buffer
			}
			
		}while(opcion < 1 || opcion > 5);
		
		return opcion;
	}
	
}
