public class Bullet extends FlyingObject {
	private int speed = 2;
	public Bullet(int x,int y){
		image = GameMain.Bullet;
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	}
    public void step(){
		y = y - speed;
	}
    public boolean outOfBounds(){
		return this.y < -this.height;
	}

}
