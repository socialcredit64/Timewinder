
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.awt.event.*; 
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{

	
	private BufferedImage back; 
	private int key; 
	private int gameState;
	private ArrayList <PlayerProj> playerBullets;
	private ArrayList <EnemyProj> enemybullets;
	private ArrayList <Enemy> testroomEnemies;
	private ArrayList <Enemy> room3Enemies;
	private PlayerProj qqq;
	private EnemyProj ppp;
	private ArrayList <EnemyProj> e04;

	
	private boolean left;
    private boolean up;
    private boolean down;
    private boolean right;
	
	private int SPEED;
	private ArrayList <Player> leon;

	private int px;
	private int py;

	private ArrayList <Background> bg;

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

		leon = new ArrayList<Player>();
		leon.add(new Player(400,300)); 
		leon.add(new Player(0,0)); //main menu
		leon.add(new Player(200,400)); 
		leon.add(new Player(100,400)); //3

		qqq = new PlayerProj(0,0);
		ppp = new EnemyProj(0,0,0);

		bg = new ArrayList<Background>();
		bg.add(new Background(new ImageIcon("smile.png")));
		bg.add(new Background(new ImageIcon("title screen.png")));
		bg.add(new Background(new ImageIcon("2.png")));
		bg.add(new Background(new ImageIcon("3.png")));
		
		
		gameState=2; 

		SPEED = 2; //player movespeed

		testroomEnemies = new ArrayList<Enemy>();
		testroomEnemies.add(new Enemy(600,300,10));
		testroomEnemies.add(new Enemy(200,300,100));

		room3Enemies = new ArrayList<Enemy>();
		room3Enemies.add(new Enemy(650,400,80));


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
		
		
		
		
		g2d.drawImage(bg.get(gameState).getImgIcon().getImage(), bg.get(gameState).getX(), bg.get(gameState).getY(), bg.get(gameState).getW(), bg.get(gameState).getH(), this);
		
		
		
		
		if(gameState==0){
			
			g2d.setColor(new Color(17, 17, 17));
			g2d.setFont( new Font("SANS_SERIF", Font.BOLD, 20));
			g2d.drawString(String.valueOf(key)+" testing font", 50, 110);
		
			drawPlayer(0,enemybullets,g2d);
			drawPlayerBullets(g2d);
			drawEnemies(testroomEnemies,g2d);
			if(willShoot(100)){
				setEnemyBullet(testroomEnemies, enemybullets, 10, 0);
			}
			drawEnemyBullets(enemybullets, g2d);
		}
		
		
		if (gameState==1){
			
			
			
		}
		
		if(gameState==2){
			g2d.setColor(new Color(171, 17, 17));
			g2d.setFont( new Font("SANS_SERIF", Font.PLAIN, 34));
			g2d.drawString("Tip: Use WASD to move.",300,80);
			drawPlayer(gameState,e04,g2d);
			if(leon.get(gameState).getX()+leon.get(gameState).getW()>980&&(leon.get(gameState).getY()>300&&leon.get(gameState).getY()+leon.get(gameState).getH()<500)){
				gameState=3;
			}
		}

		if(gameState==3){
			drawPlayer(gameState,enemybullets,g2d);
			g2d.setColor(new Color(230, 17, 17));
			g2d.setFont(new Font("SANS_SERIF", Font.PLAIN, 34));
			g2d.drawString("Tip: Aim with your mouse, then MBLEFT to fire.",50,180);
			drawPlayerBullets(g2d);
			drawEnemies(room3Enemies,g2d);
			if(willShoot(50)){ //
				setEnemyBullet(room3Enemies, enemybullets, 10, gameState);
			}
			drawEnemyBullets(enemybullets, g2d);
			if(room3Enemies.size()==0&&leon.get(gameState).getX()+leon.get(gameState).getW()>880&&leon.get(gameState).getY()>290&&leon.get(gameState).getH()<555){
				gameState=4;
			}
		}
		
		if(gameState==4){
			
		}
		
		
		
		
		++time;
		twoDgraph.drawImage(back, null, 0, 0);

	}

	
	









	private void drawPlayer(int room, ArrayList<EnemyProj> enemy, Graphics g2d){
		
		

		g2d.setColor(Color.BLACK);
		g2d.fillRect(leon.get(room).getX(), leon.get(room).getY(), leon.get(room).getW(), leon.get(room).getH());
		
		
		
		if(left==true){
			leon.get(room).move("x",-1*SPEED);
		}
		if(right==true){
			leon.get(room).move("x",SPEED);
		}
		if(up==true){
			leon.get(room).move("y",-1*SPEED);
		}
		if(down==true){
			leon.get(room).move("y",SPEED);
		}

		if(leon.get(room).getX()<10) leon.get(room).setX(10);
		if(leon.get(room).getX()+leon.get(room).getW()>1000) leon.get(room).setX(1000-leon.get(room).getW());
		if(leon.get(room).getY()<10) leon.get(room).setY(10);
		if(leon.get(room).getY()+leon.get(room).getH()>800) leon.get(room).setY(800-leon.get(room).getH());

		if(enemy!=e04){
			for(EnemyProj ebullet: enemy){
				if(ebullet.collision(leon.get(room))){
					for(Player i: leon){
						i.reduceHP(ebullet.getDMG());
					}
					enemy.remove(ebullet);
				}
			}
		}
		

		g2d.drawString(String.valueOf(leon.get(room).getHP()),20,600);

		if(leon.get(room).getHP()<0){
			gameState=0;
			for(Player i: leon){
				i.setHP(i.getMaxHP());
			}
		}
			
		
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
			((Graphics2D) g2d).setStroke(new BasicStroke(2));
			g2d.drawOval(i.getX(),i.getY(),i.getW(),i.getH());
			g2d.setColor(Color.white);
			g2d.fillOval(i.getX(),i.getY(),i.getW(),i.getH());
		}
	}

	private void setEnemyBullet(ArrayList<Enemy> enemy, ArrayList<EnemyProj> bullets, int damage, int roomid){
		Random rand = new Random(roomid);
		int xdirection = new Random().nextBoolean() ? 1 : -1;
		int ydirection = new Random().nextBoolean() ? 1 : -1;
		
		for(Enemy e: enemy){
			bullets.add(new EnemyProj(e.getCX(ppp),e.getCY(ppp), damage)); //create a new bullet
			bullets.get(bullets.size()-1).setUX(xdirection*rand.nextInt(2));
			bullets.get(bullets.size()-1).setUY(ydirection*rand.nextInt(2));
		}
		
	}

	private boolean willShoot(int frequency){
		if(time%frequency==0){
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

		if (e.getKeyCode() == KeyEvent.VK_ENTER) gameState = 1;
		
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
		
		if(gameState==1){
			//if(set hitbox for first button){
				
			//}
		}
		
		
		playerBullets.add(new PlayerProj(leon.get(gameState).getCX(qqq), leon.get(gameState).getCY(qqq)));
		
		playerBullets.get(playerBullets.size()-1).setBulletTrajectory(leon.get(gameState),px,py);
		

		System.out.println(playerBullets.get(playerBullets.size()-1).getUX());
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	

	
}
