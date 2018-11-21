package com.cg.wallet.ui;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Wallet;
import com.cg.wallet.bean.WalletTransaction;
import com.cg.wallet.service.IWalletService;
import com.cg.wallet.service.WalletServiceImpl;

public class Client {
	static Scanner scanner = new Scanner(System.in);
	IWalletService walletService = new WalletServiceImpl();
	Customer customerBean = new Customer();
	public static void main(String[] args) throws Exception {
		
		Client client = new Client();
		while (true) {
			System.out.println("========Payment wallet application========");
			System.out.println("1. Create Account ");
			System.out.println("2. Show Balance ");
			System.out.println("3. Deposit ");
			System.out.println("4. Withdraw ");
			System.out.println("5. Fund Transfer ");
			System.out.println("6. Print Transactions ");
			System.out.println("7. Exit ");
			System.out.println(" Choose an option ");
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				client.createAccount();
				break;
			case 2:
				client.showBalance();
				break;
			case 3:
				client.deposit();
				break;
			case 4:
				client.withdraw();
				break;
			case 5:
				client.fundTransfer();
				break;
			case 6:
				client.printTransaction();
				break;
			case 7:
				System.exit(0);
				break;
			default:
				System.err.println("Invalid Option Choose from 1 to 7");
				System.out.println();
				break;

			}
	}
	}
	void createAccount() throws Exception {
		
		
		Customer customer = new Customer();
		
		System.out.print("\t\t Enter First name\t\t: ");
		String firstName = scanner.next();
		customer.setFirstName(firstName);
		
		System.out.print("\t\tEnter Last name\t\t: ");
		String lastName = scanner.next();
		customer.setLastName(lastName);
		
		System.out.print("\t\tEnter mobile number\t\t: ");
		BigInteger mobile = scanner.nextBigInteger();
		customer.setMobile(mobile);
		
		System.out.print("\t\tEnter customer email\t\t: ");
		String email = scanner.next();
		customer.setEmail(email);
		
		System.out.print("\t\tSet your password\t\t:");
		String password = scanner.next();
		customer.setPassWord(password);
		
		
		Wallet wallet = new Wallet();
		System.out.print("\t\tEnter  Wallet ID\t\t:");
		int walletId=scanner.nextInt();
		wallet.setWalletId(walletId);
		
		System.out.print("\t\tEnter Date of Opening (DD/MM/YYYY)\t\t:");
		String accDateInput=scanner.next();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateOfOpening=sdf.parse(accDateInput);
		wallet.setDateOfOpenning(dateOfOpening);
		System.out.println("\t\tEnter balance to create account\t\t:");
		double balance=scanner.nextDouble();
		wallet.setBalance(balance);
		
		wallet.setCustomer(customer);
		
		
		if ( walletService.validate(customer)) {
			
			boolean create = walletService.createWallet(wallet);
			if(create)
			{
				System.out.println("\n\n\t\tCongratulations Customer account has been created successfully...");
			}
			else
			{
				System.out.println("Enter valid Details");
			}
			
		}
		
	}
	
	
	private void showBalance() throws Exception {
		System.out.print("/t/tEnter Wallet ID/t/t:");
		int walletId=scanner.nextInt();
		
		Wallet wallet=walletService.showBalance(walletId);
		
		if(wallet==null){
			System.out.println("\n\n\t\tAccount Does not exist.....");
			
		}else {
			double balance=wallet.getBalance();
			System.out.println("\t\tCurrent Balance\t\t:" + balance);
		}
		
	}
	private void deposit() throws Exception {
		System.out.print("\t\tEnter Wallet ID\t\t:");
		int walletId=scanner.nextInt();
		
		Wallet wallet=walletService.showBalance(walletId);
		
		if(wallet==null){
			System.out.println("\n\n\t\tAccount Does not exist......");
			return;
		}else {
			System.out.print("\t\tEnter Ammount to deposit\t\t:");
			double depositAmt = scanner.nextDouble();
			
			WalletTransaction walletTransaction=new WalletTransaction();
			walletTransaction.setTransactionType(1);
			walletTransaction.setTransactionDate(new Date());
			walletTransaction.setTransactionAmt(depositAmt);
			walletTransaction.setBeneficiaryAccountBean(null);
			
			wallet.addTransation(walletTransaction);
			boolean deposit = walletService.deposit(wallet, depositAmt);
			if(deposit){
				System.out.println("\n\n\t\tDeposited Money into Account...... ");
			}else{
				System.out.println("\n\n\t\tNOT Deposited Money into Account...... ");
			}
		}
		
	}
	private void withdraw() throws Exception {
		System.out.print("\t\tEnter Wallet ID\t\t:");
		int walletId=scanner.nextInt();
		
		Wallet wallet=walletService.showBalance(walletId);
		
		if(wallet==null){
			System.out.println("\n\n\t\tAccount Does not exist.....");
			return;
		}else {
			System.out.println("\t\tEnter Ammount to Withdraw......");
			double withdrawAmt = scanner.nextDouble();
		
			WalletTransaction walletTransaction=new WalletTransaction();
			walletTransaction.setTransactionType(2);
			walletTransaction.setTransactionDate(new Date());
			walletTransaction.setTransactionAmt(withdrawAmt);
			walletTransaction.setBeneficiaryAccountBean(null);
			
			wallet.addTransation(walletTransaction);
			boolean deposit = walletService.withdraw(wallet, withdrawAmt);
			if(deposit){
				System.out.println("\n\n\t\tMoney Withdrawen from Account..... ");
			}else{
				System.out.println(" \n\n\t\tWithdrawal Failed....... ");
			}
		}
		
	}
	private void printTransaction() throws Exception {
		System.out.println("\t\tEnter Wallet ID\t\t:");
		int walletId=scanner.nextInt();
		Wallet wallet=walletService.showBalance(walletId);
		
		List<WalletTransaction> transactions = wallet.getAllTransactions();
		
		System.out.println(wallet);
		System.out.println(wallet.getCustomer());
		
		System.out.println("------------------------------------------------------------------");
		
		for(WalletTransaction walletTransaction:transactions){
			
			String str="";
			if(walletTransaction.getTransactionType()==1){
				str=str+"DEPOSIT";
			}
			if(walletTransaction.getTransactionType()==2){
				str=str+"WITHDRAW";
			}
			if(walletTransaction.getTransactionType()==3){
				str=str+"FUND TRANSFER";
			}
			
			str=str+"\t\t"+walletTransaction.getTransactionDate();
			
			str=str+"\t\t"+walletTransaction.getTransactionAmt();
			System.out.println(str);
		}
		
		System.out.println("------------------------------------------------------------------");

		
	}
	private void fundTransfer() throws Exception {
		
		System.out.println("\t\tEnter Senders Wallet ID\t\t:");
		int sorceWalletId=scanner.nextInt();
		Wallet walletSorce=walletService.showBalance(sorceWalletId);
		
		
		System.out.println("\t\tEnter Recivers Wallet ID\t\t:");
		int targetWalletId=scanner.nextInt();
		Wallet walletTareget=walletService.showBalance(targetWalletId);
		
		
		if (walletSorce == null) {
			System.out.println("\n\n\t\tSenders wallet not found......");
			return;
		}
		if(walletTareget == null){
			System.out.println("\n\n\t\tReceivers wallet not found......");
			return;
		}else {
			System.out.println("\t\tEnter Ammount to transfer\t\t:");
			double transfer = scanner.nextDouble();
			
			WalletTransaction walletTransaction=new WalletTransaction();
			walletTransaction.setTransactionType(3);
			walletTransaction.setTransactionDate(new Date());
			walletTransaction.setTransactionAmt(transfer);
			walletTransaction.setBeneficiaryAccountBean(null);
			
			walletSorce.addTransation(walletTransaction);
			boolean transferResult = walletService.fundTransfer(walletSorce, walletTareget, transfer);
			if(transferResult){
				System.out.println("\n\n\t\tMoney Transfered from Account...... ");
			}else{
				System.out.println("\n\n\t\t Money Transfer Failed...... ");
			}
		}	
		
		
	}	
	
}
