package com.manoj.multithreading.lock;

import org.junit.jupiter.api.Test;

class SynchronizedBlockDeadLockRunnerTest {

	@Test
	void testRunDeadLock() {
		var deadLockRunner = new SynchronizedBlockDeadLockRunner();

		deadLockRunner.runDeadLock();
	}

}
