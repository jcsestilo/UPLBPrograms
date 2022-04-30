package bankinternal;
import java.util.ArrayList;


/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Multithreading exercise
 *
 * (c) Institute of Computer Science, CAS, UPLB
 *
 *
 *************************************************************************************************************************/

public class Bank {

	public static final String supplierName1 = "Supplier Account#1";
	public static final String storeName1 = "Store Account#1";

	SupplierAccount supplier1;
	StoreAccount storeAccount1;

	ArrayList<Account> acctarr;			// keep a list of all bank accounts, regardless of type

	public Bank(){
		acctarr = new ArrayList<Account>();
		createAccounts();
	}


	void createAccounts() {

		BankAccount account1 = new BankAccount(supplierName1, 1, 2000, this);
		supplier1 = new SupplierAccount(this, account1, supplierName1);
		acctarr.add(supplier1);

		BankAccount account3 = new BankAccount(storeName1, 3, 2000, this);
		storeAccount1 = new StoreAccount(this, account3, storeName1);
		acctarr.add(storeAccount1);

	}

	Account getAccount(int i) {
		return acctarr.get(i);
	}

	public Thread getAccountAsThread(int i) {
		return new Thread(this.getAccount(i));

	}

	Account getAccount(String name) {
		for (Account ba: acctarr) {
			if (ba.getName().equals(name)) {
				return ba;
			}
		}
		return null;
	}

	public void processDeposit(StoreAccount store, int amountToDeposit) {
		/****
		 * Item #2b:
		 * process deposit
		 */
		BankAccount b = store.getAccount();
		b.deposit(amountToDeposit);
		//System.out.println("300 deposited to Store Account#1");
	}

	public void processWithdraw(StoreAccount store, int amountToWithdraw) {
		/****
		 * Item #2c:
		 * process withdraw request
		 */
		BankAccount b = store.getAccount();
		// Since withdraw is a boolean method, if the withdraw is successful, nothing will happen
		if(b.withdraw(amountToWithdraw)){}
		// If withdraw() returned false or withdraw was not successful, print insufficient balance
		else { System.out.println("Cannot withdraw from Store Account#1, insufficient balance."); }
	}

	public void requestPayment(SupplierAccount supplier, int amountToPay, String name) {
		/****
		 * Item #5b:
		 * process request of supplier for payment from store account
		 */
		System.out.println(name +" is requesting payment.");

		BankAccount bAccount = storeAccount1.getAccount(); // get the bank account of storeaccount
		bAccount.pay(amountToPay, name);

	}

	public void completePayment(int amountToPay, String supplierName) {
		/****
		 * Item #6b:
		 * this is called from BankAccount class, remit money to supplier account
		 */
		BankAccount bAccount = supplier1.getAccount();
		bAccount.remit(amountToPay);

		System.out.println("Payment completed: "+ amountToPay +" from "+ storeAccount1.getName() +" to "+ supplierName);
	}

	public void printBalance() {

		/****
		 * Item #8:
		 * Display all account balance
		 */
		System.out.println("\n\n============================");
		System.out.println("Bank account information:\n");
		
		for(Account a: this.acctarr){
			BankAccount bAccount = a.getAccount();
			bAccount.printBalance();
			
		}
		
		System.out.println("\n============================");
	}






}
