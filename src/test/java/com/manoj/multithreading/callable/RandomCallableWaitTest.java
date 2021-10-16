package com.manoj.multithreading.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class RandomCallableWaitTest {

	static final Logger LOGGER = LogManager.getLogger();

	@Test
	void testRandomWaitCallException() {
		var randomCallableWait = new RandomCallableWait(4000);

		var executorService = Executors.newCachedThreadPool();

		var waitDurationFuture = executorService.submit(randomCallableWait);
		executorService.shutdown();

		try {
			var waitDuration = waitDurationFuture.get();
			LOGGER.info("Wait Duration: {}", waitDuration);
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			TimeoutException ex = (TimeoutException) e.getCause();
			System.out.println(ex.getMessage());
		}

	}

}
