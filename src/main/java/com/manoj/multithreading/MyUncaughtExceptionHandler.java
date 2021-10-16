package com.manoj.multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void uncaughtException(Thread thread, Throwable e) {
		LOGGER.warn("Error occured during execution of {}::{} thread", thread.getId(), thread.getName());
		LOGGER.error("Error", e);
	}

}
