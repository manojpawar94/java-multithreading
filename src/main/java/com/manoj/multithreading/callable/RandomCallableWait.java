/**
 * 
 */
package com.manoj.multithreading.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author manojpawar
 *
 */
public class RandomCallableWait implements Callable<Integer> {
	private static final Logger LOGGER = LogManager.getLogger();
	private final int bound;
	private static final int MAX_WAIT_ALLOWED = 2000;
	private final Random random;

	/**
	 * @param bound
	 */
	public RandomCallableWait(int bound) {
		this.bound = bound;
		this.random = new Random();
	}

	@Override
	public Integer call() throws TimeoutException {
		var randomWait = random.nextInt(bound);

		if (MAX_WAIT_ALLOWED < randomWait)
			throw new TimeoutException("Wait time is too large than allowed value.");
		LOGGER.info("Starting wait...");
		try {
			Thread.sleep(randomWait);
		} catch (InterruptedException e) {
			LOGGER.warn("Interrupted.");
			Thread.currentThread().interrupt();
		}
		LOGGER.info("Wait finished.");
		return randomWait;
	}

}
