import java.util.LinkedList;
import java.util.Random;
// low level sync
public class Processor {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	public void produce() throws InterruptedException{
		int value = 0;
		while(true){
			// need sync the lock
			synchronized (lock) {
				while(list.size()==LIMIT){
					lock.wait(); //check wait from notify()
				}
				list.add(value++);
				lock.notify();// wake-up thread.
			}
		}
	}
	public void consume() throws InterruptedException{
		Random random = new Random();
		while(true){
			synchronized (lock) {
				
				while(list.size()==0){
					lock.wait();
				}
				
				System.out.println("List size is: "+ list.size());
				int value = list.removeFirst();
				System.out.println("; value is: "+ value); 
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}

}
