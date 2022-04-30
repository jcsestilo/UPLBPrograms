/**
 * @author Jan Coleen S. Estilo
 * Made Oct. 21, 2021 at 16:00
 * Exercise 5
 */

package marketplace;

public class Car extends Product{

	// Attributes

	// Constants
	// Condition Constants
	public static final String COND_LIKE_NEW = "like new";
	public static final String COND_GOOD = "good";
	public static final String COND_MINT = "mint";
	// Class Constants
	public static final String CLASS_SUV = "suv";
	public static final String CLASS_CROSSOVER = "crossover";
	public static final String CLASS_SEDAN = "sedan";

	// Constructor
	public Car(String brand, String model, float price, String condition, String classification){
		super(brand, model, price, condition, classification); // Refer to the superclass Product
	}

	// Methods
	protected void setPrice(float price){ // Marketplace calls this function and sets the new price of the product
		this.price = price;
	}

	protected void setDemand(int demand){
		this.demand = demand;
	}
}
