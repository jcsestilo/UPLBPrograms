/**
 * @author Jan Coleen Estilo
 * Made Nov. 9,2021 at 23:00
 * Exercise 7
 */

package bankinternal;
import java.util.Random;

/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading exercise
 *
 * (c) Institute of Computer Science, CAS, UPLB
 *
 *
 *************************************************************************************************************************/
public class StoreAccount implements Account {
	private BankAccount account;
	private String name;
	private Bank bank;

	private int bankDepositAmount = 300;
	private int bankWithdrawAmount = 300;


	StoreAccount(Bank b, BankAccount account, String name){
		this.bank = b;
		this.account = account;
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public BankAccount getAccount() {
		return account;
	}

	@Override
	public int getBalance() {
		return this.account.getBalance();
	}

	@Override
	public void printBalance() {
		this.account.printBalance();
	}

	public void run(){
		/**
		 * Item#2a
		 * deposit-withdraw cycle
		 * Do this 50 times:
		 * get a random number (0 or 1);
		 * -if 0, request for deposit of money; use variable this.bankDepositAmount
		 * -if 1, request to process withdrawal; use variable this.bankWithdrawAmount
		 * put interval (or sleep) of 100 ms
		 *
		 */
		Random r = new Random();
		for(int i=0; i<50; i++){
			// Generate a random number from 0 to 1
			int num = r.nextInt(2);
			
			if(num==0){ // if 0, request for deposit of money; use variable this.bankDepositAmount
				this.bank.processDeposit(this, this.bankDepositAmount);
				// Everytime the balance is updated, notify the single thread that is waiting
			} else { // if 1, request to process withdrawal; use variable this.bankWithdrawAmount

				// The store account balance cannot go below 0
				if(this.getBalance() <= this.bankWithdrawAmount){
					// Continue to the next loop if the balance is less than or equal to the amount to withdraw
					continue;
				}

				this.bank.processWithdraw(this, this.bankWithdrawAmount);
			}
			
			System.out.println("StoreAccount has: " + this.getBalance());
			this.notifySingleThread();

			try {

				Thread.sleep(100);
			}catch(InterruptedException e){}
		}
		System.out.println("Store account's deposit-withdraw cycle has ended.");

		// Notify all threads when the deposit-withdraw cycle is finished.
		this.account.notifyThreads();
		
		// IF the (50) cycle is over and payment is still pending, use a boolean variable to indicate that it is time to terminate the payment and therefore notify all waiting threads).
		if(this.account.getPending()==true){
			// notify all
			this.notifyAllThreads();
		}

	}
	
	synchronized void notifySingleThread(){
		notify();
	}
	
	synchronized void notifyAllThreads(){
		notifyAll();
	}


}

