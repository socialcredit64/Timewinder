
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
	
	private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
	
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
		boolean left = false;
    	boolean up = false;
    	boolean down = false;
    	boolean right = false;

		leon = new Player(400, 300);
		
		SPEED = 5;
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
		
		if(left==true){
			leon.move("x",-1*SPEED);
		}
		if(right==true){
			leon.move("x",SPEED);
		}
		if(up==true){
			leon.move("y",-1*SPEED);
		}
		if(down==true){
			leon.move("y",SPEED);
		}
		g2d.fillRect(leon.getX(), leon.getY(), leon.getW(), leon.getH());
		
		
		twoDgraph.drawImage(back, null, 0, 0);

	}

	
	



	


	
//DO NOT DELETE
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

//DO NOT DELETE
	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		System.out.println(key);
		if (e.getKeyCode() == KeyEvent.VK_A) left = true;
		if (e.getKeyCode() == KeyEvent.VK_D) right = true;
		if (e.getKeyCode() == KeyEvent.VK_W) up = true;
		if (e.getKeyCode() == KeyEvent.VK_S) down = true;

		if(leon.getX()<0) leon.setX(0);
		if(leon.getX()+leon.getW()>1000) leon.setX(1000-leon.getW());
		if(leon.getY()<0) leon.setY(0);
		if(leon.getY()+leon.getH()>800) leon.setY(800-leon.getH());
	
	}
	

	//DO NOT DELETE
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) left = false;
		if (e.getKeyCode() == KeyEvent.VK_D) right = false;
		if (e.getKeyCode() == KeyEvent.VK_W) up = false;
		if (e.getKeyCode() == KeyEvent.VK_S) down = false;
		
		if(leon.getX()<0) leon.setX(0);
		if(leon.getX()+leon.getW()>1000) leon.setX(1000-leon.getW());
		if(leon.getY()<0) leon.setY(0);
		if(leon.getY()+leon.getH()>800) leon.setY(800-leon.getH());
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
