package de.cronus;

import java.io.FileNotFoundException;

import de.cronus.runtime.Runtime;

public class Main {

	public static void main(String[] args) {

		try {
			Runtime runtime = new Runtime("main.cpf");
			runtime.run(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
