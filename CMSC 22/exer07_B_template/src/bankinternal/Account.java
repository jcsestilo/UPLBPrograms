package bankinternal;
/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading exercise
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * 
 *
 *************************************************************************************************************************/

public interface Account extends Runnable{
	String getName();
	int getBalance();
	void printBalance();
	BankAccount getAccount();
}
