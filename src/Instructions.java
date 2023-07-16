import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Instructions extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Graphics2D g2d;
	public userInputs uInputs = new userInputs();
	public MouseListener ml = new MouseListener();
	public Texts upInst, downInst, gameEndInsts, exit;
	public boolean isCurrent = true;


	public Instructions() {
		this.setSize(Parameters.SCREEN_WIDTH, Parameters.SCREEN_HEIGHT);
		this.setTitle("Java Pong");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(uInputs);
		this.addMouseListener(ml);
		this.addMouseMotionListener(ml);

		this.upInst = new Texts("Press UP to go up", new Font("Comic Sans", Font.PLAIN, 50), Parameters.SCREEN_WIDTH/2.0 - 135, Parameters.SCREEN_HEIGHT/2.0 - 20, Color.MAGENTA);
		this.downInst = new Texts("Press DOWN to go down", new Font("Comic Sans", Font.PLAIN, 50), Parameters.SCREEN_WIDTH/2.0 - 200, Parameters.SCREEN_HEIGHT/2.0 + 110, Color.RED);
		this.gameEndInsts = new Texts("The game ends when a side scores 5", new Font("Comic Sans", Font.PLAIN, 50), Parameters.SCREEN_WIDTH/2.0 -360, 400, Color.ORANGE);
		this.exit = new Texts("Return to Main Menu <--", new Font("Comic Sans", Font.PLAIN, 50), Parameters.SCREEN_WIDTH/2.0 + 310, Parameters.SCREEN_HEIGHT/2.0 + 500, Color.WHITE);
		
		g2d = (Graphics2D)getGraphics();

	}

	public void update(double time) {
		BufferedImage dbImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D dbg = dbImage.createGraphics();
		this.draw(dbg);
		g2d.drawImage(dbImage, 0, 0, this);

		
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Parameters.SCREEN_WIDTH, Parameters.SCREEN_HEIGHT);

		upInst.draw(g2d);
		downInst.draw(g2d);
		gameEndInsts.draw(g2d);
		exit.draw(g2d);
		
		if (ml.getX() > exit.x && ml.getX() < exit.x + exit.width && ml.getY() > exit.y - exit.height / 2.0
                && ml.getY() < exit.y + exit.height / 2.0) {
            exit.color = new Color(158, 158, 158);

            if (ml.isMousePressed()) {
                Main.changeState(0);
            }
        } else {
            exit.color = Color.WHITE;
        }
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
