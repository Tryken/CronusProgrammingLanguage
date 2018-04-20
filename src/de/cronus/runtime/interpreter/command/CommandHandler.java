package de.cronus.runtime.interpreter.command;

import java.util.ArrayList;

import de.cronus.runtime.cache.line.SourceLine;

public abstract class CommandHandler {

	public abstract SourceLine onInterprete(int pos, ArrayList<String> args);

	public abstract void onRun(int pos, ArrayList<String> args);
}
