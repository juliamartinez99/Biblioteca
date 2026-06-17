package biblioteca;

import java.awt.Image;
import java.awt.Taskbar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Ventana extends JFrame {
	
	public Ventana() {
		
		setTitle("Biblioteca");
		setBounds(200,200,1200,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icono = new ImageIcon(
		        getClass().getResource("/img/logo.png"));

		Image imagen = new ImageIcon(
			    getClass().getResource("/img/logo.png")
			).getImage();

			setIconImage(imagen);

			if (Taskbar.isTaskbarSupported()) {
			    Taskbar.getTaskbar().setIconImage(imagen);
			}
		
		add(new Menu());
		setVisible(true);
		
		
	}

}
