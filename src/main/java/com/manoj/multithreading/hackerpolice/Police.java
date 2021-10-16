package com.manoj.multithreading.hackerpolice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Police implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger();

	private final int intervalToReachVault;

	Police(int intervalToReachVault) {
		super();
		this.intervalToReachVault = intervalToReachVault;
	}

	@Override
	public void run() {
		for (var index = 1; index <= intervalToReachVault; index++) {
			try {
				Thread.sleep(1000);
				LOGGER.info("Securing Level: {}", index);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		LOGGER.info("Vault secured. Hacker attack terminated .");
		System.exit(0);
	}

}
