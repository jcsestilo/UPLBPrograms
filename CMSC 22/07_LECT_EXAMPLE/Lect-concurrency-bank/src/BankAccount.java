/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading Example: Bank
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
public class BankAccount {
	private int balance;

	public BankAccount(int balance){ 
		this.balance = balance;
		System.out.println("Initial bank account balance: " + this.balance);
	}

	public void withdraw(){
		this.balance-=10;
	}

	public  int getBalance(){
		return this.balance;
	}

}
