package com.manoj.multithreading.hackerpolice;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.manoj.multithreading.utils.ThreadUtils;

class VaultTest {

	static final Logger LOGGER = LogManager.getLogger();

	@Test
	void test() {
		var random = new Random();

		var vaultPasswrd = random.nextInt(Vault.getMaxPasswordValue());
		LOGGER.info("Random Password: {}", vaultPasswrd);

		final var vault = new Vault(vaultPasswrd);

		var ascendingHacker = new AscendingHacker(vault);
		var descendingHacker = new DescendingHacker(vault);
		var police = new Thread(new Police(10));

		ascendingHacker.start();
		descendingHacker.start();
		police.start();

		ThreadUtils.waitForConsoleInput();

	}

}
