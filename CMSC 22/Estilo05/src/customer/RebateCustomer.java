/**
 * @author Jan Coleen S. Estilo
 * Made Oct. 21, 2021 at 16:00
 * Exercise 5
 */

package customer;

import marketplace.Marketplace;
import marketplace.Product;

public class RebateCustomer extends Customer{
	// Attributes

	// Constructor
	public RebateCustomer(String name, String address, String number, float coins){
		super(name, address, number, coins); // Refers to the superclass Customer
	}
	// Methods
	public void buy(Product item, Marketplace marketplace){ // Override the buy method in Customer

		/* I copy and pasted the method of buy to this method because rebate will be processed once the transaction is successful.
		* When I used super.buy method, regardless on whether the transaction is successful or not, the rebate is still being
		* computed.
		*/

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

				// REBATE
				// Get the 5% to add to the rebate customer
				float rebate = (float) (item.getPrice() * this.getRebate());

				// Add the rebate to the rebate customer's coins
				this.coins = this.coins + rebate;

				// Rebate Printing
				System.out.println("**LOG: A rebate of " + rebate + " is added back to your account.");
				return;

			} else { // If the item is found but the customer cannot afford it,
				System.out.println("**LOG: " + item.getName() + " cannot be purchased because of insufficient funds.");
			}
		} else { // If item is not in the marketplace
			System.out.println("**LOG: The item " + item.getName() + " is not available in the " + marketplace.getName() + ".");
		}

	}

	private float getRebate(){
		return (float) (0.05);
	}
}
