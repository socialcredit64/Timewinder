import javax.swing.ImageIcon;

public class Character {
    private int x,y,w,h,dx,dy,hp;
    private ImageIcon sprite;

    public Character() {
		x=0;
		y=0;
		w=350;
		h=350;
		sprite = new ImageIcon("");
		dx=0;
		dy=0;
        hp=0;
	}

    public Character(int xV, int yV, ImageIcon i){ //player constructor
        x=xV;
        y=yV;
        w=100;
        h=100;
        dx=1;
        dy=1;
        hp=36;
        sprite = i;
    }

    public void move(String direction, int amount){
        if (direction == "x"){
            x+=amount;
        }
        if (direction == "y"){
            y+=amount;
        }
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
	public ImageIcon getImage() {
		return sprite;
	}

    //write setters
    
}
