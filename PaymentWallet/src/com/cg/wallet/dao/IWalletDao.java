package com.cg.wallet.dao;

import com.cg.wallet.bean.Wallet;

public interface IWalletDao {

	public boolean createWallet(Wallet wallet);

	public Wallet findWallet(int walletId);

	public boolean updateWallet(Wallet wallet);

}
