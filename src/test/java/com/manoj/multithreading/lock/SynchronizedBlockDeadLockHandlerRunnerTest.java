package com.manoj.multithreading.lock;

import org.junit.jupiter.api.Test;

class SynchronizedBlockDeadLockHandlerRunnerTest {

	@Test
	void testSynchronizedBlockDeadLockHandlerRunner() {
		var deadLockHandlerRunner = new SynchronizedBlockDeadLockHandlerRunner();
		deadLockHandlerRunner.runDeadLock();
	}
}
