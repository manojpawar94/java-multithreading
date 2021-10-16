package com.manoj.multithreading.callable;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class CallableWordCountTest {

	static final Logger LOGGER = LogManager.getLogger();

	@Test
	void testCallableFuture() {
		var callableWordCount = new CallableWordCount("test.txt");

		var executorService = Executors.newCachedThreadPool();

		var wordCountMapFuture = executorService.submit(callableWordCount);

		executorService.shutdown();

		Map<String, Integer> wordCountMap = null;
		try {
			wordCountMap = wordCountMapFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e);
		}

		var totalWords = 0;
		for (Entry<String, Integer> wordCount : wordCountMap.entrySet()) {
			totalWords += wordCount.getValue();
			LOGGER.info("Word: {}, Count: {}", wordCount.getKey(), wordCount.getValue());
		}
		LOGGER.info("The unique word in file: {}", wordCountMap.size());
		LOGGER.info("The total word in file: {}", totalWords);
	}

}
