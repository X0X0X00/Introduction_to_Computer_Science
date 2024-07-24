import java.awt.image.BufferedImage;
public class Hero extends FlyingObject {

	private int life;
	private int doubleFire;
	private BufferedImage[] images;
	private int index;

	public boolean outOfBounds(){
		return false;
	}
	public void addLife(){
		life = life + 1;
	}
	public int getLife(){
		return life;
	}
	public void addDoubleFire(){
		doubleFire = doubleFire + 40;
	}
	public void setDoubleFire(int doubleFire){
		this.doubleFire = doubleFire;
	}
	public void subtractLife(){
		life = life - 1;
	}
	public void step(){
		image = images[index++/10%images.length];
	}


	public Hero(){
		image = GameMain.Hero0;
		width = image.getWidth();
		height = image.getHeight();
		x = 250;
		y = 400;                
		life = 4;
		doubleFire = 0;
		images = new BufferedImage[]{GameMain.Hero0, GameMain.Hero1};
		index = 0;
	}

	public Bullet[] shoot(){
		int xStep = this.width/4;
		if(doubleFire > 0){
			Bullet[] The_Bullets = new Bullet[2];
			The_Bullets[0] = new Bullet(this.x + 1 * xStep,this.y - 20);
			The_Bullets[1] = new Bullet(this.x + 3 * xStep,this.y - 20);
			doubleFire = doubleFire - 2;
			return The_Bullets;
		}else{
			Bullet[] The_Bullets = new Bullet[1];
			The_Bullets[0] = new Bullet(this.x + 2 * xStep,this.y - 20);
			return The_Bullets;
		}
	}
    
	public void moveTo(int x,int y){
		this.x = x - this.width/2;
		this.y = y - this.height/2;
	}

    public boolean hit(FlyingObject other){
    	int x1 = other.x - this.width/2;
    	int x2 = other.x + other.width + this.width/2;
    	int y1 = other.y - this.height/2;
    	int y2 = other.y + other.height + this.height/2;
    	int herox = this.x + this.width/2;
    	int heroy = this.y + this.height/2;
    	return herox > x1 && herox < x2
    			&&
				heroy > y1 && heroy < y2;
    }
}
