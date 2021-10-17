package com.manoj.multithreading.lock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CounterRunnerTest {

	@Test
	void testCounterRunner() {
		var increamentValue = 10000;

		var runnner = new CounterRunner();

		var counter1 = new Thread(() -> {
			try {
				runnner.firstThread(increamentValue);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});

		var counter2 = new Thread(() -> {
			try {
				runnner.secondThread(increamentValue);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});

		counter1.start();
		counter2.start();

		try {
			counter1.join();
			counter2.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		runnner.finished();

		assertEquals(increamentValue * 2, runnner.getCounterValue());
	}

}
