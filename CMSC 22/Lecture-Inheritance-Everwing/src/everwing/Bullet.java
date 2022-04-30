package everwing;

public class Bullet {

	// instance attributes
	private int type; 		// 0 - ordinary, 1 - upgraded
	private int damage;

	// constants: avoid magic numbers!
	public final static int ORDINARY_BULLET = 0;
	public final static int UPGRADED_BULLET = 1;
	public final static int ORDINARY_DAMAGE = 10;
	public final static int UPGRADED_DAMAGE = 20;

	
	public Bullet(int type) {
		this.type = type;
		
		if (type == ORDINARY_BULLET) this.damage = ORDINARY_DAMAGE;
		else this.damage = UPGRADED_DAMAGE;
	}
	
	public void viewState(){
		System.out.println("\nBullet");
		System.out.println("type: "+this.type);
		System.out.println("damage: "+this.damage);
	}

	public int getDamage() {
		return damage;
	}
	
	
}
