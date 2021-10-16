package com.manoj.multithreading.utils;

import java.util.Scanner;

public class ThreadUtils {

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
}
