package everwing;

public class Coin extends Item{
	
	
	public Coin() {
		
		super(Item.COIN);

	}
	
	void affect(Guardian g) {
		g.setScore(1);
		this.used = true;
	}
	

}
