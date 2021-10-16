package com.manoj.multithreading.hackerpolice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract class Hacker extends Thread {

	protected static final Logger LOGGER = LogManager.getLogger();

	protected final Vault vault;

	Hacker(Vault vault) {
		super();
		this.vault = vault;

	}

	@Override
	public synchronized void start() {
		LOGGER.info("Starting {} thread.", this.getName());
		super.start();
	}

	@Override
	public abstract void run();
}
