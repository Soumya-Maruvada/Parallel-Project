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
	@Override
	public Wallet findWallet(int walletId) {
		em = JPAManager.createEntityManager();
		Wallet wallet = em.find(Wallet.class, walletId);
		JPAManager.closeResources(em);
		return wallet;
	}
	@Override
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
		
	}

}
