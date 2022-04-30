package example2;

public class MyThread extends Thread {
	
	// run() is required to implement when extending Thread class
	public void run(){
		
		// same as the previous example but only for a limited number (*10)
		for(int i=0; i<10; i++){
			System.out.println("myThread");
		    try {
		    	Thread.sleep(1000);
		    } catch (InterruptedException e) {}
		}
		
	}
	  
}
