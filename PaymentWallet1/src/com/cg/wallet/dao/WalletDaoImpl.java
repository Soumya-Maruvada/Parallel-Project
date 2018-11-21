package com.cg.wallet.dao;

import javax.persistence.EntityManager;

import com.cg.wallet.bean.Wallet;

public class WalletDaoImpl implements IWalletDao {
	EntityManager em;
	@Override
	public boolean createWallet(Wallet wallet) {
		try {
			em = JPAManager.createEntityManager();
			em.getTransaction().begin();
			em.persist(wallet);
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/*@Override
	public Wallet findWallet(int walletId) {
		em = JPAManager.createEntityManager();
		Wallet wallet = em.find(Wallet.class, walletId);
		JPAManager.closeResources(em);
		return wallet;
	}*/
	/*@Override
	public boolean updateWallet(Wallet wallet) {
		try{
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			em.merge(wallet);
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		}catch(Exception e){
			return false;
		}
		//------------------------------------------------------------
	}*/
	@Override
	public Wallet showBalance(int walletId) throws Exception {
		em = JPAManager.createEntityManager();
		Wallet wallet = em.find(Wallet.class, walletId);
		JPAManager.closeResources(em);
		return wallet;
	}
	@Override
	public boolean deposit(Wallet wallet, double depositAmt, int walletId) throws Exception {
		em = JPAManager.createEntityManager();
		Wallet wallet1 = em.find(Wallet.class, walletId);
		JPAManager.closeResources(em);
		try{
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			em.merge(wallet1);
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	@Override
	public boolean withdraw(Wallet wallet, double withdrawAmt, int walletId) throws Exception {
		em = JPAManager.createEntityManager();
		Wallet wallet1 = em.find(Wallet.class, walletId);
		JPAManager.closeResources(em);
		try{
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			em.merge(wallet1);
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	@Override
	public boolean fundTransfer(Wallet walletSorce, Wallet walletTareget,
			double transfer) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
