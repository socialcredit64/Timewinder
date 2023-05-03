import javax.swing.ImageIcon;

public class Background {
    private int x,y,w,h;
    private ImageIcon pic;
    
    public Background(){
        x=0;
		y=0;
		w=1000;
		h=800;
    }

    public Background(ImageIcon i){
        x=0;
		y=0;
		w=1000;
		h=800;
        pic = i;
    }

    public ImageIcon getImgIcon(){
        return pic;
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
}
