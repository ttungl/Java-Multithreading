import java.util.Scanner;
// low-level lock technique
public class Processor { // two methods run on different threads.
	
	public void produce() throws InterruptedException{
		synchronized (this){ // lock
			System.out.println("Producer thread running ...");
			wait();// wait consume.; check flag; hangover control the lock.
			System.out.println("Resumed. ");
		}
	}
	
	public void consume() throws InterruptedException{
		
		Scanner scanner =  new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized (this){
			System.out.println("Waiting for return key ...");
			scanner.nextLine();
			System.out.println("Return key pressed ...");
			notify();
			Thread.sleep(5000);
		}
	}
}
