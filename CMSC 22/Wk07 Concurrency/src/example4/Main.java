package example4;

public class Main {
	
	   public static void main(String []args){
		  
		  // another way of creating a thread object is by implementing the Runnable interface
	      MyRunnable nr = new MyRunnable();
	      
	      // the Runnable object isn't a thread by itself
	      // but rather an argument for an instance of a Thread Class
	      Thread thread = new Thread(nr);	
	      thread.start();
	      
	   }
	   
}
