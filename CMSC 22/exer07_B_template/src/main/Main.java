/**
 * @author Jan Coleen Estilo
 * Made Nov. 9,2021 at 23:00
 * Exercise 7
 */

package main;
import java.util.ArrayList;

import bankinternal.Bank;
import bankinternal.BankAccount;

/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading exercise
 *
 * (c) Institute of Computer Science, CAS, UPLB
 *
 *
 *************************************************************************************************************************/
public class Main {
	public static void main(String[] args){



		ArrayList<Thread> tarr = new ArrayList<Thread>();

		Bank bank = new Bank();


			/****
			 * Item #1:
			 * Create threads; start each thread
			 */

		Thread storeAcc1 = bank.getAccountAsThread(0);
		Thread suppAcc1 = bank.getAccountAsThread(1);
		storeAcc1.start();
		suppAcc1.start();

		// Adding the threads to the arraylist
		tarr.add(storeAcc1);
		tarr.add(suppAcc1);

		/****
		 * Item #9:
		 * Make sure all threads have finished before printing bank accounts' balance
		 */

		try{
			tarr.get(0).join();
			tarr.get(1).join();

		} catch(Exception e){}

		//

		bank.printBalance();

	}
}
