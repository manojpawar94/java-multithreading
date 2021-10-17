package com.manoj.multithreading.lock;

import org.junit.jupiter.api.Test;

class ReentrantLockDeadLockHandlerRunnerTest {

	@Test
	void test() {
		var reentrantLockDeadLockHandlerRunner = new ReentrantLockDeadLockHandlerRunner();
		reentrantLockDeadLockHandlerRunner.runner();
	}

}
