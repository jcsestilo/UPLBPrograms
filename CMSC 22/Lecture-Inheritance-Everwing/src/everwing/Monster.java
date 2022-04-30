package everwing;
import java.util.Random;

public class Monster {

	// instance attributes
	private int type; 		// 0 - red, 1 - green
	private int health;

	// constants: avoid magic numbers!
	public final static int RED = 0;
	public final static int GREEN = 1;
	public final static int RED_HEALTH = 10;
	public final static int GREEN_HEALTH = 50;
	
	// constructor
	public Monster(){
		Random r = new Random();	// instantiate a Random object
		this.type = r.nextInt(2);	// randomize int values from 0-1

		this.health = this.type==Monster.RED?Monster.RED_HEALTH:Monster.GREEN_HEALTH;

		// if(this.type==Monster.RED){
		// 	this.health = Monster.RED_HEALTH;
		// }else{
		// 	this.health = Monster.GREEN_HEALTH;
		// }
	}
	
	public void viewState(){
		System.out.println("\nMonster");
		System.out.println("type: "+this.type);
		System.out.println("health: "+this.health);
	}

	void setHealth(int damage) {
		this.health -= damage;
	}
	
	
}
