package com.cg.wallet.exception;

public class WalletException extends Exception {
	public WalletException() {
		// TODO Auto-generated constructor stub
		System.out.println("wallet exception raised");
	}

	public WalletException(String args) {
		// TODO Auto-generated constructor stub
		super(args);
	}
}
