
public class Main {

	public static void main(String[] args) {
		PongFrame pFrame = new PongFrame();
		Thread thread1 = new Thread(pFrame);
		thread1.start();
	}

}
