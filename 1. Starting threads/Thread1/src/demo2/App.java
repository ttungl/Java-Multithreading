package demo2;
//Basic method to create threads in java.
class Runner implements Runnable { // this is the interface, one method; run().
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
		Thread t1 = new Thread(new Runner()); // pass an instant of Runner class.
		Thread t2 = new Thread(new Runner()); // pass an instant of Runner class.
		
		t1.start();
		t2.start();
	}
}
