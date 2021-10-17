package com.manoj.multithreading.utils;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadUtils {

	private static final Logger LOGGER = LogManager.getLogger();
	public static final String INTERRUPTED = "Interrupted";

	private ThreadUtils() {
	}

	public static void waitForConsoleInput() {
		try (var scanner = new Scanner(System.in)) {
			scanner.nextLine();
		}
	}

	public static void interruptThreadOnConsoleInput(Thread thread) {
		waitForConsoleInput();
		thread.interrupt();
	}

	public static void aquireLocks(Lock firstLock, Lock secondLock) {
		while (true) {
			boolean hasFirstLock = false;
			boolean hasSecondLock = false;

			try {
				hasFirstLock = firstLock.tryLock();
				hasSecondLock = secondLock.tryLock();

				if (hasFirstLock && hasSecondLock)
					break;

			} finally {
				if (hasFirstLock && !hasSecondLock) {
					firstLock.unlock();
				}

				if (!hasFirstLock && hasSecondLock) {
					secondLock.unlock();
				}
			}

			// Locks not acquired
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				LOGGER.warn(ThreadUtils.INTERRUPTED, e);
				// Restore interrupted state...
				Thread.currentThread().interrupt();
			}
		}
	}

	public static void releaseLocks(Lock... locks) {
		for (Lock lock : locks) {
			lock.unlock();
		}
	}

}
