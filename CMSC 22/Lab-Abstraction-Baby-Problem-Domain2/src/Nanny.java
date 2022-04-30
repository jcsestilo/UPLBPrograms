/*************************************************************************************************************
 * 
 * Topic: Abstraction (Laboratory component)
 * 
 * Problem Domain:
 * A baby has a name, sex, weight, hunger level (initially 50), stench level (initially 20), and poop level 
 * (initially 0). A baby can play, poop, and have the diaper changed.  A baby can be assigned a nanny, and be 
 * fed by a nanny. When a baby plays, the hunger level and stench levels increase by 10. When a baby poops, 
 * the hunger level increases by 10, the stench level increases by 20, and the poop level increases by 1. 
 * 
 * A nanny has a name, sex, age, and happiness level that is set initially to 50.  A nanny can feed a baby. 
 * A nanny can also change baby diapers. 
 * 
 * Baby has a feeding history that keeps a list of nannies: Whenever a nanny feeds a baby, nanny is added to the 
 * list of baby’s nannies.
 * 
 * Nanny has a baby assignment history that keeps a list of baby assignments: Whenever a nanny feeds a baby, the 
 * baby is added to nanny’s list of babies.
 * 
 * When a baby is fed, the baby’s hunger level decreases by 20. After a baby is fed, the hunger level is checked. 
 * When the baby’s hunger level reaches 0 (or below), the nanny's happiness level is increased by 10.  The baby’s 
 * hunger level cannot go below 0. When the value goes below 0, level is set back to 0.
 * 
 * Whenever a  baby’s diaper is changed,  the baby’s stench level and poop level is set to 0. (Any nanny can do 
 * this and there is no need to record the assignment).
 * 
 * The total number of babies and nannies can be tracked. The state of the baby and nanny can be viewed.
 * The list of babies can have up to 150 entries.
 * The list of nannies can have up to 150 entries.
 * 
 * @date 2021-02-16 08:00
 * (c) Institute of Computer Science, UPLB
 * 
 */

public class Nanny {

	String name;
	String sex;
	int age;
	int happinessLevel = 50;
	
	// -- modified for problem domain #2
	//Baby baby;
	Baby[] babies;
	int babyCounter;
	static final int MAX_BABIES = 150;
	// --
	
	static final String FEMALE = "Female";
	static final String MALE = "Male";

	static int population;

	
	Nanny(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		
		Nanny.population++;
		
		this.babies = new Baby[Nanny.MAX_BABIES];
	}
	
	void feed(Baby baby) {
		if (babyCounter < MAX_BABIES) {  // ==> add this for problem domain #2
			System.out.println("Nanny " + this.name + " feeds " + baby.name);
			baby.fedByNanny(this);
			//if (this.baby == null) this.baby = baby; ==> remove this for problem domain #2
			this.babies[this.babyCounter++] = baby;			
		} else System.out.println("Cannot feed baby. Maximum number of babies reached.");
	}
	
	void changeDiaper(Baby baby) {
		if (babyCounter < MAX_BABIES) {  // ==> add this for problem domain #2
			System.out.println("Nanny " + this.name + " changes the diaper of " + baby.name);
			baby.diaperChange();
			this.babies[this.babyCounter++] = baby;			
		} else System.out.println("Cannot add baby to the list. Cannot feed baby.");
	}
	
	void viewState(){
		System.out.println("~~~~~~~~~~~~~~ VIEW STATE ~~~~~~~~~~~~~~");
		System.out.println("\t Nanny's Name: " + this.name);
		System.out.println("\t Sex: " + this.sex);
		System.out.println("\t Age: " + this.age);
		System.out.println("\t Happiness level: " + this.happinessLevel);
		
		//modified for problem domain #2
		//if (this.baby != null ) System.out.println("\t Baby: " + this.baby.name);
		//else System.out.println("\t Nanny has not yet fed any baby.");
		
		//modified for problem domain #2
		System.out.println("\t Babies:");
		for (int i=0; i<babyCounter; i++) {
			Baby b = babies[i];
			int j = i+1;
			System.out.println("\t\t"+ j + ". " + b.name);
		}
		if (babyCounter == 0) System.out.println("\t\t Nanny has not yet fed any baby.");
		
		System.out.println("Nanny population: " + Nanny.population);
		//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("");
	}
}
