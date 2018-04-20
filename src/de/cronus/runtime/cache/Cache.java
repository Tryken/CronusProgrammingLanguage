package de.cronus.runtime.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import de.cronus.runtime.cache.line.SourceLine;
import de.cronus.runtime.cache.variable.Variable;
import de.cronus.runtime.cache.variable.VariableLayer;

public class Cache {

	private static Cache instance;

	private ArrayList<SourceLine> sourceLines;
	private HashMap<String, Integer> marks;
	private Stack<Integer> stack;
	private HashMap<Integer, VariableLayer> variableLayers;
	private VariableLayer args;

	private int pos;

	public Cache() {
		instance = this;
		sourceLines = new ArrayList<>();
		marks = new HashMap<>();
		args = new VariableLayer();
		clear();
	}

	public static void addSourceLine(SourceLine sourceLine) {
		getInstance().sourceLines.add(sourceLine);
	}

	public static SourceLine getNextSourceLine() {
		int pos = getPos();
		setPos(pos + 1);
		if (getInstance().sourceLines.size() > pos)
			return getInstance().sourceLines.get(pos);
		return null;
	}

	public static SourceLine getSourceLine(int pos) {
		if (getInstance().sourceLines.size() > pos)
			return getInstance().sourceLines.get(pos);
		return null;
	}

	public static void addMark(String name, int pos) {
		getInstance().marks.put(name, pos);
	}

	public static int getMarkPos(String name) {
		if (!getInstance().marks.containsKey(name))
			return -1;
		return getInstance().marks.get(name);
	}

	public static int getPrevioiusPos() {
		return getInstance().stack.pop();
	}

	public static int getPos() {
		return getInstance().pos;
	}

	public static void setPos(int pos) {
		getInstance().pos = pos;
	}

	public static void addVariable(String name) {
		VariableLayer layer = getVaribaleLayer(getInstance().stack.peek());
		layer.add(name);
	}

	public static void setVariable(String name, String value) {
		getVariable(name).setObject(value);
	}

	public static Variable getVariable(String name) {
		if (name.startsWith("arg"))
			return getArg(name.substring(2));

		for (int pos : getInstance().stack) {
			VariableLayer layer = getVaribaleLayer(pos);
			Variable variable = layer.get(name);
			if (variable != null)
				return variable;
		}
		return null;
	}

	public static void setArg(String name, String value) {
		getArg(name).setObject(value);
	}

	public static Variable getArg(String name) {
		VariableLayer layer = getInstance().args;
		layer.add(name);
		return layer.get(name);
	}

	public static HashMap<String, Variable> getArgs() {
		return getInstance().args.getArgs();
	}

	public static void clearArgs() {
		getInstance().args.clear();
	}

	public static VariableLayer getVaribaleLayer(int pos) {
		Cache cache = getInstance();
		VariableLayer layer;
		if (cache.variableLayers.containsKey(pos)) {
			layer = cache.variableLayers.get(pos);
		} else {
			layer = new VariableLayer();
			cache.variableLayers.put(pos, layer);
		}
		return layer;
	}

	/**
	 * @return the stack
	 */
	public static Stack<Integer> getStack() {
		return getInstance().stack;
	}

	public static void clear() {
		setPos(0);
		getInstance().stack = new Stack<>();
		getInstance().stack.push(-1);
		getInstance().variableLayers = new HashMap<>();
		getInstance().args.clear();
	}

	public static Cache getInstance() {
		return instance;
	}
}
