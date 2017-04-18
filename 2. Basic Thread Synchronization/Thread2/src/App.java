import java.util.Scanner;

// Volatile in java
// Basic thread sync.
class Processor extends Thread {
	private volatile boolean running = true; // volatile prevents threads catching new variable, then they are not changed.
	
	public void run() {
		while(running){
			System.out.println("Hello");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void shutdown(){
		running = false;
	}
	
}

public class App {
	public static void main(String[] args){
		Processor proc1 = new Processor();
		proc1.start(); // catch running state.
		
		System.out.println("Press return to stop ...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		proc1.shutdown();  // write/update to running state.
		
	}
}
