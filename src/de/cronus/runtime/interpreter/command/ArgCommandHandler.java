package de.cronus.runtime.interpreter.command;

import java.util.ArrayList;

import de.cronus.runtime.cache.Cache;
import de.cronus.runtime.cache.line.SourceLine;

import javax.swing.*;

public class ArgCommandHandler extends CommandHandler {

	@Override
	public SourceLine onInterprete(int pos, ArrayList<String> args) {
		if (args.size() < 2)
			return null;

		if (!args.get(0).equals("arg"))
			return null;

		ArrayList<String> tempArgs = new ArrayList<>();

		if (args.size() == 2 && args.get(1).equals("clear")) {
			tempArgs.add(args.get(0));
			tempArgs.add(args.get(1));
			return new SourceLine(tempArgs);
		}

		if (args.size() != 3)
			return null;

		tempArgs.add(args.get(0));
		tempArgs.add(args.get(1));
		tempArgs.add(args.get(2));
		return new SourceLine(tempArgs);
	}

	@Override
	public void onRun(int pos, ArrayList<String> args) {
		if (args.size() == 2) {
			Cache.clearArgs();
		} else {
			Cache.setArg(args.get(1), args.get(2));
		}
	}
}
