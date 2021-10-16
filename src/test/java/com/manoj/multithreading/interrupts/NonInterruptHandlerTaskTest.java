package com.manoj.multithreading.interrupts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import com.manoj.multithreading.utils.ThreadUtils;

class NonInterruptHandlerTaskTest {

	@Test
	void testNonInterruptHandlerTask() {
		var base = BigInteger.valueOf(200000);
		var power = BigInteger.valueOf(50000000);

		var nonInterruptHandlerTask = new NonInterruptHandlerTask(base, power);

		var nonIntrupptThread = new Thread(nonInterruptHandlerTask);
		nonIntrupptThread.setName("None_Interrupt_Handler_Task");

		// to terminate the thread on all users thread completed
		nonIntrupptThread.setDaemon(true);

		nonIntrupptThread.start();

		nonIntrupptThread.interrupt();

		assertTrue(nonIntrupptThread.isAlive());

		ThreadUtils.waitForConsoleInput();
	}

}
