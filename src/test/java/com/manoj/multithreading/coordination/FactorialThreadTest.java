package com.manoj.multithreading.coordination;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class FactorialThreadTest {

	private static final Logger LOGGER = LogManager.getLogger();

	@Disabled
	@Test
	void testThreadWithoutCoordination() {
		var inputNumbers = Arrays.asList(0L, 3456L, 5643L, 604L, 12034L);

		var threads = new ArrayList<FactorialThread>();

		inputNumbers.stream().map(FactorialThread::new).forEach(threads::add);

		threads.stream().forEach(FactorialThread::start);

		for (var index = 0; index < inputNumbers.size(); index++) {
			FactorialThread factorialThread = threads.get(index);

			if (factorialThread.isFinished()) {
				LOGGER.info("Factorial of {} is {}", inputNumbers.get(index), factorialThread.getFactorial());
			} else {
				LOGGER.info("Calculation of factorial of {} is still in progress...", inputNumbers.get(index));
			}
		}
	}

	@Disabled
	@Test
	void testThreadWithCoordination() {
		var inputNumbers = Arrays.asList(0L, 3456L, 5643L, 604L, 12034L);

		var threads = new ArrayList<FactorialThread>();

		inputNumbers.stream().map(FactorialThread::new).forEach(threads::add);

		threads.stream().forEach(FactorialThread::start);

		threads.stream().forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				LOGGER.error("Thread interruption occurs for input number {}.", thread.getInputNumber(), e);
			}
		});

		for (var index = 0; index < inputNumbers.size(); index++) {
			FactorialThread factorialThread = threads.get(index);

			if (factorialThread.isFinished()) {
				LOGGER.info("Factorial of {} is {}", inputNumbers.get(index), factorialThread.getFactorial());
			} else {
				LOGGER.info("Calculation of factorial of {} is still in progress...", inputNumbers.get(index));
			}
		}

	}

	@Test
	void testThreadWithCooredinationWithTimeout() {
		var inputNumbers = Arrays.asList(1000000000L, 3456L, 5643L, 604L, 12034L);

		var threads = new ArrayList<FactorialThread>();

		inputNumbers.stream().map(FactorialThread::new).forEach(threads::add);

		// setting thread as demon thread before calling start method so application
		// should not be running due to long running thread after expected timeout
		threads.stream().forEach(thread -> thread.setDaemon(true));

		threads.stream().forEach(FactorialThread::start);

		threads.stream().forEach(thread -> {
			try {
				// main or calling thread will wait only 5000 milliseconds to thread to
				// complete.
				thread.join(1000);
			} catch (InterruptedException e) {
				LOGGER.error("Thread interruption occurs for input number {}.", thread.getInputNumber(), e);
			}
		});

		for (var index = 0; index < inputNumbers.size(); index++) {
			FactorialThread factorialThread = threads.get(index);

			if (factorialThread.isFinished()) {
				LOGGER.info("Factorial of {} is {}", inputNumbers.get(index), factorialThread.getFactorial());
			} else {
				LOGGER.info("Calculation of factorial of {} is still in progress...", inputNumbers.get(index));
			}
		}

	}

}
