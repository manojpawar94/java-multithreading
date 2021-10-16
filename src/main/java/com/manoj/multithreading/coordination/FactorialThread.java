package com.manoj.multithreading.coordination;

import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FactorialThread extends Thread {
	private static final Logger LOGGER = LogManager.getLogger();

	private final long inputNumber;
	private BigInteger factorial;
	private boolean isFinished = false;

	public FactorialThread(long inputNumber) {
		super();
		this.inputNumber = inputNumber;
	}

	@Override
	public void run() {
		factorial();
		super.run();
	}

	private void factorial() {
		LOGGER.info("Computing factorial of {}...", inputNumber);
		factorial = BigInteger.ONE;
		factorial.pow(factorial.intValue());
		for (var index = inputNumber; index > 0; index--) {
			factorial = factorial.multiply(BigInteger.valueOf(index));
		}
		isFinished = true;
		LOGGER.info("Computation of factorial of {} is completed.", inputNumber);
	}

	public long getInputNumber() {
		return inputNumber;
	}

	public BigInteger getFactorial() {
		return isFinished ? factorial : null;
	}

	public boolean isFinished() {
		return isFinished;
	}

}
