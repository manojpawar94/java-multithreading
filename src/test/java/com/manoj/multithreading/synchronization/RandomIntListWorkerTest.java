package com.manoj.multithreading.synchronization;

import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class RandomIntListWorkerTest {

	static final Logger LOGGER = LogManager.getLogger();

	@Test
	void testRandomIntWorker() {
		var randomIntWorker = new RandomIntListWorker(10000);
		var start = Instant.now();

		randomIntWorker.createList1();

		randomIntWorker.createList2();

		var end = Instant.now();

		LOGGER.info("Process completed in: {} seconds", (end.getEpochSecond() - start.getEpochSecond()));
		LOGGER.info("Size of list1: {}", randomIntWorker.getList1().size());
		LOGGER.info("Size of list2: {}", randomIntWorker.getList2().size());
	}

	@Test
	void testParallelRandomIntWorker() {
		var randomIntWorker = new RandomIntListWorker(10000);
		var start = Instant.now();

		randomIntWorker.createParallelList();

		var end = Instant.now();

		LOGGER.info("Process completed in: {} seconds", (end.getEpochSecond() - start.getEpochSecond()));
		LOGGER.info("Size of list1: {}", randomIntWorker.getList1().size());
		LOGGER.info("Size of list2: {}", randomIntWorker.getList2().size());
	}

	@Test
	void testParallel2RandomIntWorker() {
		var randomIntWorker = new RandomIntListWorker(10000);
		var start = Instant.now();

		randomIntWorker.createParallelList2();

		var end = Instant.now();

		LOGGER.info("Process completed in: {} seconds", (end.getEpochSecond() - start.getEpochSecond()));
		LOGGER.info("Size of list1: {}", randomIntWorker.getList1().size());
		LOGGER.info("Size of list2: {}", randomIntWorker.getList2().size());
	}

}
