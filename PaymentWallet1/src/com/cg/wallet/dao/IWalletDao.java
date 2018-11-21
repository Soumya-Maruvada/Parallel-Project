package com.cg.wallet.dao;

import com.cg.wallet.bean.Wallet;

public interface IWalletDao {

	public boolean createWallet(Wallet wallet);

	//public Wallet findWallet(int walletId);

	//public boolean updateWallet(Wallet wallet);

	
	public Wallet showBalance(int walletId) throws Exception;

	public boolean deposit(Wallet wallet, double depositAmt, int walletId) throws Exception;

	public boolean withdraw(Wallet wallet, double withdrawAmt, int walletId) throws Exception;

	public boolean fundTransfer(Wallet walletSorce, Wallet walletTareget, double transfer) throws Exception;
}
