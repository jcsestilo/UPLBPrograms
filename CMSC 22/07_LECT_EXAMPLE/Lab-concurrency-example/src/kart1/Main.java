package kart1;

/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading Example: Kart
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
public class Main {
	public static void main(String[] args){
		/**********************************************************************************************
		* If extending Thread class:
		***********************************************************************************************/
		 Kart myKart = new Kart("myKart");
		 Kart yourKart = new Kart("yourKart");
		
		/**********************************************************************************************
		* If implementing Runnable interface:
		***********************************************************************************************/
//		Thread myKart = new Thread(new Kart("myKart"));
//		Thread yourKart = new Thread(new Kart("yourKart"));

		myKart.start();
		yourKart.start();

		//Waits for the two threads to finish before displaying the winner.
		try{
			myKart.join();
			yourKart.join();
		} catch(Exception e){}

		System.out.println("*****" + Kart.getWinner().getKartName() + " WON!*****");
	}
}
