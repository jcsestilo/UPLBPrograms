/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading Example: Bank
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * @author Miyah Queliste
 *
 *************************************************************************************************************************/
public class AccountOwner implements Runnable {
	private BankAccount account;
	private String name;

	public AccountOwner(BankAccount account, String name){
		this.account = account;
		this.name = name;
	}

	public void run(){
		for(int i=0; i<100; i++){
			this.account.withdraw();
			System.out.println(this.name + " withdrew money");
		}
	}

}

