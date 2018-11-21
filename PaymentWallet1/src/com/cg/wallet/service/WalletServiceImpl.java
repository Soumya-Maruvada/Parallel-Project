package com.cg.wallet.service;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Wallet;
import com.cg.wallet.dao.IWalletDao;
import com.cg.wallet.dao.WalletDaoImpl;
import com.cg.wallet.exception.WalletException;
import com.cg.wallet.exception.WalletExceptionmessage;

public class WalletServiceImpl implements IWalletService {
	IWalletDao walletDao = new WalletDaoImpl();
	@Override
	public boolean validate(Customer customer) throws WalletException {
		boolean isValid = true;
		if (!(customer.getFirstName().matches("[a-zA-Z]{3,}")) ) {
			throw new WalletException(WalletExceptionmessage.ERROR1);
		}

		if (!(customer.getLastName().matches("[a-zA-Z]{3,}"))) {
			throw new WalletException(WalletExceptionmessage.ERROR1);

		}
		if (!(customer.getEmail().matches("[a-zA-Z][a-zA-Z0-9-.]*@[a-zA-Z0-9]+([.][a-zA-Z)]+)+"))) {
			throw new WalletException(WalletExceptionmessage.ERROR1);
		}
		if (!(customer.getMobile().toString().matches("^[6-9][0-9]{9}"))) {
			throw new WalletException(WalletExceptionmessage.ERROR1);
		}
		
		return isValid;
	}

	@Override
	public boolean createWallet(Wallet wallet) throws Exception {
		return walletDao.createWallet(wallet);	
	}

	@Override
	public Wallet showBalance(int walletId) throws Exception {
		return walletDao.showBalance(walletId);
	}

	@Override
	public boolean deposit(Wallet wallet, double depositAmt, int walletId) throws Exception {
		wallet.setBalance(wallet.getBalance() + depositAmt);
		return walletDao.deposit(wallet,depositAmt,);
	}

	@Override
	public boolean withdraw(Wallet wallet, double withdrawAmt, int walletId) throws Exception {
		wallet.setBalance(wallet.getBalance() - withdrawAmt);
		return walletDao.updateWallet(wallet);
	}

	@Override
	public boolean fundTransfer(Wallet walletSorce, Wallet walletTareget, double transfer) throws Exception {
		walletSorce.setBalance(walletSorce.getBalance()-transfer);
		walletTareget.setBalance(walletTareget.getBalance()+transfer);
		
		boolean result1=walletDao.updateWallet(walletSorce);
		boolean result2=walletDao.updateWallet(walletTareget);
		return result1 && result2;
	}
}
