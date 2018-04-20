package de.cronus.runtime.interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import de.cronus.runtime.cache.Cache;
import de.cronus.runtime.cache.line.SourceLine;
import de.cronus.runtime.interpreter.command.CommandManager;

public class Interpreter {

	private static Interpreter instance;

	public Interpreter() {
		instance = this;

		new CommandManager();
	}

	public static void interpretateSourceCode(File file) {
		try {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {

				int pos = 0;
				for (String line; (line = br.readLine()) != null;) {
					SourceLine sourceLine = CommandManager.onInterprete(pos, line);
					if (sourceLine != null) {
						Cache.addSourceLine(sourceLine);
						pos++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void run(String[] args) {
		Cache.clear();
		for (SourceLine sourceLine; (sourceLine = Cache.getSourceLine(Cache.getPos())) != null;) {
			int pos = Cache.getPos();
			Cache.setPos(pos + 1);
			CommandManager.onRun(pos, sourceLine);
		}
	}

	public static Interpreter getInstance() {
		return instance;
	}
}
