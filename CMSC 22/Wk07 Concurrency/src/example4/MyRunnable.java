package example4;

public class MyRunnable implements Runnable {
	
	// run() needs to be implemented; it is required
	public void run(){
		
		for(int i=0; i<10; i++){
			
			System.out.println(Thread.currentThread().getName()+"*");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
		}
		
	}
	
}
