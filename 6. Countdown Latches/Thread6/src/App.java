import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
	private CountDownLatch latch;
	// constructor
	public Processor(CountDownLatch latch){
		this.latch = latch;
	}
	public void run() {
		System.out.println("Started. ");
		try {
			Thread.sleep(3000); // 3s
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		latch.countDown();
	}
	
}

public class App {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3); // it lets threads waiting until the last reaches countdown to zero.
		
		ExecutorService executor = Executors.newFixedThreadPool(3); // 3 threads, each thread gets one processor.
		
		for(int i=0; i<3; i++){
			executor.submit(new Processor(latch));
			
		}
		try {
			latch.await(); // wait countdownlatch countdowns to zero.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed! ");
		
	}

}
