package com.manoj.multithreading.hackerpolice;

class Vault {
	private static final int MAX_PASSWORD_VALUE = 9999;
	private final int password;

	Vault(int password) {
		super();
		this.password = password;
	}

	boolean isCorrectPassword(final int password) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return this.password == password;
	}

	public static int getMaxPasswordValue() {
		return MAX_PASSWORD_VALUE;
	}

}
