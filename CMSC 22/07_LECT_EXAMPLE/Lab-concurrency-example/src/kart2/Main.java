package kart2;

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
		Kart hisKart = new Kart("hisKart");
		Kart herKart = new Kart("herKart");
		Kart aKart = new Kart("aKart");
		Kart bKart = new Kart("bKart");
		Kart cKart = new Kart("cKart");
		
		/**********************************************************************************************
		* If implementing Runnable interface:
		***********************************************************************************************/
		// Thread myKart = new Thread(new Kart("myKart"));
		// Thread yourKart = new Thread(new Kart("yourKart"));

		myKart.start();
		yourKart.start();
		hisKart.start();
		herKart.start();
		aKart.start();
		bKart.start();
		cKart.start();

		//Waits for the two threads to finish before displaying the winner.
		try{
			myKart.join();
			yourKart.join();
			hisKart.join();
			herKart.join();
			aKart.join();
			bKart.join();
			cKart.join();
		} catch(Exception e){}

		System.out.println("*****" + Kart.getWinner().getKartName() + " WON!*****");
	}
}
