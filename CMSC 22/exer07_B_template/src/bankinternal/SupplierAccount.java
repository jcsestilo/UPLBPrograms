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
public class SupplierAccount implements Account {
	
	private BankAccount account;
	private String name;
	private int amountToPay = 1500;
	private Bank bank;

	public SupplierAccount(Bank b, BankAccount account, String name){
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
	
	int getAmountToPay(){
		return this.amountToPay;
	}

	public void run(){
		/**
		 * Item#5a
		 *
		 * Do this 2 times:
		 * using bank, request for payment
		 * - use variable this.amountToPay
		 * - put interval (or sleep) of 3 sec
		 */
		for(int i=0; i<2; i++){
			Bank b = this.bank;
			b.requestPayment(this, this.amountToPay, this.getName());
			
			try{ // put interval (or sleep) of 3 seconds
				Thread.sleep(3000);
			} catch(InterruptedException e){}
		}
	}

}

