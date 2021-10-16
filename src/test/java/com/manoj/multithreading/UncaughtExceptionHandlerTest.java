package com.manoj.multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.manoj.multithreading.utils.ThreadUtils;

class UncaughtExceptionHandlerTest {

	private static final Logger LOGGER = LogManager.getLogger();

	@Test
	void uncaughtExceptionExample() {

		var thread = new Thread(() -> {
			LOGGER.info("Inside {} thread.", Thread.currentThread().getName());
			throw new RuntimeException("Internal thread error.");
		});

		// to handle uncaught exceptions
		thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

		// set thread name
		thread.setName("Misbehaving-Thread-For-Test");

		LOGGER.info("Inside {} thread before starting new thread.", Thread.currentThread().getName());

		// starting thread
		thread.start();

		LOGGER.info("Inside {} thread after starting new thread.", Thread.currentThread().getName());

		ThreadUtils.waitForConsoleInput();
	}
}
