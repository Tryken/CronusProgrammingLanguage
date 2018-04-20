package de.cronus.runtime.interpreter.command;

import java.util.ArrayList;

import de.cronus.runtime.cache.Cache;
import de.cronus.runtime.cache.line.SourceLine;

import javax.swing.*;

public class MarkCommandHandler extends CommandHandler {

	@Override
	public SourceLine onInterprete(int pos, ArrayList<String> args) {
		if (args.size() != 1)
			return null;

		if (!args.get(0).endsWith(":"))
			return null;

		String markName = args.get(0).substring(0, args.get(0).length() - 1);
		Cache.addMark(markName, pos);

		ArrayList<String> tempArgs = new ArrayList<>();
		tempArgs.add(markName);
		return new SourceLine(tempArgs);
	}

	@Override
	public void onRun(int pos, ArrayList<String> args) {

	}
}
