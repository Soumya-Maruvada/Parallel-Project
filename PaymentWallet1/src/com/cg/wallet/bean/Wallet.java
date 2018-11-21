package com.cg.wallet.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "Wallet2")
public class Wallet {
	@Id
	private int walletId;
	
	private double balance;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfOpenning;
	
	private double initialDeposit;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<WalletTransaction> allTransactions;
	
	
	public List<WalletTransaction> getAllTransactions() {
		return allTransactions;
	}
	public void setAllTransactions(List<WalletTransaction> allTransactions) {
		this.allTransactions = allTransactions;
	}
	public int getWalletId() {
		return walletId;
	}
	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getDateOfOpenning() {
		return dateOfOpenning;
	}
	public void setDateOfOpenning(Date dateOfOpenning) {
		this.dateOfOpenning = dateOfOpenning;
	}
	public double getInitialDeposit() {
		return initialDeposit;
	}
	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}
	
	public Wallet(int walletId, double balance, Customer customer,
			Date dateOfOpenning, double initialDeposit) {
		super();
		this.walletId = walletId;
		this.balance = balance;
		this.customer = customer;
		this.dateOfOpenning = dateOfOpenning;
		this.initialDeposit = initialDeposit;
	}
	public Wallet() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + walletId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		if (walletId != other.walletId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", balance=" + balance
				+ ", customer=" + customer + ", dateOfOpenning="
				+ dateOfOpenning + ", initialDeposit=" + initialDeposit + "]";
	}
	public void addTransation(WalletTransaction walletTransaction) {
		this.allTransactions.add(walletTransaction);
		
	}
	
	
	
}
