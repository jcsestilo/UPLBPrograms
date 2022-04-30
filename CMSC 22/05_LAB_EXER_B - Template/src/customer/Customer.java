package customer;

import marketplace.*;

public class Customer {
	protected String name;
	protected String address;
	protected String number;
	protected float coins;
	protected Product[] items;
	protected int itemCounter;
	public final static int MAX_PRODUCTS = 3;
	
	public Customer(String name, String address, String number, float coins) {
		// TODO: Complete this constructor
	}

	protected boolean canAfford(Product item) {
		// TODO: Complete this method
	}
	
	/*
	 * The function below should contain the logic of the buy method of a customer,
	 * complete the method below and make use of the following commands as you construct the flow of your algorithm:
	 *
	 * At the start of a transaction print the following
	 * System.out.println("**LOG: Customer " + this.name + " is trying to buy " + item.getName() + " from " + marketplace.getName() + ".");
	 * 
	 * After a successful purchase print the following:
	 * System.out.println("**LOG: The item " + item.getName() + " was successfully bought by customer " + this.getName() + ".");
	 * 
	 * If purchase invalid due to insufficient fund print the following:
	 * System.out.println("**LOG: " + item.getName() + " cannot be purchased because of insufficient funds.");
	 * 
	 * If item is not in the marketplace print the following:
	 * System.out.println("**LOG: The item " + item.getName() + " is not available in the " + marketplace.getName() + ".");	
	 * 
	 * If customer reached the max product purchase print the following:
	 * System.out.println("**LOG: Maximum number of products is reached.");
	 */

	public void buy(Product item, Marketplace marketplace){
		// TODO: Complete this method
	}
	
	// DO NOT MODIFY THIS METHOD
	public void viewState() {
		System.out.println("\n==========[ CUSTOMER STATE ]==========");
		System.out.println("\tName: "+this.name);
		System.out.println("\tAddress: "+this.address);
		System.out.println("\tMobile Number: "+this.number);
		System.out.println("\tShopping Coins: "+this.coins);
		System.out.println("\n\tPurchases: ");

		if (this.items.length > 0) {
			for(int i=0; i<this.items.length; i++) {
				if(this.items[i] != null) {
					System.out.println("\n\tItem " + (i+1) + ": ");
					this.items[i].viewInfo();					
				}
			}	
		} else System.out.println("\tCustomer hasn't purchased any item.");
		
		System.out.println("======================================");
	}

	public String getName() {
		return this.name;
	}
}