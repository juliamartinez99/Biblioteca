package biblioteca;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class BuscarLibro extends JPanel implements ActionListener{
	
	private JLabel fondo;
	
	private JButton jjBtitulo;
	private JButton jbBAutor;
	private JButton jbOrdenarAnio;
	private JButton jbOrdenarAlta;
	private JButton salir;
	private JLabel jlTexto;
	private JTextField jtfBuscador;
	private JTable tabla;
	private JScrollPane scroll;
	
	private static String[] columnas = {"ID", "TITULO", "AUTOR","AÑO","DISPONIBLE","FECHA ALTA"};
	protected DefaultTableModel modelo = new DefaultTableModel(Menu.traducir(), columnas);
	
	public static ArrayList<Libro> libros = new ArrayList<>();
	
	public BuscarLibro() {
		
		setLayout(null);
		int pos = 50;
		
		
		jlTexto = new JLabel("INTRODUCE TEXTO: ");
		jlTexto.setFont(new Font("Arial", Font.BOLD, 40));
		jlTexto.setBounds(50,140,500,40);
		add(jlTexto);
		
		jtfBuscador = new JTextField();
		jtfBuscador.setBounds(590, 140, 550, 40);
		jtfBuscador.setFont(new Font("Arial", Font.BOLD, 40));
		add(jtfBuscador);
		
		jjBtitulo = new JButton("POR TITULO");
		jjBtitulo.setBounds(pos, 60, 200, 50);
		jjBtitulo.setCursor(new Cursor(12));
		add(jjBtitulo);
		jjBtitulo.addActionListener(this);
		
		
		jbBAutor = new JButton("POR AUTOR");
		jbBAutor.setBounds(pos+=225, 60, 200, 50);
		jbBAutor.setCursor(new Cursor(12));
		add(jbBAutor);
		jbBAutor.addActionListener(this);
		
		
		jbOrdenarAnio = new JButton("ORDENAR POR AÑO");
		jbOrdenarAnio.setBounds(pos+=225, 60, 200, 50);
		jbOrdenarAnio.setCursor(new Cursor(12));
		add(jbOrdenarAnio);
		jbOrdenarAnio.addActionListener(this);
		
		jbOrdenarAlta = new JButton("ORDENAR POR ALTA");
		jbOrdenarAlta.setBounds(pos+=225, 60, 200, 50);
		jbOrdenarAlta.setCursor(new Cursor(12));
		add(jbOrdenarAlta);
		jbOrdenarAlta.addActionListener(this);
		
		salir = new JButton("VOLVER AL MENU");
		salir.setBounds(pos+=225, 60, 200, 50);
		salir.setCursor(new Cursor(12));
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
		scroll.setBounds(58, 200, 1091, 500);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		add(scroll);
		
		fondo = new JLabel(new ImageIcon("src/img/fondoBiblio.jpg"));
		fondo.setBounds(0, 0, 1200, 800);
		add(fondo);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(salir)) {
			
			JFrame ev = (JFrame) SwingUtilities.getWindowAncestor(this);
			ev.remove(this);
			ev.getContentPane().add(new Menu());
			ev.setVisible(true);
			
		}else if(e.getSource().equals(jjBtitulo)) {
			
			tabla.setModel(new DefaultTableModel(traducir(1,jtfBuscador.getText()), columnas));
			
			
		}else if(e.getSource().equals(jbBAutor)) {
			
			tabla.setModel(new DefaultTableModel(traducir(2,jtfBuscador.getText()), columnas));
			
			
		}else if(e.getSource().equals(jbOrdenarAnio)) {
			
			tabla.setModel(new DefaultTableModel(traducir(3,jtfBuscador.getText()), columnas));
			
			
		}else if(e.getSource().equals(jbOrdenarAlta)) {
			
			tabla.setModel(new DefaultTableModel(traducir(4,jtfBuscador.getText()), columnas));
			
			
		}
		
	}
	
	public static void mostrarLibros(int tipo, String p) {
		
		String sql = "";
		
		switch(tipo) {
		
		case 1: 
			sql = "SELECT id, titulo, autor, anio_publicacion, disponible, falta FROM libros WHERE titulo LIKE ?";
			break;
		case 2:
			sql = "SELECT id, titulo, autor, anio_publicacion, disponible, falta FROM libros WHERE autor LIKE ?";
			break;
		case 3: 
			sql = "SELECT id, titulo, autor, anio_publicacion, disponible, falta FROM libros ORDER BY anio_publicacion desc";
			break;
		case 4: 
			sql = "SELECT id, titulo, autor, anio_publicacion, disponible, falta FROM libros ORDER BY falta desc";
			break;
		
		}
		
		
		 
		 libros.clear();

	        try (Connection con = Conexion.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ) {
	        	
	        	if(tipo == 1 || tipo == 2) {
	        		
		               ps.setString(1, "%"+p+"%");
		               
		               
	        	}
	               
	               //ejecutamos cambio
	               ResultSet rs = ps.executeQuery();
	        	
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
	
	public static String[][] traducir(int tipo, String p){
		
		mostrarLibros(tipo, p);
		
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

	
}
