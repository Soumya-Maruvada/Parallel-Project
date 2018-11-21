package com.cg.wallet.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Wallet;
import com.cg.wallet.dao.IWalletDao;
import com.cg.wallet.dao.WalletDaoImpl;
import com.cg.wallet.exception.WalletException;

public class WalletDaoImplTest {
	static IWalletDao walletDao = null;
	static Wallet wallet = null;
	static Customer customer = null;
	@BeforeClass
	public static void createInstance() {
		walletDao = new WalletDaoImpl();
		wallet = new Wallet();
		customer = new Customer();
	}
	@Test
	public void testShowBalanceNegative() throws Exception, WalletException {
		Wallet wTest = walletDao.findWallet(104);
		double bal = wTest.getBalance();
		assertNotSame(null,100, bal);
	}
	@Test
	public void testShowBalance() throws Exception, WalletException {
		Wallet wTest = walletDao.findWallet(104);
		double bal = wTest.getBalance();
		assertEquals(500, bal,0);
	}
}
