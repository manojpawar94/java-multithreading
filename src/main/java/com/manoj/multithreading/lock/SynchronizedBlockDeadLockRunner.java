package com.manoj.multithreading.lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.manoj.multithreading.utils.ThreadUtils;

public class SynchronizedBlockDeadLockRunner {
	private static final Logger LOGGER = LogManager.getLogger();
	private Object lock1;
	private Object lock2;

	public void runDeadLock() {
		lock1 = new Object();
		lock2 = new Object();

		var threadOne = new ThreadOne();
		var threadTwo = new ThreadTwo();

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
				LOGGER.info("{} acquirred lock1 for 10 ms...", this.getName());
				try {
					lock1.wait(10);
				} catch (InterruptedException e) {
					LOGGER.warn(ThreadUtils.INTERRUPTED, e);
					// Restore interrupted state...
					Thread.currentThread().interrupt();
				}
				LOGGER.info("{} trying to acquire lock2...", this.getName());
				synchronized (lock2) {
					LOGGER.info("{} acquirred lock2 for 10 ms...", this.getName());
					try {
						lock2.wait(10);
					} catch (InterruptedException e) {
						LOGGER.warn(ThreadUtils.INTERRUPTED, e);
						// Restore interrupted state...
						Thread.currentThread().interrupt();
					}
				}
				LOGGER.info("{} released lock2 after 10 ms...", this.getName());
			}
			LOGGER.info("{} released lock1 after 10 ms...", this.getName());
		}
	}

	private class ThreadTwo extends Thread {

		@Override
		public void run() {
			synchronized (lock2) {
				LOGGER.info("{} acquirred lock2 for 10 ms...", this.getName());
				try {
					lock2.wait(10);
				} catch (InterruptedException e) {
					LOGGER.warn("Interrupted!", e);
					// Restore interrupted state...
					Thread.currentThread().interrupt();
				}
				LOGGER.info("{} trying to acquire lock1...", this.getName());
				synchronized (lock1) {
					LOGGER.info("{} acquirred lock1 for 10 ms...", this.getName());
					try {
						lock1.wait(10);
					} catch (InterruptedException e) {
						LOGGER.warn("Interrupted!", e);
						// Restore interrupted state...
						Thread.currentThread().interrupt();
					}
				}
				LOGGER.info("{} released lock1 after 10 ms...", this.getName());
			}
			LOGGER.info("{} released lock2 after 10 ms...", this.getName());
		}
	}
}
