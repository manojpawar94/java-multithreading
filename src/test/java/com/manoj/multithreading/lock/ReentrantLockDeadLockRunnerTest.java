package com.manoj.multithreading.lock;

import org.junit.jupiter.api.Test;

class ReentrantLockDeadLockRunnerTest {

	@Test
	void test() {
		var reentrantLockDeadLockRunner = new ReentrantLockDeadLockRunner();
		reentrantLockDeadLockRunner.runner();
	}

}
