import java.awt.image.BufferedImage;

public abstract class FlyingObject {
	protected BufferedImage image;
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	public abstract void step();
	public abstract boolean outOfBounds();

	public boolean shootBy(Bullet The_Bullet){
		int x1 = this.x;
		int x2 = this.x + this.width;
		int y1 = this.y;
		int y2 = this.y + this.height;
		int x3 = The_Bullet.x;
		int y3 = The_Bullet.y;
		return x3 > x1 && x3 < x2
				&&
				y3 > y1 && y3 < y2;
	}
	
}
