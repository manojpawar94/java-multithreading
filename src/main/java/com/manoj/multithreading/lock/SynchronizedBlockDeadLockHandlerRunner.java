package com.manoj.multithreading.lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.manoj.multithreading.utils.ThreadUtils;

public class SynchronizedBlockDeadLockHandlerRunner {
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String ACQUIRRED_LOCK = "{} acquirred lock on {} for 10 ms...";
	private static final String TRYING_TO_ACQUIRRE_LOCK = "{} trying to acquire lock on {}...";
	private static final String RELEASED_LOCK = "{} released lock of {} after 10 ms...";
	private Object lock1;
	private Object lock2;
	private Object lock3;

	public void runDeadLock() {
		lock1 = new Object();
		lock2 = new Object();
		lock3 = new Object();

		var threadOne = new ThreadOne();
		var threadTwo = new ThreadTwo();
		threadOne.setName("threadOne");
		threadTwo.setName("threadTwo");
		threadOne.start();
		threadTwo.start();

		try {
			threadOne.join();
			threadTwo.join();
		} catch (InterruptedException e) {
			LOGGER.warn(ThreadUtils.INTERRUPTED, e);
			// Restore interrupted state...
			Thread.currentThread().interrupt();
		}

	}

	private class ThreadOne extends Thread {

		@Override
		public void run() {
			synchronized (lock1) {
				LOGGER.info(ACQUIRRED_LOCK, this.getName(), lock1);
				try {
					lock1.wait(10);
				} catch (InterruptedException e) {
					LOGGER.warn(ThreadUtils.INTERRUPTED, e);
					// Restore interrupted state...
					Thread.currentThread().interrupt();
				}
				LOGGER.info(TRYING_TO_ACQUIRRE_LOCK, this.getName(), lock2);
				synchronized (lock2) {
					LOGGER.info(ACQUIRRED_LOCK, this.getName(), lock2);
					try {
						lock2.wait(10);
					} catch (InterruptedException e) {
						LOGGER.warn(ThreadUtils.INTERRUPTED, e);
						// Restore interrupted state...
						Thread.currentThread().interrupt();
					}
				}
				LOGGER.info(RELEASED_LOCK, this.getName(), lock2);
			}
			LOGGER.info(RELEASED_LOCK, this.getName(), lock1);
		}
	}

	private class ThreadTwo extends Thread {

		@Override
		public void run() {
			synchronized (lock2) {
				LOGGER.info(ACQUIRRED_LOCK, this.getName(), lock2);
				try {
					lock2.wait(10);
				} catch (InterruptedException e) {
					LOGGER.warn(ThreadUtils.INTERRUPTED, e);
					// Restore interrupted state...
					Thread.currentThread().interrupt();
				}
				LOGGER.info(TRYING_TO_ACQUIRRE_LOCK, this.getName(), lock3);
				synchronized (lock3) {
					LOGGER.info(ACQUIRRED_LOCK, this.getName(), lock3);
					try {
						lock3.wait(10);
					} catch (InterruptedException e) {
						LOGGER.warn(ThreadUtils.INTERRUPTED, e);
						// Restore interrupted state...
						Thread.currentThread().interrupt();
					}
				}
				LOGGER.info(RELEASED_LOCK, this.getName(), lock3);
			}
			LOGGER.info(RELEASED_LOCK, this.getName(), lock2);
		}
	}
}
