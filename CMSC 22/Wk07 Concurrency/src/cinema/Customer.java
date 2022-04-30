package cinema;
public class Customer extends Thread {
	private Cinema cinema;
	private String name;
	private int numOfTickets;

	public Customer(String name, Cinema cinema, int numOfTickets) {
	    this.name = name;
	    this.cinema = cinema;
	    this.numOfTickets = numOfTickets;
	}

	public String getCustomerName() {
		return this.name;
	}

	public int getNumOfTickets() {
		return this.numOfTickets;
	}

	public void buyTickets() {
		this.cinema.sellTickets(this);
	}

	public void run() {
		this.buyTickets();
	}
}