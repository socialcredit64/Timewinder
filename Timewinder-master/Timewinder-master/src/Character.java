import javax.swing.ImageIcon;

public class Character {
    private int x,y,w,h,dx,dy,hp,cx,cy;
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
		cx=(x+w)/2;
		cy=(y+h)/2;
	}

    public Character(int xV, int yV, ImageIcon i){ //player constructor
        x=xV;
        y=yV;
        w=40;
        h=40;
        dx=1;
        dy=1;
        hp=36;
        sprite = i;
		cx=(x+w)/2;
		cy=(y+h)/2;
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
	public int getCX() {
		return cx;
	}
	public int getCY() {
		return cy;
	}
	public ImageIcon getImage() {
		return sprite;
	}

    //to do: write setters
    public void setX(int n) {
		x=n;
	}
	public void setY(int n) {
		y=n;
	}
}
