
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.awt.event.*; 
import java.util.ArrayList;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{

	
	private BufferedImage back; 
	private int key; 
	private String gameState;
	private ArrayList <PlayerProj> playerBullets;
	private ArrayList <Enemy> testroomEnemies;
	private PlayerProj qqq;

	
	private boolean left;
    private boolean up;
    private boolean down;
    private boolean right;
	
	private int SPEED;
	private Player leon;

	private int px;
	private int py;

	private Character titleScreen;

	
	
	public Game() {
		new Thread(this).start();	
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		key =-1; 
		gameState="test room"; 
		boolean left = false;
    	boolean up = false;
    	boolean down = false;
    	boolean right = false;
		
		playerBullets = new ArrayList<PlayerProj>();

		leon = new Player(400, 300);
		qqq = new PlayerProj(0,0);

		//not actually a "character"
		titleScreen = new Character(0, 0, 1000, 800, "title screen.png");


		SPEED = 2; //player movespeed

		testroomEnemies = new ArrayList<Enemy>();
		testroomEnemies.add(new Enemy(600,300,10));
		testroomEnemies.add(new Enemy(200,300,100));
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
		
		if(gameState=="test room"){
			
			

			g2d.setColor(new Color(17, 17, 17));
			g2d.setFont( new Font("SANS_SERIF", Font.BOLD, 20));
			g2d.drawString(String.valueOf(key)+" testing font", 50, 110);
		
			drawPlayer(g2d);
			drawPlayerBullets(g2d);
			drawEnemies(testroomEnemies,g2d);
			
		}
		
		
		if (gameState=="menu"){
			
			/*g2d.setColor(new Color(255, 27, 255));
			g2d.drawRect(0, 0, 1000, 800);
			g2d.setColor(Color.BLACK);
			
			g2d.setFont( new Font("SANS_SERIF", Font.BOLD, 40));
			g2d.drawString("timewinder (alpha build)", 50, 110);*/
			g2d.drawImage(titleScreen.getImage().getImage(), titleScreen.getX(), titleScreen.getY(), titleScreen.getW(), titleScreen.getH(), this);
		}
		

		
		
		twoDgraph.drawImage(back, null, 0, 0);

	}

	
	


	private void drawPlayer(Graphics g2d){
		g2d.setColor(Color.BLACK);
		g2d.fillRect(leon.getX(), leon.getY(), leon.getW(), leon.getH());
		
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

		if(leon.getX()<10) leon.setX(10);
		if(leon.getX()+leon.getW()>980) leon.setX(980-leon.getW());
		if(leon.getY()<10) leon.setY(10);
		if(leon.getY()+leon.getH()>760) leon.setY(760-leon.getH());
	}
	
	private void drawPlayerBullets(Graphics g2d){
		g2d.setColor(Color.BLACK);
		for(PlayerProj i : playerBullets){
			i.moveBullets();
			g2d.fillOval(i.getX(),i.getY(),i.getW(),i.getH());
		}
		
	}

	private void drawEnemies(ArrayList<Enemy> enemy, Graphics g2d) {
		for(Enemy e: enemy){
			g2d.setColor(new Color(55, 55, 255));
			g2d.fillRect(e.getX(), e.getY(), e.getW(), e.getH());
			
			g2d.drawRect(e.getX(), e.getY()-13, e.getW(), 5);
			g2d.fillRect(e.getX(), e.getY()-13, e.getW()*(e.getHP()/e.getMaxHP()), 5);
			
			
			for(PlayerProj pbullet: playerBullets){
				if(pbullet.collision(e)){
				
					e.reduceHP(pbullet.getDMG());
					playerBullets.remove(pbullet);
					
					if(e.getHP()<0){
						enemy.remove(e);
					}
				}
			}
		}
		
	}

	
//DO NOT DELETE
	@Override
	public void keyTyped(KeyEvent e){
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

		
	
	}
	

	//DO NOT DELETE
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) left = false;
		if (e.getKeyCode() == KeyEvent.VK_D) right = false;
		if (e.getKeyCode() == KeyEvent.VK_W) up = false;
		if (e.getKeyCode() == KeyEvent.VK_S) down = false;

		if (e.getKeyCode() == KeyEvent.VK_ENTER) gameState = "menu";
		
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
		px = e.getX();
		py = e.getY();
		System.out.println(px+", "+py);

		playerBullets.add(new PlayerProj(leon.getCX(qqq), leon.getCY(qqq)));
		
		playerBullets.get(playerBullets.size()-1).setBulletTrajectory(leon,px,py);


		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	

	
}
