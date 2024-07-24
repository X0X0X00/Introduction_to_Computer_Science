/*
Zhenhao Zhang 32277234 zzh133@u.rochester.edu
Tony Ling 32249392 tling2@u.rochester.edu
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMain extends JPanel {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;
	public static BufferedImage Background;
	public static BufferedImage Start;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	public static BufferedImage Aircraft;
	public static BufferedImage Star;
	public static BufferedImage Bullet;
	public static BufferedImage Hero0;
	public static BufferedImage Hero1;

	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	private int state = 0;

	private Hero hero = new Hero();
	private Bullet[] Bullets = {};
	private FlyingObject[] flyings = {};

	private Timer timer;
	private int intervel = 10;
	int flyEnteredIndex = 0;
	int shootIndex = 0;
	int score = 0;
	static double time = 0.00;
	static int time1 = 0;

	// get image
	static {
		try {
			Background = ImageIO.read(GameMain.class.getResource("Background.png"));
			Start = ImageIO.read(GameMain.class.getResource("Start.png"));
			pause = ImageIO.read(GameMain.class.getResource("Pause.png"));
			gameover = ImageIO.read(GameMain.class.getResource("Gameover.png"));
			Aircraft = ImageIO.read(GameMain.class.getResource("Aircraft.png"));
			Star = ImageIO.read(GameMain.class.getResource("Star.png"));
			Bullet = ImageIO.read(GameMain.class.getResource("Bullet.png"));
			Hero0 = ImageIO.read(GameMain.class.getResource("Hero0.png"));
			Hero1 = ImageIO.read(GameMain.class.getResource("Hero1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		g.drawImage(Background, 0, 0, null);
		paintHero(g);
		paintFlyingObjects(g);
		paintBullets(g);
		paintScore(g);
		paintState(g);
	}

	//paint
	public void paintScore(Graphics g) {
		g.setColor(new Color(0x000000));
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		g.drawString("SCORE: " + score, 20, 25);
		g.drawString("LIFE: " + hero.getLife(), 20, 45);
	}

	public static FlyingObject nextOne() {
		Random R = new Random();
		int type = R.nextInt(20);
		if (type == 0) {
			return new Star();
		} else {
			return new Aircraft();
		}
	}

	public void paintState(Graphics g) {
		switch (state) {
			case START:
				g.drawImage(Start, 0, -150, null);
				break;
			case PAUSE:
				g.drawImage(pause, 0, 0, null);
				break;
			case GAME_OVER:
				g.drawImage(gameover, 0, 0, null);
				break;

		}
	}

	public void enterAction() {
		flyEnteredIndex++;
		if (flyEnteredIndex % 40 == 0) {
			FlyingObject obj = nextOne();
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
		}
	}

	public void stepAction() {
		hero.step();
		int num = time1 / 15;
		for (int i = 0; i < flyings.length; i++) {
			for(int j = 0; j <= num; j++) {
				flyings[i].step();
			}
		}
		for (int i = 0; i < Bullets.length; i++) {
			for(int j = 0; j <= num/2; j++) {
				Bullets[i].step();
			}
		}
	}

	public void shootAction() {
		shootIndex = shootIndex + 1;
		if (shootIndex % 30 == 0) {
			Bullet[] The_Bullet = hero.shoot();
			Bullets = Arrays.copyOf(Bullets, Bullets.length + The_Bullet.length);
			System.arraycopy(The_Bullet, 0, Bullets, Bullets.length - The_Bullet.length, The_Bullet.length);
		}
	}

	public void outOfBoundsAction() {
		int index = 0;
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (!f.outOfBounds()) {
				flyingLives[index] = f;
				index++;
			}
		}
		flyings = Arrays.copyOf(flyingLives, index);

		index = 0;
		Bullet[] BulletsLives = new Bullet[Bullets.length];
		for (int i = 0; i < Bullets.length; i++) {
			Bullet bs = Bullets[i];
			if (!bs.outOfBounds()) {
				BulletsLives[index] = bs;
				index++;
			}
		}
		Bullets = Arrays.copyOf(BulletsLives, index);

	}

	public void bangAction() {
		for (int i = 0; i < Bullets.length; i++) {
			bang(Bullets[i]);
		}
	}

	public void bang(Bullet b) {
		int index = -1;
		for (int i = 0; i < flyings.length; i++) {
			if (flyings[i].shootBy(b)) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			FlyingObject one = flyings[index];
			if (one instanceof Enemy) {
				Enemy e = (Enemy) one;
				score += e.getScore();
			}
			if (one instanceof Award) {
				Award a = (Award) one;
				int type = a.getType();
				switch (type) {
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
					hero.addLife();
					break;
				}
			}
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = t;
			flyings = Arrays.copyOf(flyings, flyings.length - 1);
		}
	}

	public void checkGameOverAction() {
		if (isGameOver()) {
			state = GAME_OVER;
			time1 = 0;
			time = 0;
		}
	}

	public boolean isGameOver() {
		for (int i = 0; i < flyings.length; i++) {
			if (hero.hit(flyings[i])) {
				hero.subtractLife();
				hero.setDoubleFire(0);

				FlyingObject t = flyings[i];
				flyings[i] = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		return hero.getLife() <= 0;
	}

	public void action() {
		MouseAdapter l = new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				if (state == RUNNING) {
					int x = e.getX();
					int y = e.getY();
					hero.moveTo(x, y);
				}
			}
			public void mouseClicked(MouseEvent e) {
				switch (state) {
				case PAUSE:
					state = RUNNING;
					break;
				case RUNNING:
					state = PAUSE;
					break;
				case START:
					state = RUNNING;
					break;
				case GAME_OVER:
					hero = new Hero();
					flyings = new FlyingObject[0];
					Bullets = new Bullet[0];
					score = 0;
					state = START;
					break;
				}
			}
			public void mouseEntered(MouseEvent e) {
				if (state == PAUSE) {
					state = RUNNING;
				}
			}
			public void mouseExited(MouseEvent e) {
				if (state == RUNNING) {
					state = PAUSE;
				}
			}
		};

		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				if (state == RUNNING) {
					enterAction();
					stepAction();
					shootAction();
					outOfBoundsAction();
					bangAction();
					time = time +0.01;
					time1 = (int)time;
					checkGameOverAction();
				}
				repaint();
			}
		}, intervel, intervel);
	}

	// paint hero
	public void paintHero(Graphics g) {
		g.drawImage(hero.image, hero.x, hero.y, null);
	}

	public void paintFlyingObjects(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}

	// paint bullet
	public void paintBullets(Graphics g) {
		for (int i = 0; i < Bullets.length; i++) {
			Bullet b = Bullets[i];
			g.drawImage(b.image, b.x, b.y, null);
		}

	}

	// main
	public static void main(String[] args) {
		JFrame frame = new JFrame("Aircraft Battle");
		GameMain game = new GameMain();
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.action();
	}
}
