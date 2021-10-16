package com.manoj.multithreading.interrupts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InterruptHandlerTask implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final long THREAD_WAIT = 50000000;

	@Override
	public void run() {
		try {
			Thread.sleep(THREAD_WAIT);
		} catch (InterruptedException e) {
			// Interrupt handling code
			LOGGER.warn("{} thread is interrupted. Existing from thread.", Thread.currentThread().getName());
			Thread.currentThread().interrupt();
		}

	}

}
