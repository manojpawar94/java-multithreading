package com.manoj.multithreading.hackerpolice;

class DescendingHacker extends Hacker {

	DescendingHacker(Vault vault) {
		super(vault);
		this.setName("DescendingHacker");
		this.setPriority(MAX_PRIORITY);
	}

	@Override
	public void run() {
		for (var passwordGuess = Vault.getMaxPasswordValue(); passwordGuess >= 0; passwordGuess--) {
			if (vault.isCorrectPassword(passwordGuess)) {
				LOGGER.info("{} has hacked the vault. Police failed.", this.getName());
				System.exit(0);
			}
		}

	}
}
