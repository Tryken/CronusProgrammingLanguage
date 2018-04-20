package de.cronus.runtime.interpreter.command;

import java.util.ArrayList;

import de.cronus.runtime.cache.Cache;
import de.cronus.runtime.cache.line.SourceLine;

public class GotoCommandHandler extends CommandHandler {

	@Override
	public SourceLine onInterprete(int pos, ArrayList<String> args) {
		if (args.size() != 2)
			return null;

		if (!args.get(0).equals("goto"))
			return null;

		ArrayList<String> tempArgs = new ArrayList<>();
		tempArgs.add(args.get(0));
		tempArgs.add(args.get(1));
		return new SourceLine(tempArgs);
	}

	@Override
	public void onRun(int pos, ArrayList<String> args) {
		int newPos = Cache.getMarkPos(args.get(1));
		if (newPos != -1) {
			Cache.getStack().push(pos);
			Cache.setPos(newPos);
		}
	}
}
