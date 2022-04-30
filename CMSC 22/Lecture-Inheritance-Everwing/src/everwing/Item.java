package everwing;

public abstract class Item {
	boolean used = false;
	String type;
	
	public final static String GEM = "Gem";
	public final static String CLOVER = "Clover";
	public final static String COIN = "Coin";

	public Item(String type){
		this.type=type;
	}
	
	abstract void affect(Guardian g);

}
