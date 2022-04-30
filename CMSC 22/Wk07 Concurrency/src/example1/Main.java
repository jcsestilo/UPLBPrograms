package example1;

public class Main {
	
	public static void main(String []args){
		
		while(true){
			
			System.out.println("main");
			try {
				// stops the main thread from executing for 1000ms/1s
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
		}
		
	}
	
}
