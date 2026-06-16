package biblioteca;

import javax.swing.JFrame;

public class Ventana extends JFrame {
	
	public Ventana() {
		
		setTitle("Biblioteca");
		setBounds(200,200,1200,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new Menu());
		setVisible(true);
		
		
	}

}
