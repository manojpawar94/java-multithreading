package com.manoj.multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StopWatchThread extends Thread {

	private static final Logger LOGGER = LogManager.getLogger();

	private boolean stop = false;
	private long value = 0;

	@Override
	public void run() {
		while (!stop) {
			try {
				Thread.sleep(1);
				if (!stop) {
					value++;
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}
		LOGGER.info("Value: {}", value);
	}

	public long getValue() {
		return value;
	}

	public void stopWatch() {
		this.stop = true;
	}
}
