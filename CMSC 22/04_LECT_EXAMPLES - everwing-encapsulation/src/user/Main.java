/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Abstraction: Everwing Example
 * Problem Domain: Everwing is a game where the goal is for the guardian to kill monsters by shooting them and collect coins.  
 * When a guardian is created, it is given a name and initially a score of 0. The guardian can move to the left or to the right. 
 * Monsters appear by waves and die when health reaches 0. 
 * When a monster dies, it becomes a coin or item that the guardian can collect. There is the red and the green type monster 
 * with 10 and 50 health respectively.
 * When the guardian hits a monster, the guardian dies.
 * Different items can be collected by the guardian:
 * Item 		Effect
 * Coin 		Adds +1 score and 1 coin
 * Purple Gem 	Adds +10 score and 10 coins
 * Clover 		Increase the player's bullet level by 5 for the rest of the game round
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
package user;	// For the purposes of CMSC 22, Main will frequently (if not always) be in a separate package

import everwing.Guardian;	// import Guardian and Monster so that Main can access them
import everwing.Monster;

public class Main {
	public static void main(String[] args){
		
		System.out.println("\nPopulation: "+Guardian.count());	// call class method through the Classname

		Guardian g1 = new Guardian("Alice");				// instantiate a Guardian object named g1	
		g1.viewState();

		g1.collectCoin();									// call method collectCoin() on g1
		g1.viewState();

		System.out.println("\nPopulation: "+Guardian.count());	// call class method through the Classname

		Monster monster = new Monster();							// instantiate a Monster object; the type of Monster will be randomized in the constructor
		monster.viewState();
		
		g1.shoot(monster);									// call method shoot() on g1; pass the Monster to be shot: monster
		monster.viewState();
	}
}