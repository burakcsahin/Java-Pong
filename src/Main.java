
public class Main {
	public static int state = 0;
	public static Thread mainThread;
	public static Menu menu;
	public static PongFrame pongFrame;

	public static void main(String[] args) {
		menu = new Menu();
		mainThread = new Thread(menu);
		mainThread.start();

	}

	public static void changeState(int newState) {
		if(newState == 1 && state == 0) {
			menu.stop();
			pongFrame = new PongFrame();
			mainThread = new Thread(pongFrame);
			mainThread.start();

		}
		else if (newState == 0 && state == 1) {
			pongFrame.stop();
			menu = new Menu();
			mainThread = new Thread(menu);
			mainThread.start();
			
		}
		
		state = newState;
	}
}
