package com.manoj.multithreading.interrupts;

import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExplicitInterruptHandlerTask implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger();
	private BigInteger base;
	private BigInteger power;

	public ExplicitInterruptHandlerTask(BigInteger base, BigInteger power) {
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
			// Handling thread interrupt explicitly
			if (Thread.interrupted()) {
				LOGGER.info("Prematurely interrupted computations.");
				return BigInteger.ZERO;
			}
			result = result.max(base);
		}

		return result;
	}
}
