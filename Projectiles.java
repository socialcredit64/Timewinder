package spcaeinvaders;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Projectiles {
	private int x,y,w,h,dx,dy;
	private ImageIcon projImg;
	public Projectiles() {
		x=0;
		y=0;
		w=40;
		h=90;
		projImg = new ImageIcon("");
		dx=0;
		dy=0;
	}
	 //alien constructor
	public Projectiles(int xV, int yV, int width, int height, ImageIcon i) {
		x=xV;
		y=yV;
		w=width;
		h=height;
		dx=0;
		dy=15;
		projImg=i;
	}
	
	//player constructor
	
	public Projectiles(int xV, int yV, ImageIcon i) {
		x=xV;
		y=yV;
		w=20;
		h=70;
		dx=0;
		dy=-15;
		projImg=i;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	public ImageIcon getBulletImage() {
		return projImg;
	}
	
	public boolean collision(SpaceShip s) {
		Rectangle ship = new Rectangle(s.getX(), s.getY(), s.getW(), s.getH());
		Rectangle missile = new Rectangle(getX(), getY(), getW(), getH());
		if(ship.intersects(missile)) {
			return true;
		}
				return false;
	}
	
	public void bulletmove() {
		y+=dy;
	}
}
