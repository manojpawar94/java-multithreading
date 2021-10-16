package com.manoj.multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class RunnableInterfaceTest {

	private static final Logger LOGGER = LogManager.getLogger();

	@Test
	void testRunnableInterfaceExample() {

		// define the thread
		var thread = new Thread(() -> {
			LOGGER.info("Inside {} thread.", Thread.currentThread().getName());
			LOGGER.info("Current thread priority {}.", Thread.currentThread().getPriority());
		});

		// setting thread name
		thread.setName("New-worker-thread");

		// setting thread priority
		thread.setPriority(Thread.MAX_PRIORITY);

		LOGGER.info("Inside {} thread before starting new thread.", Thread.currentThread().getName());

		// starting thread
		thread.start();

		LOGGER.info("Inside {} thread after starting new thread.", Thread.currentThread().getName());

	}
}
