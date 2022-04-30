package example3;

public class MyThread extends Thread {
	
	public void run(){	
		
		 for(int i=0; i<10; i++){
			
			// prints the name of the thread so that we could distinguish which one is running at a given time
			System.out.println(Thread.currentThread().getName());
		    
			try {
		    	Thread.sleep(1000);
		    } catch (InterruptedException e) {}
		    
		 }
		 
	 }
	
}
