package marketplace;

/* DO NOT MODIFY THIS FILE */
public abstract class Shop {
    protected static final int MAX_PRODUCTS = 50;
    protected Product[] items;
    protected int itemCounter;

    protected String name;
    protected float sales;

    public Shop(String name) {
        this.name = name;
        this.sales = 0f;
        this.items = new Product[MAX_PRODUCTS];
    }

    /* This accepts a Product and adds it on the product list. */
    protected abstract void add(Product item);

    /* This removes a product from list. */
    protected abstract void remove(Product item);

    /* This returns true if the product is on the list, else false if the product is not found. */
    protected abstract boolean find(Product item);

    /*
     * This method
     * (1) removes the item from the shop list,
     * (2) updates the demand of all products,
     * (3) update the sales of the shop
     */
    protected abstract void transact(Product item);

    /* This prints a listing of items of the shop. */
    protected abstract void viewProducts();

    /* This prints the
     * (1) name of the shop
     * (2) total products in the shop
     * (3) total sales.
     */
    protected abstract void viewInfo();

    /* This is a getter for shop name */
    protected abstract String getName();

    /* This is a getter for shop sales */
    protected abstract float getSales();
}
