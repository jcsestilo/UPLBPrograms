package cinema;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
	    Cinema cinema = new Cinema("SM Calamba Cinema", 20);
	    ArrayList<Customer> customerList = new ArrayList<Customer>(); 
	    
	    customerList.add(new Customer("A", cinema, 4));
	    customerList.add(new Customer("B", cinema, 5));
	    customerList.add(new Customer("C", cinema, 8));
	    customerList.add(new Customer("D", cinema, 3));
	    customerList.add(new Customer("E", cinema, 10));
	
	    for(Customer thread: customerList) {
	    	thread.start();
	    }
	    
// join() here makes the main threads wait the other threads
// finish before proceeding with its next execution (line 29)
	    
//	    for(Customer thread: customerList) {
//	    	try {
//				thread.join();
//			} catch (InterruptedException e) {}
//	    }
	
	     System.out.println("Movie is now starting.");
	}
}