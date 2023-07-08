import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

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

	public Texts playerScore, compScore;

	public boolean isCurrent = true;

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
		playerScore = new Texts(0, new Font("Comic Sans", Font.PLAIN, Parameters.TEXT_SIZE), 310, Parameters.TEXTPOS_Y, Color.MAGENTA);
		compScore = new Texts(0, new Font("Comic Sans", Font.PLAIN, Parameters.TEXT_SIZE), Parameters.SCREEN_WIDTH - 310 - 16, Parameters.TEXTPOS_Y, Color.RED);


		computercontroller = new ComputerController(new PlayerController(computer), ballRectangle);

		ball = new Ball(ballRectangle, p1, computer, playerScore, compScore);


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

		playerScore.draw(g2d);
		compScore.draw(g2d);


		p1.draw(g2d);
		computer.draw(g2d);
		ballRectangle.draw(g2d);
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
