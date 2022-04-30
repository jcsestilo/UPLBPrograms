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
public class Baby {

	String name;
	String sex;
	float weight;
	int hungerLevel = 50;
	int stenchLevel = 20;
	int poopLevel = 0;
	
	// -- add this for problem domain #2
	Nanny[] nannies;  
	static final int MAX_NANNIES = 150;
	int nanniesCounter = 0;
	// --
	
	static int population;

	final static String MALE = "Male";
	final static String FEMALE = "Female";
	
	
	public Baby(String name, String sex, float weight){
		this.name = name; 
		this.sex = sex;
		this.weight = weight;
		Baby.population += 1;
		nannies = new Nanny[Baby.MAX_NANNIES]; //add this for problem domain #2
	}
	
	void play(){
		System.out.println("[Baby " + this.name + " is playing] **Giggle giggle giggle**");
		this.stenchLevel += 10;
		this.hungerLevel += 10;
	}
	
	void poop(){
		System.out.println("[Baby " + this.name + " pooped] Nanny, I have pooped! Prepare the diaper!");
		this.hungerLevel += 10;
		this.stenchLevel += 20;
		this.poopLevel++;
	}
	
	void fedByNanny(Nanny nanny) {
		// if (this.nanny == null) this.nanny = nanny;   ==> remove this for problem domain #2
		if (this.nanniesCounter < MAX_NANNIES) {  // ==> add this for problem domain #2
			this.nannies[this.nanniesCounter++] = nanny;
			System.out.println("[Baby " + this.name + " is eating] **nom nom nom**");
			this.hungerLevel -= 20;
			if (this.hungerLevel <= 0) {
				this.hungerLevel = 0;
				//this.nanny.happinessLevel += 10; ==> remove this for problem domain #2
				nanny.happinessLevel += 10;  // add this for problem domain #2
				System.out.println("Baby is full. Nanny's happiness level increased. :) :)");
			}
		} else System.out.println("Cannot add nanny to list. Baby cannot be fed by nanny.");
	}
	
	void diaperChange() {
		System.out.println("[Baby " + this.name + " : diaper change] **goo goo**");
		this.poopLevel = 0;
		this.stenchLevel = 0;
	}
	
	void viewState(){
		System.out.println("~~~~~~~~~~~~~~ VIEW STATE ~~~~~~~~~~~~~~");
		System.out.println("\t Baby's Name: "+this.name);
		System.out.println("\t Sex: "+this.sex);
		System.out.println("\t Weight: "+this.weight);
		System.out.println("\t Hunger Level: "+this.hungerLevel);
		System.out.println("\t Stench Level: "+this.stenchLevel);
		System.out.println("\t Nannies: ");
		
		//modified for problem domain #2
		for (int i=0; i<nanniesCounter; i++) {
			Nanny n = nannies[i];
			int j = i+1;
			System.out.println("\t\t"+ j + ". " + n.name);
		}
		
		if (nanniesCounter == 0) System.out.println("\t\t Baby does not have a nanny.");
		
		System.out.println("Baby population: "+Baby.population);
		//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("");

	}	
	
}
