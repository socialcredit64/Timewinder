import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Projectile {
    private int x,y,w,h,dx,dy;
    public Projectile() {
		x=0;
		y=0;
		w=40;
		h=90;
		dx=0;
		dy=0;
	}

    public Projectile(int xV, int yV) { //player projectile
		x=xV;
		y=yV;
		w=20;
		h=70;
		dx=0;
		dy=0;
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

    public void setX(int n){
        x=n;
    }
    public void setY(int n){
        y=n;
    }
    public void setBulletSpeed(String direction, int n){
        if (direction == "x"){
            dx=n;
        }
        if(direction == "y"){
            dy=n;
        }
        
    }

    public boolean collision(Character c) {
		Rectangle character = new Rectangle(c.getX(), c.getY(), c.getW(), c.getH());
		Rectangle missile = new Rectangle(getX(), getY(), getW(), getH());
		if(character.intersects(missile)) {
			return true;
		}
				return false;
	}

    public void setBulletProjectory(Character c, int MPosX, int MPosY){
        //capitalized <- vectors
        //TODO: Math.abs(MPosX-c.getCX());
    }
}