package com.manoj.multithreading.interrupts;

import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Long running class with complex big mathematical computation. Interrupt has
 * not been handled here.
 * 
 * @author manojpawar
 *
 */
public class NonInterruptHandlerTask implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger();
	private BigInteger base;
	private BigInteger power;

	public NonInterruptHandlerTask(BigInteger base, BigInteger power) {
		super();
		this.base = base;
		this.power = power;
	}

	@Override
	public void run() {
		LOGGER.info("Power: {}", power);
		LOGGER.info("Base: {}", base);
		LOGGER.info("Result: {}", pow());
	}

	private BigInteger pow() {
		BigInteger result = BigInteger.ZERO;

		for (BigInteger index = BigInteger.ZERO; index.compareTo(power) != 0; index = index.add(BigInteger.ONE)) {
			result = result.max(base);
		}

		return result;
	}

}
