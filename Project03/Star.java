import java.util.Random;
public class Star extends FlyingObject implements Award {
	private int xSpeed = 1;
	private int ySpeed = 3;
	private int awardType;
	
	public Star(){
		image = GameMain.Star;
		width = image.getWidth();
		height = image.getHeight();
		Random R = new Random();
		x = R.nextInt(GameMain.WIDTH - this.width);
	    y = -this.height; 
		awardType = R.nextInt(2);
	
	}
	
	public int getType(){
		return awardType;
	}
    public void step(){   	
    	if(x >= GameMain.WIDTH - this.width){
    		xSpeed = -1;
    	}
    	if(x <= 0){
    		xSpeed = 1;
    	}
    	x = x + xSpeed;
    	y = y + ySpeed;
	}
    public boolean outOfBounds(){
    	return this.y > GameMain.HEIGHT;
	}
}
