package everwing;

public class Clover extends Item {

	
	public Clover() {
		
		super(Item.CLOVER);

	}
	
	void affect(Guardian g) {
		// change g's bullet type
		g.setBulletType(Bullet.UPGRADED_DAMAGE);
		this.used = true;
	}
	
	
}
