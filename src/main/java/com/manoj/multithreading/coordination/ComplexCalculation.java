package com.manoj.multithreading.coordination;

import java.math.BigInteger;

public class ComplexCalculation {

	public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
		BigInteger result = BigInteger.ZERO;
		PowerCalculatingThread powerCalculatingThread1 = new PowerCalculatingThread(base1, power1);
		PowerCalculatingThread powerCalculatingThread2 = new PowerCalculatingThread(base2, power2);

		powerCalculatingThread1.start();
		powerCalculatingThread2.start();

		try {
			powerCalculatingThread1.join();
			powerCalculatingThread2.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		result = result.add(powerCalculatingThread1.getResult());
		result = result.add(powerCalculatingThread2.getResult());

		return result;
	}

	private static class PowerCalculatingThread extends Thread {
		private BigInteger result = BigInteger.ONE;
		private BigInteger base;
		private BigInteger power;

		public PowerCalculatingThread(BigInteger base, BigInteger power) {
			this.base = base;
			this.power = power;
		}

		@Override
		public void run() {
			calculatePow();
		}

		private void calculatePow() {
			result = base.pow(power.intValue());
		}

		public BigInteger getResult() {
			return result;
		}
	}
}