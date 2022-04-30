package cinema;
public class Cinema {
	public String name;
	private int numOfTickets;				//shared resource

	public Cinema(String name, int numOfTickets){
	    this.name = name;
	    this.numOfTickets = numOfTickets;
	}

	// in order to make the critical section synchronized
	// use java's keyword "synchronized"
	// public synchronized void sellTickets(Customer customer) {
	
	public void sellTickets(Customer customer) {		//critical section
		System.out.println("\n" + customer.getCustomerName() + " trying to buy " + customer.getNumOfTickets() +" tickets.");
		if(this.numOfTickets>=customer.getNumOfTickets()){
			this.numOfTickets = this.numOfTickets - customer.getNumOfTickets();
			System.out.println(customer.getCustomerName() + " successfully bought " + customer.getNumOfTickets() +" tickets.");
			System.out.println("Available tickets after "+ customer.getCustomerName() + "'s purchase: " + this.numOfTickets + "\n");
		}
		else System.out.println(customer.getCustomerName() + " failed to buy " + customer.getNumOfTickets() + " tickets. " + this.numOfTickets +" ticket/s available.\n");	
	}
}
