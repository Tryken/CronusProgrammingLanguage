package de.cronus.runtime.interpreter.command;

import java.util.ArrayList;

import de.cronus.runtime.cache.Cache;
import de.cronus.runtime.cache.line.SourceLine;

public class SetCommandHandler extends CommandHandler {

	@Override
	public SourceLine onInterprete(int pos, ArrayList<String> args) {
		if (args.size() != 3)
			return null;

		if (!args.get(0).equals("set"))
			return null;

		ArrayList<String> tempArgs = new ArrayList<>();
		tempArgs.add(args.get(0));
		tempArgs.add(args.get(1));
		tempArgs.add(args.get(2));
		return new SourceLine(tempArgs);
	}

	@Override
	public void onRun(int pos, ArrayList<String> args) {
		Cache.setVariable(args.get(1), args.get(2));
		// TODO
	}
}
