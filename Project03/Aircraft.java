import java.util.Random;

public class Aircraft extends FlyingObject implements Enemy {
	private int speed = 1;

	public Aircraft() {
		image = GameMain.Aircraft;
		width = image.getWidth();
		height = image.getHeight();
		Random r = new Random();
		x = r.nextInt(GameMain.WIDTH - this.width);
		y = -this.height;

	}

	public int getScore() {
		return 1;
	}

	public void step() {
		y = y + speed;
	}

	public boolean outOfBounds() {
		return this.y > GameMain.HEIGHT;

	}

}
