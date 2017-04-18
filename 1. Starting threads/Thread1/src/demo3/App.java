package demo3;
// Basic method to create threads in java.
public class App {
	public static void main(String[] args){
		Thread t1 = new Thread(new Runnable(){
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
			
		});
		t1.start();
	}
}
