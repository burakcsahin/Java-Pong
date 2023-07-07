
public class ComputerController {
	public PlayerController playercontroller;
	public Rectangle ball;

	public ComputerController(PlayerController playercontroller, Rectangle ball) {
		this.playercontroller = playercontroller;
		this.ball = ball;
	}

	public void update(double dt) {
		playercontroller.update(dt);

		if( ball.y < playercontroller.rect.y) {
			playercontroller.moveUP(dt);
		}

		else if( ball.y + ball.height > playercontroller.rect.y + playercontroller.rect.height) {
			playercontroller.moveDOWN(dt);
		}
	}
}
