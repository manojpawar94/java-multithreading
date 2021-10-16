package com.manoj.multithreading.hackerpolice;

class AscendingHacker extends Hacker {

	AscendingHacker(Vault vault) {
		super(vault);
		this.setName("AscendingHacker");
		this.setPriority(MAX_PRIORITY);
	}

	@Override
	public void run() {
		for (var passwordGuess = 0; passwordGuess <= Vault.getMaxPasswordValue(); passwordGuess++) {
			if (vault.isCorrectPassword(passwordGuess)) {
				LOGGER.info("{} has hacked the vault. Police failed.", this.getName());
				System.exit(0);
			}
		}

	}
}
