import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;

public class Main extends JFrame{
	private static final int WIDTH =1000;
	private static final int HEIGHT=800;
	
	public Main () {
		super("Timewinder");
		setSize(WIDTH, HEIGHT);
		Game play = new Game();
		((Component) play).setFocusable(true);
		
		Color Dark = new Color(102, 102, 102);
		
		
		setBackground(Dark);
		
		
		getContentPane().add(play);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	public static void main(String[] args) {
		Main run = new Main();
		

	}


}


