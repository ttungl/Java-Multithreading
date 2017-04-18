import java.util.Random;
// if using threadpools, it has the method to cancel thread.
public class App {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Starting ...");
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				Random random = new Random();
				for(int i=0; i<1E8; i++){
				/* if(Thread.currentThread().isInterrupted()) { // check interrupt flag.
						System.out.println("Interrupted! ");
						break;
					}*/
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println("Interrupted! ");
						break;
					}
					
					Math.sin(random.nextDouble());
				}
			}
		});
		
		t1.start();
		
		Thread.sleep(500);
		
		t1.interrupt(); // set flag.
		
		t1.join();
		
		
		System.out.println("Finished! ");
		
	}

}
