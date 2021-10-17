package com.manoj.multithreading.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.manoj.multithreading.utils.ThreadUtils;

public class ReentrantLockDeadLockHandlerRunner {
	private static final Logger LOGGER = LogManager.getLogger();
	private static final Account ACCOUNT_1 = new Account(1001, 10000);
	private static final Account ACCOUNT_2 = new Account(1002, 15000);
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	private static final Random random = new Random();

	public void runner() {
		var bankOffice = new BankOffice();
		var netBanking = new NetBanking();

		bankOffice.start();
		netBanking.start();

		try {
			bankOffice.join();
			netBanking.join();
		} catch (InterruptedException e) {
			LOGGER.warn(ThreadUtils.INTERRUPTED, e);
			// Restore interrupted state...
			Thread.currentThread().interrupt();
		}

	}

	private class BankOffice extends Thread {

		@Override
		public void run() {
			for (var index = 0; index < 1000; index++) {
				ThreadUtils.aquireLocks(lock1, lock2);
				try {
					Account.transfer(ACCOUNT_1, ACCOUNT_2, random.nextInt(ACCOUNT_1.getAccountBalance()));
				} finally {
					ThreadUtils.releaseLocks(lock1, lock2);
				}
			}
		}
	}

	private class NetBanking extends Thread {

		@Override
		public void run() {
			for (var index = 0; index < 1000; index++) {
				ThreadUtils.aquireLocks(lock2, lock1);
				try {
					Account.transfer(ACCOUNT_2, ACCOUNT_1, random.nextInt(ACCOUNT_2.getAccountBalance()));
				} finally {
					ThreadUtils.releaseLocks(lock2, lock1);
				}
			}
		}
	}
}
