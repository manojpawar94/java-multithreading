package com.manoj.multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.manoj.multithreading.utils.ThreadUtils;

class StopWatchThreadTest {

	static final Logger LOGGER = LogManager.getLogger();

	@Test
	void testStopWatch() {
		var stopWatchThread = new StopWatchThread();
		LOGGER.info("Starting stopwatch...");
		stopWatchThread.start();

		ThreadUtils.waitForConsoleInput();
		stopWatchThread.stopWatch();
		LOGGER.info("Value of StopWatch in main {}", stopWatchThread.getValue());

	}

}
