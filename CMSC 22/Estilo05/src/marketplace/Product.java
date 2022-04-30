package marketplace;

/* DO NOT MODIFY THIS FILE */
public abstract class Product {
	protected String brand;
	protected String model;
	protected float price;
	protected int demand;
    protected String condition;
	protected String classification;

	public Product(String brand, String model, float price, String condition, String classification) {
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.condition = condition;
		this.classification = classification;
		this.demand = 3;
	}

	public String getName() {
		return this.brand + " " + this.model;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getModel() {
		return this.model;
	}

	public float getPrice() {
		return this.price;
	}

	public int getDemand() {
		return this.demand;
	}

	public String getCondition() {
		return this.condition;
	}

	public String getClassification() {
		return this.classification;
	}

	protected abstract void setPrice(float price);

	protected abstract void setDemand(int demand);
	
	public void viewInfo(){
		System.out.println("\tBrand: " + this.getBrand());
		System.out.println("\tModel: " + this.getModel());
		System.out.println("\tPrice: " + this.getPrice());
		System.out.println("\tCondition: " + this.getCondition());
		System.out.println("\tClassification: " + this.getClassification());
		System.out.println("\tDemand Points: " + this.getDemand());
	}
}