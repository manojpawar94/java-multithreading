package com.manoj.multithreading.callable;

import java.util.concurrent.Callable;

/**
 * 
 * @author manojpawar
 *
 */
public class VoidCallableFuture implements Callable<Void> {

	@Override
	public Void call() throws Exception {
		// Add implementation
		// always return value is null
		return null;
	}

}
