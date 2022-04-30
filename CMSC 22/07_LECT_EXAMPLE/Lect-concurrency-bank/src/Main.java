/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading Example: Bank
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
public class Main {
	public static void main(String[] args){
		
		BankAccount account = new BankAccount(2000);
		
		// If extending Thread class:
		// AccountOwner wife = new AccountOwner(account);
		// AccountOwner husband = new AccountOwner(account);

		// If implementing Runnable interface:
		Thread wife = new Thread(new AccountOwner(account, "Wife"));
		Thread husband = new Thread(new AccountOwner(account, "Husband"));

		wife.start();
		husband.start();

		try{
			wife.join();
			husband.join();
		} catch(Exception e){}

		System.out.println("Bank account balance: "+ account.getBalance());
		
	}
}
