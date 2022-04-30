/**
 * @author Jan Coleen Estilo
 * Made Nov. 9,2021 at 23:00
 * Exercise 7
 */
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

/**
 * Item#4
 * - Check relevant methods and see which will need the 'synchronized' keyword;
 */

public class BankAccount {
	private int balance;
	private int accountNum;
	private String name;
	private Bank bank;
	private boolean hasPending = false;
	boolean terminatePayment = false;

	BankAccount(String name, int accountNum, int balance, Bank b){
		this.name = name;
		this.accountNum = accountNum;
		this.balance = balance;
		System.out.println(this.name + " Initial bank account balance: " + this.balance);
		this.bank = b;
	}

	boolean getPending(){
		return this.hasPending;
	}
	
	boolean withdraw(int x){

		/**
		 * Item#3
		 * Withdraw:
		 * Check first if there is sufficient amount
		 * If there is sufficient amount, make sure there is no pending payment to make
		 */
		int amountToWithdraw = x; // Putting x to amountToWithdraw for readability
		
		if(this.balance<amountToWithdraw){ // If the balance is less than the amount to withdraw..
			return false;
		} else {
			
			if(this.hasPending && this.balance>=this.bank.supplier1.getAmountToPay()){ // If there is sufficient 
				// amount to withdraw, BUT there is a pending payment AND the current balance is >= amount that needs to be paid
				
				System.out.println("There is sufficient amount to withdraw BUT there is a pending payment!");
				
				if(this.pay(this.bank.supplier1.getAmountToPay(), this.bank.supplier1.getName())){
					System.out.println("Payment successful.");
				} else{
					return false;
				}
				return true;
				
			} 
			
			else {
				// if there is sufficient amount and no pending payment
				this.balance-=amountToWithdraw;
				System.out.println(amountToWithdraw + " withdrawn from Store Account#1");
				return true;
			}
		}

	}
	

	synchronized boolean pay(int amountToPay, String supplierName) {

		/**
		 * Item#6
		 *
		 * Check first if there is sufficient amount
		 * If insufficient amount
		 * - change pending indicator to true
		 */



		if (this.balance >= amountToPay) { // Store is able to pay

			// complete this part
			this.balance -= amountToPay; // Remove the amount to pay from storeaccount's balance
			this.bank.completePayment(amountToPay, supplierName);


			this.hasPending=false;
			return true;
		}
		else {
			this.hasPending = true;


			while(this.hasPending==true){

				System.out.println("Waiting, will try paying: " + amountToPay + " from " + name + " to " + supplierName + "; Has only "+ this.balance);
				try{ 
					wait();
				}
				catch(InterruptedException e){}

			}
			return true;
		}
	}

	void deposit(int amount){
		this.balance+=amount;
		System.out.println(amount + " deposited to " + this.name);
		
	}

	void remit(int amount){
		this.balance+=amount;
		System.out.println(amount + " remitted to " + this.name);
	}

	int getBalance(){
		return this.balance;
	}

	void printBalance(){
		System.out.println(this.name + " has " + this.getBalance());
	}

	void notifyThreads() {

		terminatePayment = true;
	}


}
