package com.manoj.multithreading.interrupts;

import org.junit.jupiter.api.Test;

import com.manoj.multithreading.utils.ThreadUtils;

class InterruptHandlerTaskTest {

	@Test
	void testRun() {
		var thread = new Thread(new InterruptHandlerTask());
		thread.setName("Interrupt_Handler_Task");

		thread.start();

		thread.interrupt();

		ThreadUtils.waitForConsoleInput();
	}

}
