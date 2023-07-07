import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class PongFrame extends JFrame implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Graphics2D g2d;
	public userInputs uInputs = new userInputs();
	
	public Rectangle p1;
	public Rectangle computer;
	public Rectangle ballRectangle;
	
	public Ball ball;
	public PlayerController playercontroller;
	public ComputerController computercontroller;
	
	public PongFrame() {
		this.setSize(Parameters.SCREEN_WIDTH, Parameters.SCREEN_HEIGHT);
		this.setTitle("Java Pong");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addKeyListener(uInputs);
		Parameters.TOOLBAR_HEIGHT = this.getInsets().top;
		Parameters.INSETS_BOTTOM = this.getInsets().bottom;
		g2d = (Graphics2D) this.getGraphics();
		
		p1 = new Rectangle(40,40,Parameters.BAR_WIDTH,Parameters.BAR_HEIGHT, Color.MAGENTA);
		playercontroller = new PlayerController(p1, uInputs);
		
		computer = new Rectangle(Parameters.SCREEN_WIDTH - Parameters.BAR_WIDTH - Parameters.HZ_PADDING, 40, Parameters.BAR_WIDTH, Parameters.BAR_HEIGHT, Color.RED);

		
		ballRectangle = new Rectangle(Parameters.SCREEN_WIDTH/2, Parameters.SCREEN_HEIGHT /2, Parameters.BALL_WIDTH, Parameters.BALL_WIDTH, Color.ORANGE);
		ball = new Ball(ballRectangle, p1, computer);
		
		computercontroller = new ComputerController(new PlayerController(computer), ballRectangle);
		
	}
	
	public void update(double time) {
		Image dbImage = createImage(getWidth(), getHeight());
		Graphics dbg = dbImage.getGraphics();
		this.draw(dbg);
		g2d.drawImage(dbImage, 0, 0, this);
		
		playercontroller.update(time);
		computercontroller.update(time);
		ball.update(time);
	
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Parameters.SCREEN_WIDTH, Parameters.SCREEN_HEIGHT);
		
		p1.draw(g2d);
		computer.draw(g2d);
		ballRectangle.draw(g2d);
	}
	
	@Override
	public void run() {
		double prevFrameTime = 0.0;
		while (true) {
			double currFrameTime = TimeInfo.getTime();
			double timeChange = currFrameTime - prevFrameTime;
			prevFrameTime = currFrameTime;
			
			update(timeChange);
			
			
		}
	}

}
