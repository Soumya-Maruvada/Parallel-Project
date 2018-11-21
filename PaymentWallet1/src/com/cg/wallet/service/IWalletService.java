package com.cg.wallet.service;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Wallet;
import com.cg.wallet.exception.WalletException;

public interface IWalletService {
	
	public boolean validate(Customer customer) throws WalletException;

	public boolean createWallet(Wallet wallet) throws Exception;

	public Wallet showBalance(int walletId) throws Exception;

	public boolean deposit(Wallet wallet, double depositAmt, int walletId) throws Exception;

	public boolean withdraw(Wallet wallet, double withdrawAmt, int walletId) throws Exception;

	public boolean fundTransfer(Wallet walletSorce, Wallet walletTareget, double transfer) throws Exception;
}
