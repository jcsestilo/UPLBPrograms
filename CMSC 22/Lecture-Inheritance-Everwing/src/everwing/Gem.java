package everwing;

public class Gem extends Item {

	
	public Gem() {
		super(Item.GEM);
		
	}
	
	public void affect(Guardian g) {
		g.setScore(10);
		this.used = true;
	}
	
}
