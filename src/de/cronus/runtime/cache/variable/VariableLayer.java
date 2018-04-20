package de.cronus.runtime.cache.variable;

import java.util.HashMap;

public class VariableLayer {

	private HashMap<String, Variable> variables;

	public VariableLayer() {

		variables = new HashMap<>();
	}

	public void add(String name) {
		if (!variables.containsKey(name))
			variables.put(name, new Variable(null));
	}

	public void set(String name, Object object) {
		Variable variable = get(name);
	}

	public Variable get(String name) {
		return variables.get(name);
	}

	public HashMap<String, Variable> getArgs() {
		return variables;
	}

	public void clear() {
		variables = new HashMap<>();
	}
}
