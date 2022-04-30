package example2;

public class Main {
	
	public static void main(String []args){
		
		// creates an object that extends Thread class
		MyThread thread = new MyThread();
		thread.start();	//executes run() method in another thread apart from the main thread used in main()
		
		System.out.println("This is what the main thread executes.");
		
	}
    
}

