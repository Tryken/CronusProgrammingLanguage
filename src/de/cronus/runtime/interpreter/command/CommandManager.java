package de.cronus.runtime.interpreter.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.cronus.runtime.cache.line.SourceLine;

public class CommandManager {

	private ArrayList<CommandHandler> commandHandlers;
	private HashMap<Integer, CommandHandler> runCommandhandler;
	private Pattern argPattern;

	private static CommandManager instance;

	public CommandManager() {
		instance = this;

		commandHandlers = new ArrayList<>();
		runCommandhandler = new HashMap<>();
		argPattern = Pattern.compile("[^\\s\"']+|\"([^']*)\"|'([^']*)'");

		registerCommandHandler(new InitCommandHandler());
		registerCommandHandler(new SetCommandHandler());
		registerCommandHandler(new MarkCommandHandler());
		registerCommandHandler(new GotoCommandHandler());
		registerCommandHandler(new ArgCommandHandler());
		registerCommandHandler(new BreakCommandHandler());
		registerCommandHandler(new CallCommandhandler());
	}

	public static SourceLine onInterprete(int pos, String line) {
		ArrayList<String> args = new ArrayList<>();
		Matcher matcher = getInstance().argPattern.matcher(line);
		while (matcher.find())
			args.add(matcher.group(0));

		for (CommandHandler commandHandler : getInstance().commandHandlers) {
			SourceLine sourceLine = commandHandler.onInterprete(pos, args);
			if (sourceLine != null) {
				getInstance().runCommandhandler.put(pos, commandHandler);
				return sourceLine;
			}
		}
		return null;
	}

	public static void onRun(int pos, SourceLine sourceLine) {
		CommandHandler commandHandler = getInstance().runCommandhandler.get(pos);
		if (commandHandler != null)
			commandHandler.onRun(pos, sourceLine.getArgs());
	}

	public static void registerCommandHandler(CommandHandler commandHandler) {
		getInstance().commandHandlers.add(commandHandler);
	}

	public static CommandManager getInstance() {
		return instance;
	}
}
