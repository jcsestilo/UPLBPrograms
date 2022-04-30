import java.util.Random;

public class Monster {

	// instance attributes
	int type; 		// 0 - red, 1 - green
	int health;

	// constants: avoid magic numbers!
	final static int RED = 0;
	final static int GREEN = 1;
	final static int RED_HEALTH = 10;
	final static int GREEN_HEALTH = 50;
	
	// constructor
	Monster(){
		Random r = new Random();	// instantiate a Random object
		this.type = r.nextInt(2);	// randomize int values from 0-1

		this.health = this.type==Monster.RED?Monster.RED_HEALTH:Monster.GREEN_HEALTH;

		// if(this.type==Monster.RED){
		// 	this.health = Monster.RED_HEALTH;
		// }else{
		// 	this.health = Monster.GREEN_HEALTH;
		// }
	}
	
	void viewState(){
		System.out.println("\nMonster");
		System.out.println("type: "+this.type);
		System.out.println("health: "+this.health);
	}
	
	
}
