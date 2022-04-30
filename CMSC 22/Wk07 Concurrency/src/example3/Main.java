package example3;

public class Main {
	
    public static void main(String []args){
    	
    	// for each iteration we're creating a new thread and starting it immediately
     	for(int i=0; i<5; i++){
     		
           MyThread thread = new MyThread();
           thread.start();
           
       }	
     	
  }
    
}

