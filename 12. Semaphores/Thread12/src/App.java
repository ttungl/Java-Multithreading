import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Semaphore

public class App {

	public static void main(String[] args) throws Exception {
		Connection.getInstance().connect();
		
		// create multiple threads.
		ExecutorService executor =  Executors.newCachedThreadPool();
		for(int i =0; i<200;i++){ // run this will get 200 connections. Thus, use semaphore to limit the number of connections at a time.
			executor.submit(new Runnable(){
				public void run(){
					Connection.getInstance().connect();
				}
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);

	}

}
