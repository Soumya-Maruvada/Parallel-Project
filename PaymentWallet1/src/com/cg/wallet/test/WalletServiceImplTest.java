package com.cg.wallet.test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Wallet;
import com.cg.wallet.dao.IWalletDao;
import com.cg.wallet.dao.WalletDaoImpl;
import com.cg.wallet.exception.WalletException;
import com.cg.wallet.service.IWalletService;
import com.cg.wallet.service.WalletServiceImpl;

public class WalletServiceImplTest {

	Date dateOfOpening;
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	static IWalletService walletService = null;
	static IWalletDao walletDao = null;
	static Wallet wallet = null;
	static Customer customer = null;
	@BeforeClass
	public static void createInstance() {
		walletService = new WalletServiceImpl();
		walletDao = new WalletDaoImpl();
		wallet = new Wallet();
		customer = new Customer();
	}
	@Test
	public void testforAccountCreationNegetive() throws Exception {
		wallet.setWalletId(107);
		wallet.setBalance(500.0);
		wallet.setInitialDeposit(0.0);
		wallet.setDateOfOpenning(new Date(19/11/2018));
		customer.setFirstName("Yogesh");
		customer.setLastName("Mapari");
		customer.setEmail("yogesh@gmail.com");
		customer.setMobile(new BigInteger("8965412376"));
		customer.setId(6);
		customer.setPassWord("mapari");
		wallet.setCustomer(customer);
		
		boolean valid = walletService.validate(customer);
		if (valid == true) {
			valid = walletService.createWallet(wallet);
		}else {
			valid = false;
		}
		assertFalse(valid);
	}
	
	//show balance
	@Test
	public void testShowBalance() throws Exception, WalletException {
		Wallet wTest = walletService.showBalance(104);
		double bal = wTest.getBalance();
		assertEquals(500, bal,0);
	}
	@Test
	public void testShowBalanceNegative() throws Exception, WalletException {
		Wallet wTest = walletService.showBalance(104);
		double bal = wTest.getBalance();
		assertNotSame(null,100, bal);
	}
	
	//deposite
	
	@Test
	public void testDeposite() throws Exception, WalletException {
		wallet = walletDao.findWallet(106);
		
		boolean wTest = walletService.deposit(wallet, 5000);
		//double bal = wTest.getBalance();
		assertTrue(wTest);
	}
		
	@Test
	public void testDepositeEquals() throws Exception, WalletException {
		
		
		wallet = walletDao.findWallet(106);
		
		boolean wTest = walletService.deposit(wallet, 500);
		double bal = wallet.getBalance();
		assertEquals(7000, bal, 0);
	}
	
	@Test
	public void testDepositeNotEquals() throws Exception, WalletException {
		
		
		wallet = walletDao.findWallet(106);
		
		boolean wTest = walletService.deposit(wallet, 500);
		double bal = wallet.getBalance();
		assertNotEquals(7000, bal, 0);
	}
	// withdraw
	
	@Test
	public void testWithdraw() throws Exception, WalletException {
		wallet = walletDao.findWallet(106);
		
		boolean wTest = walletService.withdraw(wallet, 5500);
		//double bal = wTest.getBalance();
		assertTrue(wTest);
	}
	
	@Test
	public void testwithdrawEquals() throws Exception, WalletException {
		
		wallet = walletDao.findWallet(106);
		
		boolean wTest = walletService.withdraw(wallet, 500);
		double bal = wallet.getBalance();
		assertEquals(3500, bal, 0);
	}
	
	@Test
	public void testwithdrawNotEquals() throws Exception, WalletException {
		
		wallet = walletDao.findWallet(106);
		
		boolean wTest = walletService.withdraw(wallet, 500);
		double bal = wallet.getBalance();
		assertNotEquals(3500, bal, 0);
	}
	
	//fund Transfer
	@Test
	public void testFundTransfer() throws Exception, WalletException {
		wallet = walletDao.findWallet(106);
		Wallet walletTarget = walletDao.findWallet(105);
		boolean wTest = walletService.fundTransfer(wallet,walletTarget,500);
		//double bal = wTest.getBalance();
		assertTrue(wTest);
	}
	
	@Test
	public void testFundTransferEqualsSender() throws Exception, WalletException {
		
		wallet = walletDao.findWallet(105);
		Wallet walletTarget = walletDao.findWallet(106);
		boolean wTest = walletService.fundTransfer(wallet, walletTarget, 500);
		double bal = wallet.getBalance();
		assertEquals(5500, bal, 0);
	}
	@Test
	public void testFundTransferEqualsReceiver() throws Exception, WalletException {
		
		wallet = walletDao.findWallet(105);
		Wallet walletTarget = walletDao.findWallet(106);
		boolean wTest = walletService.withdraw(wallet, 500);
		double bal = walletTarget.getBalance();
		assertEquals(3500, bal, 0);
	}
	
	@Test
	public void testFundTransferNotEquals() throws Exception, WalletException {
		
		wallet = walletDao.findWallet(106);
		
		boolean wTest = walletService.withdraw(wallet, 500);
		double bal = wallet.getBalance();
		assertNotEquals(3500, bal, 0);
	}
	

}
