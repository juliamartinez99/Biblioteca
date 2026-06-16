package biblioteca;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import biblioteca.Principal;

public class Menu extends JPanel implements ActionListener{
	
	private JButton mostrar;
	private JButton anhadir;
	private JButton mod;
	private JButton prestarLibro;
	private JButton devLibro;
	private JButton eliminarLibro;
	private JButton salir;
	private JLabel fondo;
	private JLabel errores;
	private Cursor pulsar = new Cursor(12);
	private MouseListener ml;
	private static String[] columnas = {"ID", "TITULO", "AUTOR","AÑO","DISPONIBLE","FECHA ALTA"};
	protected DefaultTableModel modelo = new DefaultTableModel(traducir(), columnas);
	private JTable tabla;
	private JScrollPane scroll;
	
	private JButton jBuscar;
	
	public static ArrayList<Libro> libros = new ArrayList<>();
	
	public Menu() {
		
		setLayout(null);
		int pos = 50;
		
		errores = new JLabel();
		errores.setBounds(460, 110, 500, 50);
		errores.setForeground(Color.red);
		errores.setFont(new Font("Arial",Font.BOLD,20));
		add(errores);
		
		anhadir = new JButton("AÑADIR LIBRO");
		anhadir.setBounds(pos, 60, 200, 50);
		estiloBotones(anhadir);
		add(anhadir);
		anhadir.addActionListener(this);
		
		mod = new JButton("GUARDAR CAMBIOS");
		mod.setBounds(949, 673, 200, 50);
		estiloBotones(mod);
		add(mod);
		mod.addActionListener(this);
		
		prestarLibro = new JButton("PRESTAR LIBRO");
		prestarLibro.setBounds(pos+=225, 60, 200, 50);
		estiloBotones(prestarLibro);
		add(prestarLibro);
		prestarLibro.addActionListener(this);
		
		devLibro = new JButton("DEVOLVER LIBRO");
		devLibro.setBounds(pos+=225, 60, 200, 50);
		estiloBotones(devLibro);
		add(devLibro);
		devLibro.addActionListener(this);
		
		eliminarLibro = new JButton("ELIMINAR LIBRO");
		eliminarLibro.setBounds(pos+=225, 60, 200, 50);
		estiloBotones(eliminarLibro);
		add(eliminarLibro);
		eliminarLibro.addActionListener(this);
		
		jBuscar = new JButton("BUSCAR LIBRO");
		jBuscar.setBounds(pos+=225, 60, 200, 50);
		estiloBotones(jBuscar);
		add(jBuscar);
		jBuscar.addActionListener(this);
		
		salir = new JButton("SALIR");
		salir.setBounds(58, 673, 200, 50);
		estiloBotones(salir);
		add(salir);
		salir.addActionListener(this);
		
		

		tabla = new JTable(modelo);
		tabla.setGridColor(Color.black);
		tabla.setOpaque(false);
		tabla.setBackground(new Color(255, 255, 255, 100));
		tabla.setRowHeight(30);
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabla.getTableHeader().setReorderingAllowed(false);
		
		scroll = new JScrollPane(tabla);
		scroll.setBounds(58, 162, 1091, 500);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		add(scroll);
		
		fondo = new JLabel(new ImageIcon("src/img/fondoBiblio.jpg"));
		fondo.setBounds(0, 0, 1200, 800);
		add(fondo);

	}
	

