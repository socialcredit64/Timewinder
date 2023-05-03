
import javax.swing.*;
import javax.swing.plaf.TreeUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.awt.event.*; 
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{

	
	private BufferedImage back; 
	private int key; 
	private String gameState;
	private ArrayList <PlayerProj> playerBullets;
	private ArrayList <EnemyProj> enemybullets;
	private ArrayList <Enemy> testroomEnemies;
	private PlayerProj qqq;
	private EnemyProj ppp;
	private ArrayList <EnemyProj> e04;

	
	private boolean left;
    private boolean up;
    private boolean down;
    private boolean right;
	
	private int SPEED;
	private Player leon;

	private int px;
	private int py;

	private Character titleScreen;

	private int time;
	
	public Game() {
		new Thread(this).start();	
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		key =-1; 
		
		boolean left = false;
    	boolean up = false;
    	boolean down = false;
    	boolean right = false;
		
		playerBullets = new ArrayList<PlayerProj>();
		enemybullets = new ArrayList<EnemyProj>();

		leon = new Player(400, 300);
		qqq = new PlayerProj(0,0);
		ppp = new EnemyProj(0,0,0);

		//not actually a "character"
		titleScreen = new Character(0, 0, 1000, 800, "title screen.png");
		
		gameState="0.1"; 

		SPEED = 2; //player movespeed

		testroomEnemies = new ArrayList<Enemy>();
		testroomEnemies.add(new Enemy(600,300,10));
		testroomEnemies.add(new Enemy(200,300,100));

		time = 0;
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
		if( back == null)
			back=(BufferedImage)( (createImage(getWidth(), getHeight()))); 
		

		Graphics g2d = back.createGraphics(); 
	
		g2d.clearRect(0,0,getSize().width, getSize().height);
		
		
		if(gameState=="test room"){
			
			

			g2d.setColor(new Color(17, 17, 17));
			g2d.setFont( new Font("SANS_SERIF", Font.BOLD, 20));
			g2d.drawString(String.valueOf(key)+" testing font", 50, 110);
		
			drawPlayer(enemybullets,g2d,400,300);
			drawPlayerBullets(g2d);
			drawEnemies(testroomEnemies,g2d);
			if(willShoot(100)){
				setEnemyBullet(testroomEnemies, enemybullets, 10, 0);
			}
			
			drawEnemyBullets(enemybullets, g2d);
			
		}
		
		
		if (gameState=="menu"){
			
			/*g2d.setColor(new Color(255, 27, 255));
			g2d.drawRect(0, 0, 1000, 800);
			g2d.setColor(Color.BLACK);
			
			g2d.setFont( new Font("SANS_SERIF", Font.BOLD, 40));
			g2d.drawString("timewinder (alpha build)", 50, 110);*/
			g2d.drawImage(titleScreen.getImage().getImage(), titleScreen.getX(), titleScreen.getY(), titleScreen.getW(), titleScreen.getH(), this);
		}
		
		if(gameState=="0.1"){
			g2d.setColor(new Color(171, 17, 17));
			g2d.setFont( new Font("SANS_SERIF", Font.BOLD, 40));
			g2d.drawString("Tip: Use WASD to move.",300,100);
			drawPlayer(e04,g2d,350,700);
		}
		
		

		
		++time;
		twoDgraph.drawImage(back, null, 0, 0);

	}

	
	









	private void drawPlayer(ArrayList<EnemyProj> enemy, Graphics g2d, int x, int y){
		boolean i = false;
		
		if(i=false){
			leon.setX(x);
			leon.setY(y);
			i=true;
		}
		

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
		if(leon.getX()+leon.getW()>1000) leon.setX(1000-leon.getW());
		if(leon.getY()<10) leon.setY(10);
		if(leon.getY()+leon.getH()>800) leon.setY(800-leon.getH());

		if(enemy!=e04){
			for(EnemyProj ebullet: enemy){
				if(ebullet.collision(leon)){
					leon.reduceHP(ebullet.getDMG());
					enemy.remove(ebullet);
				}
			}
		}
		

		g2d.drawString(String.valueOf(leon.getHP()),20,600);
			
		
	}
	
	private void drawPlayerBullets(Graphics g2d){
		g2d.setColor(Color.BLACK);
		for(PlayerProj i : playerBullets){
			i.moveBullets();
			g2d.fillOval(i.getX(),i.getY(),i.getW(),i.getH());
		}
		
	}

	private void drawEnemyBullets(ArrayList<EnemyProj> bullet, Graphics g2d){
		
		for (EnemyProj i: bullet){
			i.moveBullets();
			g2d.setColor(new Color(255, 46, 46));
			g2d.drawOval(i.getX(),i.getY(),i.getW(),i.getH());
			g2d.setColor(Color.white);
			g2d.fillOval(i.getX(),i.getY(),i.getW(),i.getH());
		}
	}

	private void setEnemyBullet(ArrayList<Enemy> enemy, ArrayList<EnemyProj> bullets, int damage, int roomid){
		Random rand = new Random(roomid);
		
		for(Enemy e: enemy){
			bullets.add(new EnemyProj(e.getCX(ppp),e.getCY(ppp), damage)); //create a new bullet
			bullets.get(bullets.size()-1).setUX(rand.nextInt(2));
			bullets.get(bullets.size()-1).setUY(rand.nextInt(2));
		}
		
	}

	private boolean willShoot(int multiplier){
		if(time%multiplier==0){
			return true;
		}
		else return false;
	}

	private void drawEnemies(ArrayList<Enemy> enemy, Graphics g2d) {
		for(Enemy e: enemy){
			g2d.setColor(new Color(55, 55, 255));
			g2d.fillRect(e.getX(), e.getY(), e.getW(), e.getH());
			
			if(e.getHP()<e.getMaxHP()){
				g2d.drawRect(e.getX(), e.getY()-13, e.getW(), 5);
				g2d.fillRect(e.getX(), e.getY()-13, e.getW()*e.getHP()/e.getMaxHP(), 5);
			
			}
			
			
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
