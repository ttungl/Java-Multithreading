import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private int count = 0;
	private Lock lock = new ReentrantLock(); // when a thread is acquired, it's locked, then have to unlock it by the same number of times.
	private Condition cond = lock.newCondition();
	
	
	private void increment(){
		for(int i=0; i<10000; i++){
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException{
		
		lock.lock();
		
		System.out.println("Waiting ...");
		cond.await(); // requires a lock
		
		System.out.println("Woken up!");
		
		try{
			increment(); // do session.
		} finally {
			lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException{
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("Press the return key!");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key!");
		
		cond.signal(); // signal() to wake-up
		
		try{
			increment(); // do session.
		} finally {
			lock.unlock();
		}
	}
	
	public void finished() {
		System.out.println("Count is: "+count);
	}

}
