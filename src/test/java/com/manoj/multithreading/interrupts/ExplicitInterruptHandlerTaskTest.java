package com.manoj.multithreading.interrupts;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import com.manoj.multithreading.utils.ThreadUtils;

class ExplicitInterruptHandlerTaskTest {

	@Test
	void testExplicitInterruptHandlerTask() {
		var base = BigInteger.valueOf(200000);
		var power = BigInteger.valueOf(50000000);

		var explicitInterruptHandlerTask = new ExplicitInterruptHandlerTask(base, power);

		var thread = new Thread(explicitInterruptHandlerTask);
		thread.setName("Explicit_Interrupt_Handler_Task");

		thread.start();

		ThreadUtils.interruptThreadOnConsoleInput(thread);

		ThreadUtils.waitForConsoleInput();
	}

}
