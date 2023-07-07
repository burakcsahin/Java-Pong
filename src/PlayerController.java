import java.awt.event.KeyEvent;

public class PlayerController {

	protected Rectangle rect;
	protected userInputs uInputs;

	public PlayerController(Rectangle rect, userInputs uInputs) {
		this.rect = rect;
		this.uInputs = uInputs;
	}

	public PlayerController(Rectangle rect) {
		this.rect = rect;
		this.uInputs = null;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public userInputs getuInputs() {
		return uInputs;
	}

	public void setuInputs(userInputs uInputs) {
		this.uInputs = uInputs;
	}

	public void update(double dt) {
		if(uInputs != null) {
			if(uInputs.isKeyPressed(KeyEvent.VK_DOWN)) {
				moveDOWN(dt);
			}

			else if(uInputs.isKeyPressed(KeyEvent.VK_UP)) {
				moveUP(dt);
			}
		}
	}

	public void moveUP(double dt) {
		if(rect.y - Parameters.BAR_SPEED * dt > Parameters.TOOLBAR_HEIGHT)
			this.rect.y -= Parameters.BAR_SPEED * dt;
	}

	public void moveDOWN(double dt) {
		if((rect.y + Parameters.BAR_SPEED * dt) + rect.height < Parameters.SCREEN_HEIGHT - Parameters.INSETS_BOTTOM )
			this.rect.y +=  Parameters.BAR_SPEED * dt;
	}

}
