
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage; 
import java.awt.event.*; 
import java.util.ArrayList;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{

	
	private BufferedImage back; 
	private int key; 
	private String gameState;
	private int playerVspeed;
	private int playerHspeed;
	private int SPEED;
	private Player leon;
	
	
	public Game() {
		new Thread(this).start();	
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		key =-1; 
		gameState="start";
		playerHspeed = 0; //player's horizontal speed
		playerVspeed = 0; //player's vertical 
		SPEED = 1;
		leon = new Player(400, 300);
	}

	//
		
		

	
	
	public void run()
	   {
	   	try
	   	{
	   		while(true)
	   		{
	   		   Thread.currentThread().sleep(5);
	            repaint();
	         }
	      }
	   		catch(Exception e)
	      {
	      }
	  	}
	

	
	
	
	public void paint(Graphics g){
		
		Graphics2D twoDgraph = (Graphics2D) g; 
		if( back ==null)
			back=(BufferedImage)( (createImage(getWidth(), getHeight()))); 
		

		Graphics g2d = back.createGraphics(); 
	
		g2d.clearRect(0,0,getSize().width, getSize().height);
		
		g2d.setFont( new Font("SANS_SERIF", Font.BOLD, 20));
		
		g2d.drawString(String.valueOf(key)+" testing font", 50, 110);
		
		
		leon.move("x", playerHspeed);
		leon.move("y", playerVspeed);
		g2d.fillRect(leon.getX(), leon.getY(), leon.getW(), leon.getH());
		
		
		twoDgraph.drawImage(back, null, 0, 0);

	}

	
	



	private void f1(int directionspeed, int sign){ //抽象
		directionspeed = sign*SPEED;
	}
	private void f2(int directionspeed, int sign){
		directionspeed = 0;
	}


	
//DO NOT DELETE
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

//DO NOT DELETE
	@Override
	public void keyPressed(KeyEvent e) {
		key= e.getKeyCode();
		System.out.println(key);
		if (key==87){ //W
			f1(playerVspeed,-1);
		}
		if (key==83){ //S
			f1(playerVspeed,1);
		}
		if (key==65){ //A
			f1(playerHspeed,-1);
		}
		if (key==68){ //D
			f1(playerHspeed,1);
		}
		
		
	
	}
	

	//DO NOT DELETE
	@Override
	public void keyReleased(KeyEvent e) {
		if (key==87){ //W
			f2(playerVspeed,-1);
		}
		if (key==83){ //S
			f2(playerVspeed,1);
		}
		if (key==65){ //A
			f2(playerHspeed,-1);
		}
		if (key==68){ //D
			f2(playerHspeed,1);
		}

	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseClicked(MouseEvent e) {
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



	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	

	
}
