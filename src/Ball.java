public class Ball {
	public Rectangle rect;
	public Rectangle leftPad, rightPad;

	private double veloX = -600.0;
	private double veloY = 40.0;
	private double speed = 800.0;

	public Ball(Rectangle rect, Rectangle leftPad, Rectangle rightPad) {
		this.rect = rect;
		this.leftPad = leftPad;
		this.rightPad = rightPad;
	}

	public double newVelocityAngle(Rectangle paddle) {
		double relativeInterY = (paddle.y + (paddle.height / 2.0)) - (this.rect.y + (this.rect.height / 2.0));
		double normalInterY = relativeInterY / (paddle.height / 2.0);
		double alpha = normalInterY + Parameters.MAX_ANGLE;

		return Math.toRadians(alpha);
	}

	public void update(double dt) {
		if (veloY >= 0.0 && this.rect.y + (veloY * dt) + this.rect.height > Parameters.SCREEN_HEIGHT - Parameters.INSETS_BOTTOM) {
			veloY *= -1.0;
		} 
		else if (veloY < 0 && this.rect.y + (veloY * dt) < Parameters.TOOLBAR_HEIGHT) {
			veloY *= -1.0;
		}

		if (veloX < 0.0) {
			if (this.rect.x + (veloX * dt) < leftPad.x + leftPad.width) {
				if (this.rect.y + (veloY * dt) > leftPad.y &&
						this.rect.y + (veloY * dt) + this.rect.height < leftPad.y + leftPad.height) {
					double omega = newVelocityAngle(leftPad);
					double newVx = Math.abs((Math.cos(omega) * speed));
					double newVy = (-Math.sin(omega) * speed);

					double oldSign = Math.signum(veloX);
					this.veloX = newVx * (-1.0 * oldSign);
					this.veloY = newVy;

					normalizeVelocity();
				}
			}
		} 
		else if (veloX > 0) {
			if (this.rect.x + (veloX * dt) + rect.width > rightPad.x) {
				if (this.rect.y + (veloY * dt) > rightPad.y &&
						this.rect.y + (veloY * dt) + this.rect.height < rightPad.y + rightPad.height) {
					double omega = newVelocityAngle(rightPad);
					double newVx = Math.abs((Math.cos(omega) * speed));
					double newVy = (-Math.sin(omega) * speed);

					double oldSign = Math.signum(veloX);
					this.veloX = newVx * (-1.0 * oldSign);
					this.veloY = newVy;

					normalizeVelocity();
				}
			}
		}

		this.rect.x += veloX * dt;
		this.rect.y += veloY * dt;
	}

	private void normalizeVelocity() {
		double magnitude = Math.sqrt(veloX * veloX + veloY * veloY);
		this.veloX /= magnitude;
		this.veloY /= magnitude;
		this.veloX *= speed;
		this.veloY *= speed;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public Rectangle getLeftPad() {
		return leftPad;
	}

	public void setLeftPad(Rectangle leftPad) {
		this.leftPad = leftPad;
	}

	public Rectangle getRightPad() {
		return rightPad;
	}

	public void setRightPad(Rectangle rightPad) {
		this.rightPad = rightPad;
	}

	public double getVeloX() {
		return veloX;
	}

	public void setVeloX(double veloX) {
		this.veloX = veloX;
	}

	public double getVeloY() {
		return veloY;
	}

	public void setVeloY(double veloY) {
		this.veloY = veloY;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	
}
