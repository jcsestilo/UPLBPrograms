
public class Bullet {

	// instance attributes
	int type; 		// 0 - ordinary, 1 - upgraded
	int damage;

	// constants: avoid magic numbers!
	final static int ORDINARY_BULLET = 0;
	final static int UPGRADED_BULLET = 1;
	final static int ORDINARY_DAMAGE = 10;
	final static int UPGRADED_DAMAGE = 20;

	
	Bullet(int type) {
		this.type = type;
		
		if (type == ORDINARY_BULLET) this.damage = ORDINARY_DAMAGE;
		else this.damage = UPGRADED_DAMAGE;
	}
	
	void viewState(){
		System.out.println("\nBullet");
		System.out.println("type: "+this.type);
		System.out.println("damage: "+this.damage);
	}
	
	
}
