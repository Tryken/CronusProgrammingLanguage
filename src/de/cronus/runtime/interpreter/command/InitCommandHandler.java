package de.cronus.runtime.interpreter.command;

import java.util.ArrayList;

import de.cronus.runtime.cache.Cache;
import de.cronus.runtime.cache.line.SourceLine;

public class InitCommandHandler extends CommandHandler {

	@Override
	public SourceLine onInterprete(int pos, ArrayList<String> args) {
		if (args.size() != 2)
			return null;

		if (!args.get(0).equals("init"))
			return null;

		ArrayList<String> tempArgs = new ArrayList<>();
		tempArgs.add(args.get(0));
		tempArgs.add(args.get(1));
		return new SourceLine(tempArgs);
	}

	@Override
	public void onRun(int pos, ArrayList<String> args) {
		Cache.addVariable(args.get(1));
	}
}
