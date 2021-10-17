/**
 * 
 */
package com.manoj.multithreading.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.manoj.multithreading.utils.ThreadUtils;

/**
 * @author manojpawar
 *
 */
public class CounterRunner {

	private static final Logger LOGGER = LogManager.getLogger();
	private int counterValue;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void firstThread(int increamentValue) throws InterruptedException {
		lock.lock();
		LOGGER.info("{} is Waiting.", Thread.currentThread().getName());
		condition.await();
		LOGGER.info("{} is resumed.", Thread.currentThread().getName());
		try {
			increamentBy(increamentValue);
		} finally {
			// Unlock added in finally block to ensure that unlock runs always
			// to avoid deadlock situation
			lock.unlock();
		}
	}

	public void secondThread(int increamentValue) throws InterruptedException {
		Thread.sleep(1000);
		lock.lock();
		LOGGER.info("Press return key");
		ThreadUtils.waitForConsoleInput();
		LOGGER.info("You have pressed return key");
		condition.signal();
		try {
			increamentBy(increamentValue);
		} finally {
			// Unlock added in finally block to ensure that unlock runs always
			// to avoid deadlock situation
			lock.unlock();
		}
	}

	public void finished() {
		LOGGER.info("The counterValue value is {}", counterValue);
	}

	/**
	 * @return the counterValue
	 */
	public int getCounterValue() {
		return counterValue;
	}

	private void increamentBy(int increamentValue) {
		for (var index = 0; index < increamentValue; index++) {
			counterValue++;
		}
	}

}
