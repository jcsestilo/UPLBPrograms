/**
 * @author Jan Coleen S. Estilo
 * Made Oct. 21, 2021 at 16:00
 * Exercise 5
 */

package marketplace;

public class Marketplace extends Shop{
	// Attributes of Marketplace is seen in Shop

	// Constructor
	public Marketplace(String name){
		super(name); // Refers to the superclass Shop
	}

	// Methods
	public String getName(){ // Getter of marketplace name
		return this.name;
	}

	protected float getSales(){
		return this.sales;
	}

	public void add(Product car){
		/* itemCounter's default is 0. Will add the car object to the items array.*/
		this.items[this.itemCounter] = car;
		this.itemCounter++;
	}

	protected void remove(Product item){
		// Create a temporary array to store the new array
		Product[] tempArray = new Product[MAX_PRODUCTS];

		for(int i=0, j=0; i<this.itemCounter; i++){
			Product product = this.items[i]; /* This does not pertain to the passed Product "item". This pertains to
			 					the "items" array of marketplace*/
			if(product == item){ // If the index encountered the item, continue to the next loop
				continue;
			} else{
				tempArray[j] = product; // Put the product to tempArray
				j++; // Increment j (the counter for tempArray)
			}
		}

		// Put the tempArray to the items array of marketplace
		this.items = tempArray;
		this.itemCounter--;
	}

	/* This returns true if the product is on the list, else false if the product is not found. */
	public boolean find(Product car){
		for(int i=0; i<this.itemCounter; i++){
			Product prod = this.items[i];
			if(prod == car){ // If the product is equal to the car, return true.
				return true;
			}
		}
		// If the loop ends and it does not return true, will return false.
		return false;
	}

	/*
     * This method
     * (1) removes the item from the shop list,
     * (2) updates the demand of all products,
     * (3) update the sales of the shop
     */
	public void transact(Product item){
		// (1) removes the item from the shop list
		this.remove(item);

		// (3) update the sales of the shop
		// new sales will be the previous sales plus the price of the item
		this.sales = this.sales + item.getPrice();

		// (2) updates the demand of all products
		this.updateDemand(item);
	}

	// Additional method
	private void updateDemand(Product item){
		for(int i=0; i<this.itemCounter; i++){ // Will loop through the whole items array
			Product product = this.items[i];

			if(product.getClassification().equals(item.getClassification())){ // If the classification of the item is the same as the passed item (parameter)

				// Set that item's demand by 3
				int newDemand = product.getDemand() + 3;
				product.setDemand(newDemand);

				/** If the current demand point reaches at least 10, the item’s current price is reduced as follows: 15% less
				if the condition is good, 10% less if the condition is mint, and 5% less if the condition is like-new. The demand
				is reduced by 10 should the price of the item updates.
				*/
				if(product.getDemand() >= 10){
					float oldPrice = product.getPrice(); // get the old price (for printing)

					if(product.getCondition().equals(Car.COND_GOOD)){ // If the product's condition is GOOD
						// 15% less on the price
						float percentage = (float) (product.getPrice() * 0.15);
						float newPrice = product.getPrice() - percentage;

						product.setPrice(newPrice);

						// Print the sale
						System.out.println("**LOG: SALE! Get "+ product.getBrand() + " " + product.getModel() + "at 15% off in " + product.getCondition()+ "condition. From "+oldPrice+" to "+product.getPrice()+" only!");
					} else if (product.getCondition().equals(Car.COND_MINT)){ // If the product's condition is GOOD
						// 10% less on the price
						float percentage = (float) (product.getPrice() * 0.1);
						float newPrice = product.getPrice() - percentage;

						product.setPrice(newPrice);

						// Print the sale
						System.out.println("**LOG: SALE! Get "+ product.getBrand() + " " + product.getModel() + "at 15% off in " + product.getCondition()+ "condition. From "+oldPrice+" to "+product.getPrice()+" only!");
					} else { // If the product's condition is LIKE NEW
						// 5% less on the price
						float percentage = (float) (product.getPrice() * 0.05);
						float newPrice = product.getPrice() - percentage;

						product.setPrice(newPrice);

						// Print the sale
						System.out.println("**LOG: SALE! Get "+ product.getBrand() + " " + product.getModel() + "at 15% off in " + product.getCondition()+ "condition. From "+oldPrice+" to "+product.getPrice()+" only!");

					}

					// The demand is reduced by 10 should the price of the item updates
					int reducedDemand = product.getDemand() - 10;
					product.setDemand(reducedDemand);
				}
			}
		}
	}

	public void viewProducts(){
		System.out.println("\t"+this.getName() + " Product List:\n");
		for(int i=0; i<this.itemCounter; i++){
			Product item = this.items[i];

			System.out.println("\tItem "+(i+1)+":");
			System.out.println("\tBrand: "+ item.getBrand());
			System.out.println("\tModel: "+ item.getModel());
			System.out.println("\tPrice: "+ item.getPrice());
			System.out.println("\tCondition: "+ item.getCondition());
			System.out.println("\tClassification: "+ item.getClassification());
			System.out.println("\tDemand Points: "+ item.getDemand());
			System.out.println();
		}
	}

	public void viewInfo(){
		System.out.println("\n==========[ MARKETPLACE STATE ]==========");
		System.out.println("\tMarketplace: " + this.getName());
		System.out.println("\tTotal income: " + this.getSales());
		System.out.println("\tTotal items: " + this.itemCounter);
		System.out.println("=========================================");
	}
}
