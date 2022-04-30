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

public class Main {

	public static void main(String[] args){
		

		Baby boss = new Baby("BOSS BABY",Baby.MALE,8.5f);
		Baby ted = new Baby("TED",Baby.MALE,8.0f);
		Baby mary = new Baby("MARY",Baby.FEMALE,7.0f);
		Baby mikey = new Baby("MIKEY",Baby.MALE,6.0f);
		
		Nanny n1 = new Nanny("GILBERTINE",Nanny.FEMALE,26);
		//modified for problem domain #2
		Nanny n2 = new Nanny("POPPINS",Nanny.FEMALE,27);
		Nanny n3 = new Nanny("MCPHEE",Nanny.MALE,28);
		Nanny n4 = new Nanny("MRS. DOUBTFIRE",Nanny.MALE,29);
		

//		__________________;   //make boss play 
//		__________________;   //make Nanny feed boss

		System.out.println("Show the state of all objects:");
		boss.viewState();
		ted.viewState();
		mary.viewState();
		mikey.viewState();
		n1.viewState();
		n2.viewState();
		n3.viewState();
		n4.viewState();

		System.out.println("Make objects interact with each other:");
		System.out.println();
		n1.feed(boss);
		boss.viewState();

		System.out.println();
		boss.play();
		boss.viewState();
		
		System.out.println();
		ted.play();
		ted.viewState();
		
		System.out.println();
		n2.feed(boss);
		boss.viewState();

		System.out.println();
		n3.feed(boss);
		boss.viewState();

		System.out.println();
		n4.feed(boss);
		boss.viewState();	
		
		System.out.println();
		n4.feed(ted);
		ted.viewState();
		
		System.out.println();
		n4.feed(mary);
		mary.viewState();

		System.out.println();
		n4.feed(mikey);
		mikey.viewState();

		System.out.println();
		System.out.println("Show state of all Nanny objects:");
		n1.viewState();
		n2.viewState();
		n3.viewState();
		n4.viewState();
		
		System.out.println("==== End  =====");
		
	}
	
}
