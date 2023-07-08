import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Menu extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Graphics2D g2d;
	public userInputs uInputs = new userInputs();
	public MouseListener ml = new MouseListener();
	public Texts startGame, exitGame, pong;
	public boolean isCurrent = true;


	public Menu() {
		this.setSize(Parameters.SCREEN_WIDTH, Parameters.SCREEN_HEIGHT);
		this.setTitle("Java Pong");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(uInputs);
		this.addMouseListener(ml);
		this.addMouseMotionListener(ml);

		this.startGame = new Texts("Play Pong", new Font("Comic Sans", Font.PLAIN, 50), Parameters.SCREEN_WIDTH/2.0 - 145, Parameters.SCREEN_HEIGHT/2.0, Color.WHITE);
		this.exitGame = new Texts("Exit", new Font("Comic Sans", Font.PLAIN, 50), Parameters.SCREEN_WIDTH/2.0 - 80, Parameters.SCREEN_HEIGHT/2.0 + 100, Color.WHITE);
		this.pong = new Texts("Java Pong by burakcsahin", new Font("Comic Sans", Font.PLAIN, 100), Parameters.SCREEN_WIDTH/2.0 -580, 150, Color.WHITE);

		g2d = (Graphics2D)getGraphics();

	}

	public void update(double time) {
		BufferedImage dbImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D dbg = dbImage.createGraphics();
		this.draw(dbg);
		g2d.drawImage(dbImage, 0, 0, this);

		if (ml.getX() > startGame.x && ml.getX() < startGame.x + startGame.width && ml.getY() > startGame.y - startGame.height / 2.0 
				&& ml.getY() < startGame.y + startGame.height / 2.0) {
			startGame.color = new Color (158, 158, 158);
			
			if (ml.isMousePressed()) {
				Main.changeState(1);
			}
		}
		else {
			startGame.color = Color.WHITE;
		}
		
		if (ml.getX() > exitGame.x && ml.getX() < exitGame.x + exitGame.width && ml.getY() > exitGame.y - exitGame.height / 2.0 
				&& ml.getY() < exitGame.y + exitGame.height / 2.0) {
			exitGame.color = new Color (158, 158, 158);
			
			if (ml.isMousePressed()) {
				this.dispose();
			}
		}
		else {
			exitGame.color = Color.WHITE;
		}
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Parameters.SCREEN_WIDTH, Parameters.SCREEN_HEIGHT);

		startGame.draw(g2d);
		exitGame.draw(g2d);
		pong.draw(g2d);

	}
	
	public void stop() {
		isCurrent = false;
	}

	@Override
	public void run() {
		double prevFrameTime = 0.0;
		while (isCurrent) {
			double currFrameTime = TimeInfo.getTime();
			double timeChange = currFrameTime - prevFrameTime;
			prevFrameTime = currFrameTime;

			update(timeChange);


		}
		this.dispose();
		return;
	}

}
