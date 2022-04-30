/**
 * @author Jan Coleen S. Estilo
 * Made Oct. 21, 2021 at 16:00
 * Exercise 5
 */

package customer;

import marketplace.*;

public class Customer {
	protected String name;
	protected String address;
	protected String number; // Contact number
	protected float coins;
	protected Product[] items;
	protected int itemCounter;
	public final static int MAX_PRODUCTS = 3;

	public Customer(String name, String address, String number, float coins) {
		// Constructor for customer, RebateCustomer also refers to this constructor
		this.name = name;
		this.address = address;
		this.number = number;
		this.coins = coins;
		this.items = new Product[MAX_PRODUCTS];
	}

	protected boolean canAfford(Product item) {
		if(item.getPrice() <= this.coins){ // If the price of the item is less than or equal to the customer's coins,
									// returns true, which means customer can buy.
			return true;
		} else { // Will return false if customer cannot buy the product.
			return false;
		}
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
		// Start of transaction
		System.out.println("**LOG: Customer " + this.name + " is trying to buy " + item.getName() + " from " + marketplace.getName() + ".");

		// If customer reached the max product purchase
		if(this.itemCounter == MAX_PRODUCTS){
			System.out.println("**LOG: Maximum number of products is reached.");
			return;
		}
		if(marketplace.find(item)){ // If the item is found in the marketplace,
			if(this.canAfford(item)){ // If the customer can afford the item,

				// Add the item to the customer's list of items
				this.items[this.itemCounter] = item;
				this.itemCounter++;

				// The customer shopping coins is decreased by the item’s price
				this.coins = this.coins - item.getPrice();

				// Process the transaction
				marketplace.transact(item);

				// After a successful purchase
				System.out.println("**LOG: The item " + item.getName() + " was successfully bought by customer " + this.getName() + ".");
				return;

			} else { // If the item is found but the customer cannot afford it,
				System.out.println("**LOG: " + item.getName() + " cannot be purchased because of insufficient funds.");
			}
		} else { // If item is not in the marketplace
			System.out.println("**LOG: The item " + item.getName() + " is not available in the " + marketplace.getName() + ".");
		}
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