	private void estiloBotones(JButton b) {
		
		b.setCursor(pulsar);
		
		ml = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(anhadir)) {
			
			JFrame ev = (JFrame) SwingUtilities.getWindowAncestor(this);
			ev.remove(this);
			ev.getContentPane().add(new AnhadirLibro());
			ev.setVisible(true);
			
		}else if(e.getSource().equals(prestarLibro)) {
			
			if(Integer.parseInt(libros.get(tabla.getSelectedRow()).getDisponible()) == 0) {
				
				errores.setText("El libro no esta disponible");
				
			}else {
				
				errores.setText("");
			
				prestarLibro(Integer.parseInt(libros.get(tabla.getSelectedRow()).getId()));
				tabla.setModel(new DefaultTableModel(traducir(), columnas));
			}
			
		}else if(e.getSource().equals(eliminarLibro)) {
			
			eliminarLibro(Integer.parseInt(libros.get(tabla.getSelectedRow()).getId()));
			tabla.setModel(new DefaultTableModel(traducir(), columnas));
			
		}else if(e.getSource().equals(devLibro)) {
			
			if(Integer.parseInt(libros.get(tabla.getSelectedRow()).getDisponible()) == 1) {
				
				errores.setText("El libro ya esta marcado como disponible");
				
			}else {
				
				errores.setText("");
			
				disponibleLibro(Integer.parseInt(libros.get(tabla.getSelectedRow()).getId()));
				tabla.setModel(new DefaultTableModel(traducir(), columnas));
				
			}
		}else if(e.getSource().equals(salir)) {
			
			System.exit(ABORT);
			
		}else if(e.getSource().equals(mod)) {
			
			if(tabla.getSelectedColumn() != 1 && tabla.getSelectedColumn() != 2 && tabla.getSelectedColumn() != 3){
				
				errores.setText("No se puede modificar columna seleccionada");
				
			}else {
			
				errores.setText("");
				modificar(Integer.parseInt(libros.get(tabla.getSelectedRow()).getId()), String.valueOf(tabla.getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn())), tabla.getSelectedColumn());
				tabla.setModel(new DefaultTableModel(traducir(), columnas));
				
			}
		}else if(e.getSource().equals(jBuscar)) {
			
			JFrame ev = (JFrame) SwingUtilities.getWindowAncestor(this);
			ev.remove(this);
			ev.getContentPane().add(new BuscarLibro());
			ev.setVisible(true);
			
			
		}
		
	}
	
	
	private static void eliminarLibro(int id) {

		
		try {
			
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from libros where id = ?");
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		}catch(Exception e) {
			
			System.out.println("error al eliminar id");
			
		}
		
	}


	private static void prestarLibro(int id) {
		
		
		String cambio = "UPDATE libros set disponible = 0 where id = ?";
		
        try (Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(cambio)) {

        	//introducimos valor en la tabla o cambio, valor equivalente a '?'
               ps.setInt(1, id);
               
            //ejecutamos cambio
               ps.executeUpdate();


           } catch (Exception e) {
               e.printStackTrace();
           }
      		
	}
	
	private static void disponibleLibro(int id) {
		
		
		String cambio = "UPDATE libros set disponible = 1 where id = ?";
		
        try (Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(cambio)) {

        	//introducimos valor en la tabla o cambio, valor equivalente a '?'
               ps.setInt(1, id);
               
            //ejecutamos cambio
               ps.executeUpdate();


           } catch (Exception e) {
               e.printStackTrace();
           }
      		
	}

	
	public static void anhadirLibros(String t, String a, String an) {
		
        String sql = "INSERT INTO libros (titulo,autor,anio_publicacion,disponible,falta) VALUES (?,?, ?,?, now())";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t);
            ps.setString(2, a);
            ps.setString(3, an);
            ps.setString(4, "1");

            int filas = ps.executeUpdate();

            System.out.println("Filas insertadas: " + filas);
            System.out.println("Insercion correcta");

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}


	public static void mostrarLibros() {
		
		 String sql = "SELECT id, titulo, autor, anio_publicacion, disponible, falta FROM libros";
		 
		 libros.clear();

	        try (Connection con = Conexion.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	        	
	        	if(!rs.next()) {
	        		
	        		System.out.println("No hay elementos en la tabla");
	        	}else {
	        		
	        		System.out.println("ID\tTITULO\tAUTOR\tAÑO\tDISPONIBLE\tFECHA ALTA");
	        		
		            do {
		            	
		                int id = rs.getInt("id");
		                String titulo = rs.getString("titulo");
		                String autor = rs.getString("autor");
		                String year = rs.getString("anio_publicacion");
		                String disponible = rs.getString("disponible");
		                String alta = rs.getString("falta");
		                System.out.println(id + "\t" + titulo + "\t" + autor + "\t" + year + "\t" + disponible + "\t" + alta);
		                libros.add(new Libro(String.valueOf(id),titulo,autor,year,disponible,alta));
		           
		            }while (rs.next());
	        	}

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	}
	
	public static String[][] traducir(){
		
		mostrarLibros();
		
		String [][] t = new String[libros.size()][6];
		int i = 0;
		int j = 0;
		
		for (Libro libro : libros) {
			
			t[i][j++] = libro.getId();
			t[i][j++] = libro.getTitulo();
			t[i][j++] = libro.getAutor();
			t[i][j++] = libro.getAnio();
			t[i][j++] = libro.getDisponible();
			t[i++][j++] = libro.getFalta();
			j = 0;
			
		}
		
		return t;
		
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
	

}
