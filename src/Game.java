//無敵和重生調回來
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
	private ArrayList <EnemyProj> bossbullets;
	
	private ArrayList <Enemy> testroomEnemies;
	private ArrayList <Enemy> room3Enemies;
	private ArrayList <Enemy> r4e;
	private ArrayList <Enemy> r5e;
	private ArrayList <Enemy> r6e;
	private Enemy boss;

	private PlayerProj qqq;
	private EnemyProj ppp;
	private ArrayList <EnemyProj> e04;
	private EnemyProj c8;

	
	private boolean left;
    private boolean up;
    private boolean down;
    private boolean right;
	
	private int SPEED;
	private ArrayList <Player> leon;

	private int px;
	private int py;
	private int vx;
	private int vy;

	private int desperate;

	private ArrayList <Background> bg;

	private int time;

	private Music sound;
	
	public Game() {
		new Thread(this).start();	
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		key =-1; 
		desperate=0;
		boolean left = false;
    	boolean up = false;
    	boolean down = false;
    	boolean right = false;
		
		playerBullets = new ArrayList<PlayerProj>();
		enemybullets = new ArrayList<EnemyProj>();
		bossbullets = new ArrayList<EnemyProj>();

		leon = new ArrayList<Player>();
		leon.add(new Player(400,300)); 
		leon.add(new Player(0,0)); //main menu
		leon.add(new Player(200,400)); 
		leon.add(new Player(100,400)); //3
		leon.add(new Player(100,350)); //4 in the castle
		leon.add(new Player(50,350)); //5 going up
		leon.add(new Player(460,900)); //6 before boss room
		leon.add(new Player(500,900)); //7 boss room

		qqq = new PlayerProj(0,0);
		ppp = new EnemyProj(0,0,0);
		c8 = new EnemyProj(0,0,0);

		bg = new ArrayList<Background>();
		bg.add(new Background(new ImageIcon("smile.png")));
		bg.add(new Background(new ImageIcon("title screen.png")));
		bg.add(new Background(new ImageIcon("2.png")));
		bg.add(new Background(new ImageIcon("3.png")));
		bg.add(new Background(new ImageIcon("4.png")));
		bg.add(new Background(new ImageIcon("5.png")));
		bg.add(new Background(new ImageIcon("6.png")));
		bg.add(new Background(new ImageIcon("7.png")));

		
		
		gameState=7; 

		SPEED = 2; //player movespeed

		testroomEnemies = new ArrayList<Enemy>();
		testroomEnemies.add(new Enemy(600,300,10));
		testroomEnemies.add(new Enemy(200,300,100));
		

		room3Enemies = new ArrayList<Enemy>();
		room3Enemies.add(new Enemy(650,400,75));


		r4e=new ArrayList<Enemy>();
		for(int i=0;i<4;++i){
		r4e.add(new Enemy(200+100*i,150,20));
		r4e.add(new Enemy(300+100*i,350,20));
		r4e.add(new Enemy(500+100*i,550,20));
		r4e.add(new Enemy(100*i,550,20));
	
		}

		r5e=new ArrayList<Enemy>();
		r5e.add(new Enemy(500,500,30));
		r5e.add(new Enemy(700,300,30));
		r5e.add(new Enemy(200,150,30));
		
		r6e=new ArrayList<Enemy>();
		r6e.add(new Enemy(470,315,1));

		boss = new Enemy(460, 120, 400);
		boss.setW(120);
		boss.setH(120);


		time = 0;

		sound = new Music();
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
			drawEnemies(55,55,255,testroomEnemies,g2d);
			if(willShoot(100)){
				setEnemyBullet(testroomEnemies, enemybullets, 10, 0);
			}
			drawEnemyBullets(enemybullets, g2d);
		}
		
		
		if (gameState==1){
			g2d.setColor(new Color(0, 0, 0));
			g2d.setFont( new Font("SANS_SERIF", Font.PLAIN, 64));
			g2d.drawString("New Game",80,300);
			g2d.drawString("Credits",140,630);
			g2d.setFont( new Font("SANS_SERIF", Font.PLAIN, 60));
			g2d.drawString("Training Mode",50,466);
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
			drawEnemies(55,55,255,room3Enemies,g2d);
			if(willShoot(50)){ //
				setEnemyBullet(room3Enemies, enemybullets, 10, gameState);
			}
			drawEnemyBullets(enemybullets, g2d);
			if(room3Enemies.size()==0&&leon.get(gameState).getX()+leon.get(gameState).getW()>880&&leon.get(gameState).getY()>290&&leon.get(gameState).getH()<555){
				gameState=4;
				time=0;
			}
		}
		
		if(gameState==4){
			drawPlayer(gameState,enemybullets,g2d);
			

			drawPlayerBullets(g2d);
			drawEnemies(55,55,255,r4e,g2d);
			if(willShoot(75)){ //
				setEnemyBullet(r4e, enemybullets, 10, gameState);
			}
			drawEnemyBullets(enemybullets, g2d);
				

			if(r4e.size()==0&&leon.get(gameState).getX()+leon.get(gameState).getW()>880&&leon.get(gameState).getY()>290&&leon.get(gameState).getH()<555){
				gameState=5;
				time=0;
			}

			if(time<250){
				g2d.setColor(new Color(230, 17, 17));
				g2d.setFont(new Font("SANS_SERIF", Font.BOLD, 54));
				g2d.drawString("Lvl 1: Dark Castle",250,380);
			}
		}

		if(gameState==5){
			for(int i=r4e.size()-1;i>=0;++i){ //destroy bullets
				r4e.remove(i);
			}

			drawPlayer(gameState,enemybullets,g2d);
			

			drawPlayerBullets(g2d);
			drawEnemies(55,55,255,r5e,g2d);
			if(willShoot(50)){ //
				setEnemyBullet(r5e, enemybullets, 10, gameState);
			}
			drawEnemyBullets(enemybullets, g2d);
			//make moving enemies
			
			if(r5e.size()==3){
				if((time/150)%2==0){
					r5e.get(0).move("y",-1);
					r5e.get(1).move("y",2);
					r5e.get(2).move("x",1);
				}
				else{
					r5e.get(0).move("y",1);
					r5e.get(1).move("y",-2);
					r5e.get(2).move("x",-1);
				}
			}
			else if(r5e.size()==2){
				if((time/150)%2==0){
					r5e.get(0).move("y",-1);
					r5e.get(1).move("x",1);
				}
				else{
					r5e.get(0).move("y",1);
					r5e.get(1).move("x",-1);
				}
			}
			else if (r5e.size()==1){
				if((time/100)%2==0){
					r5e.get(0).move("x",-1);
					r5e.get(0).move("y",1);
					
				}
				else{
					
					r5e.get(0).move("x",1);
					r5e.get(0).move("y",-1);
				}
			}
			

			

			if(r5e.size()==0&&leon.get(gameState).getY()<30&&leon.get(gameState).getX()>390&&leon.get(gameState).getX()<630){
				gameState=6;
				time=0;
			}
		}

		if(gameState==6){
			drawPlayer(gameState,enemybullets,g2d);
			drawEnemies(255, 55, 55, r6e, g2d);
			drawPlayerBullets(g2d);

			if(r6e.size()==0){
				sound.playmusic("healing.wav");
				leon.get(gameState).setHP(36);
				leon.get(gameState+1).setHP(36);
				g2d.setColor(new Color(230, 17, 17));
				g2d.setFont(new Font("SANS_SERIF", Font.BOLD, 54));
				g2d.drawString("Health Restored.",260,380);

				if(leon.get(gameState).getY()<30&&leon.get(gameState).getX()>390&&leon.get(gameState).getX()<630){
					gameState=7;
					time=0;
					for(int i=0; i<enemybullets.size();++i){
						enemybullets.remove(i);
					}
					
				}
			}

		}
		//boss fight
		

		if(gameState==7){
			//draw the Boss
			if(desperate==0){
				g2d.setColor(new Color(245, 49, 209));
			}
			else{
				g2d.setColor(new Color(244/2, 48/2, 210/2));
			}
			g2d.fillRect(boss.getX(), boss.getY(), 120, 120);
			
			g2d.setColor(new Color(245, 49, 49));
			g2d.setFont(new Font("SANS_SERIF", Font.BOLD, 40));
			g2d.drawString("Boss Health", 100, 590);
			g2d.drawRect(100, 600, 400, 25);
			g2d.fillRect(100, 600, boss.getHP(), 25);

			
			//player
			drawPlayer(gameState,enemybullets,g2d);
			drawPlayerBullets(g2d);

			//collision
			for(PlayerProj i: playerBullets){
				if(i.collision(boss)){
					playerBullets.remove(i);
					boss.reduceHP(10);
				}
			}
			for(EnemyProj i: bossbullets){
				if(i.collision(leon.get(gameState))){
					bossbullets.remove(i);
					leon.get(gameState).reduceHP(i.getDMG());
					sound.playmusic("takedamage.wav");
				}
			}

			if(boss.getHP()<=0){
				gameState=8; //win screen
			}
			
			//out of bound bullets
			for(PlayerProj i: playerBullets){
				if(i.getY()<60||i.getX()<60||i.getX()>940){
					playerBullets.remove(i);
				}
			}
			
			//bullet tracking logic
			vx=leon.get(gameState).getCX(c8);
			vy=leon.get(gameState).getCY(c8);

			//attack pattern: follow
			
			
			
			if(willShoot(20)){
				bossbullets.add(new EnemyProj(boss.getCX(c8),boss.getCY(c8),10));
				bossbullets.get(bossbullets.size()-1).setBulletTrajectory(boss,vx,vy);
			}
			
			
			for(EnemyProj i: bossbullets){
				i.setW(30);
				i.setH(i.getW());
				i.moveBullets();
				System.out.println(i.getUX());
				g2d.setColor(new Color(255, 46, 46));
				((Graphics2D) g2d).setStroke(new BasicStroke(3));
				g2d.drawOval(i.getX(),i.getY(),i.getW(),i.getH());
				g2d.setColor(Color.white);
				g2d.fillOval(i.getX(),i.getY(),i.getW(),i.getH());

			}
			
			//attack pattern: desperate
			if(desperate==0&&boss.getHP()<100){
				boss.setHP(300);
				++desperate;
			}
			if(desperate>0){
				if(willShoot(10)){
					Random rand = new Random(gameState);
					//positive and negative
					int xdirection = new Random().nextBoolean() ? 1 : -1;
					int ydirection = new Random().nextBoolean() ? 1 : -1;
					bossbullets.add(new EnemyProj(boss.getCX(ppp),boss.getCY(ppp), 10)); //create a new bullet
					bossbullets.get(bossbullets.size()-1).setUX(xdirection*rand.nextInt(3));
					bossbullets.get(bossbullets.size()-1).setUY(ydirection*rand.nextInt(3));
				}
			}


			//boss movement
			if(desperate==0){
				if(willShoot(50)){
					boss.move("x", (int) ((leon.get(gameState).getCX(ppp)-boss.getCX(ppp))*60/(Math.sqrt(Math.pow(leon.get(gameState).getCX(ppp)-boss.getCX(ppp), 2)+Math.pow(leon.get(gameState).getCY(ppp)-boss.getCY(ppp), 2)))));
					boss.move("y", (int) ((leon.get(gameState).getCY(ppp)-boss.getCY(ppp))*60/(Math.sqrt(Math.pow(leon.get(gameState).getCX(ppp)-boss.getCX(ppp), 2)+Math.pow(leon.get(gameState).getCY(ppp)-boss.getCY(ppp), 2)))));
				}
			}
			else {
				if(willShoot(25)){
					boss.move("x", (int) ((leon.get(gameState).getCX(ppp)-boss.getCX(ppp))*50/(Math.sqrt(Math.pow(leon.get(gameState).getCX(ppp)-boss.getCX(ppp), 2)+Math.pow(leon.get(gameState).getCY(ppp)-boss.getCY(ppp), 2)))));
					boss.move("y", (int) ((leon.get(gameState).getCY(ppp)-boss.getCY(ppp))*50/(Math.sqrt(Math.pow(leon.get(gameState).getCX(ppp)-boss.getCX(ppp), 2)+Math.pow(leon.get(gameState).getCY(ppp)-boss.getCY(ppp), 2)))));

				}
			}
			
		}
		
		
		
		if(leon.get(gameState).getHP()<=0){
			//System.exit(0);
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
						sound.playmusic("takedamage.wav");
					}
					enemy.remove(ebullet);
				}
			}
		}
		

		g2d.drawString(String.valueOf(leon.get(room).getHP()),20,600);

		
		
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
		//positive and negative
		int xdirection = new Random().nextBoolean() ? 1 : -1;
		
		int ydirection = new Random().nextBoolean() ? 1 : -1;
		
		for(Enemy e: enemy){
			bullets.add(new EnemyProj(e.getCX(ppp),e.getCY(ppp), damage)); //create a new bullet
			bullets.get(bullets.size()-1).setUX(xdirection*rand.nextInt(2));
			System.out.println(xdirection);
			bullets.get(bullets.size()-1).setUY(ydirection*rand.nextInt(2));
		}
		
	}


	private boolean willShoot(int frequency){
		if(time%frequency==0){
			return true;
		}
		else return false;
	}

	//55, 55, 255
	private void drawEnemies(int r, int g, int b, ArrayList<Enemy> enemy, Graphics g2d) {
		for(Enemy e: enemy){
			g2d.setColor(new Color(r, g, b));
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
			if(px>48&&px<458&&py>230&&py<357){
				gameState=2;
			}
		}
		
		sound.playmusic("shoot.wav");
		playerBullets.add(new PlayerProj(leon.get(gameState).getCX(qqq), leon.get(gameState).getCY(qqq)));
		
		playerBullets.get(playerBullets.size()-1).setBulletTrajectory(leon.get(gameState),px,py);
		

		System.out.println(playerBullets.get(playerBullets.size()-1).getUX());
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	

	
}
