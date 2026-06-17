package biblioteca;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AnhadirLibro extends JPanel implements ActionListener{
	
	
	private JLabel fondo;
	private JTextField jTitulo;
	private JTextField jAutor;
	private JTextField jAnio;
	
	private JLabel jlTitulo;
	private JLabel jlAutor;
	private JLabel jlAnio;
	
	private JLabel jlTituloPantalla;
	
	private JButton jbSalir;
	private JButton jbGuardar;
	
	
	private JLabel jlerrores;
	
	public AnhadirLibro() {
		
		setLayout(null);
		
		int pos = 255;
		
		jlTituloPantalla = new JLabel("AÑADIR LIBRO");
		jlTituloPantalla.setFont(new Font("Arial", Font.BOLD, 80));
		jlTituloPantalla.setBounds(270,60,600,100);
		add(jlTituloPantalla);
		
		jlerrores = new JLabel();
		jlerrores.setFont(new Font("Arial", Font.BOLD, 40));
		jlerrores.setForeground(Color.red);
		jlerrores.setBounds(270,150,600,100);
		add(jlerrores);
		
		jlTitulo = new JLabel("TITULO : ");
		jlTitulo.setFont(new Font("Arial", Font.BOLD, 65));
		jlTitulo.setBounds(160,255,300,75);
		add(jlTitulo);
		
		jTitulo = new JTextField();
		jTitulo.setBounds(561, 255, 550, 70);
		jTitulo.setFont(new Font("Arial", Font.BOLD, 70));
		add(jTitulo);
		
		jlAutor = new JLabel("AUTOR : ");
		jlAutor.setFont(new Font("Arial", Font.BOLD, 65));
		jlAutor.setBounds(160,pos+=125,300,75);
		add(jlAutor);
		
		jAutor = new JTextField();
		jAutor.setBounds(561, pos, 550, 70);
		jAutor.setFont(new Font("Arial", Font.BOLD, 70));
		add(jAutor);
		
		jlAnio = new JLabel("AÑO : ");
		jlAnio.setFont(new Font("Arial", Font.BOLD, 65));
		jlAnio.setBounds(160,pos+=125,300,75);
		add(jlAnio);
		
		jAnio = new JTextField();
		jAnio.setBounds(561, pos, 550, 70);
		jAnio.setFont(new Font("Arial", Font.BOLD, 70));
		add(jAnio);
		
		jbSalir = new JButton("SALIR");
		jbSalir.setBounds(100, 680, 200, 50);
		jbSalir.setCursor(new Cursor(12));
		add(jbSalir);
		jbSalir.addActionListener(this);
		
		jbGuardar = new JButton("GUARDAR");
		jbGuardar.setBounds(900, 680, 200, 50);
		jbGuardar.setCursor(new Cursor(12));
		add(jbGuardar);
		jbGuardar.addActionListener(this);
		
		fondo = new JLabel(
			    new ImageIcon(
			        getClass().getResource("/img/fondoBiblio.jpg")
			    )
			);
		fondo.setBounds(0, 0, 1200, 800);
		add(fondo);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(jbSalir)) {
			
			JFrame ev = (JFrame) SwingUtilities.getWindowAncestor(this);
			ev.remove(this);
			ev.getContentPane().add(new Menu());
			ev.setVisible(true);
			
			
		}else if(e.getSource().equals(jbGuardar)) {
			
			String titulo = jTitulo.getText();
			String autor = jAutor.getText();
			String anio = jAnio.getText();
			boolean esCorrecto = false;
			
			try {
				
				int anio1 = Integer.parseInt(anio);
				esCorrecto = true;
				
			}catch(Exception ex) {
				
				System.out.println("Valor introducido no valido");
				esCorrecto = false;
				
			}
			
			if(esCorrecto) {
				
				jlerrores.setText("");
				Menu.anhadirLibros(jTitulo.getText(),jAutor.getText(), jAnio.getText());
				System.out.println("Libro añadido correctamente");
				JFrame ev = (JFrame) SwingUtilities.getWindowAncestor(this);
				ev.remove(this);
				ev.getContentPane().add(new Menu());
				ev.setVisible(true);
				
			}else {
				
				jlerrores.setText("El año tiene que ser un numero");
				
			}
			
		}
		
	}

}
