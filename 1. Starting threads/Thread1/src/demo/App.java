package demo;
//Basic method to create threads in java.
class Runner extends Thread{
	public void run() {
		for(int i=0; i<10; i++){
			System.out.println("Hello"+i);
			try {
				Thread.sleep(100); // 1/10 (s)
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}	
}

public class App {
	public static void main(String[] args){
		Runner runner1 = new Runner();
		runner1.start(); // don't put run(); when start(), it goes to Runner class and looks into run() method, and run that thread.
		
		Runner runner2 = new Runner(); // run() method runs concurrently.
		runner2.start(); 
	}
}
