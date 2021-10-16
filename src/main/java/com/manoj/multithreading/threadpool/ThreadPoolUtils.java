package com.manoj.multithreading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {

	private ThreadPoolUtils() {
	}

	public static ExecutorService createFixedThreadPool(int nThread) {
		var executorService = Executors.newFixedThreadPool(nThread);
		return executorService;
	}
}
