package de.cronus.runtime;

import java.io.File;
import java.io.FileNotFoundException;

import de.cronus.runtime.cache.Cache;
import de.cronus.runtime.interpreter.Interpreter;

public class Runtime {

	private File file;

	public Runtime(String path) throws FileNotFoundException {
		File file = new File(path);

		if (!file.exists())
			throw new FileNotFoundException(String.format("Path: %s", path));

		new Cache();
		new Interpreter();

		Interpreter.interpretateSourceCode(file);
	}

	public void run(String[] args) {
		Interpreter.run(args);
	}
}
