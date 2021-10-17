package com.manoj.multithreading.lock;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author manojpawar
 *
 */
public class Account {
	private static final Logger LOGGER = LogManager.getLogger();
	private int accountId;
	private int accountBalance;

	/**
	 * @param accountId
	 * @param accountBalance
	 */
	public Account(int accountId, int accountBalance) {
		this.accountId = accountId;
		this.accountBalance = accountBalance;
	}

	/**
	 * @return the accountId
	 */
	public int getAccountId() {
		return accountId;
	}

	/**
	 * @return the accountBalance
	 */
	public int getAccountBalance() {
		return accountBalance;
	}

	/**
	 * 
	 * @param amount
	 */
	public void deposit(int amount) {
		accountBalance += amount;
	}

	/**
	 * 
	 * @param amount
	 */
	public void withdraw(int amount) {
		accountBalance -= amount;
	}

	/**
	 * 
	 * @param acc1
	 * @param acc2
	 * @param amount
	 */
	public static void transfer(Account acc1, Account acc2, int amount) {
		UUID transactionId = UUID.randomUUID();
		LOGGER.info("Initiating transaction id {}", transactionId);
		acc1.withdraw(amount);
		acc2.deposit(amount);
		LOGGER.info("\nTransactionId: {}\nAmount: {}\nFrom Account No: {}\nTo Account No: {}", transactionId, amount,
				acc1.getAccountId(), acc2.getAccountId());
		LOGGER.info("Completed transaction id {}", transactionId);
		LOGGER.info("{}::{}", transactionId, acc1);
		LOGGER.info("{}::{}", transactionId, acc2);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [accountId=").append(accountId).append(", accountBalance=").append(accountBalance)
				.append("]");
		return builder.toString();
	}

}
