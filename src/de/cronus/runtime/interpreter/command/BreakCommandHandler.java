package de.cronus.runtime.interpreter.command;

import java.util.ArrayList;

import de.cronus.runtime.cache.Cache;
import de.cronus.runtime.cache.line.SourceLine;

public class BreakCommandHandler extends CommandHandler {

	@Override
	public SourceLine onInterprete(int pos, ArrayList<String> args) {
		if (args.size() != 1)
			return null;

		if (!args.get(0).equals("break"))
			return null;

		ArrayList<String> tempArgs = new ArrayList<>();
		tempArgs.add(args.get(0));
		return new SourceLine(tempArgs);
	}

	@Override
	public void onRun(int pos, ArrayList<String> args) {
		int newPos = Cache.getPrevioiusPos();
		if (newPos != -1)
			Cache.setPos(newPos + 1);
	}
}
