package de.cronus.runtime.cache.variable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import de.cronus.runtime.cache.Cache;

public class Variable {

	private Object object;

	public Variable(Object object) {
		this.object = object;
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param value the object to set
	 */
	public void setObject(String value) {

		if (value.startsWith("\"") && value.endsWith("\"")) {
			value = value.substring(1, value.length() - 1);

			Variable variable = Cache.getVariable(value);
			if (variable != null)
				object = variable.getObject();

			if (value.startsWith("Java ")) {
				value = value.substring(5);
				Class cl = null;
				try {
					cl = Class.forName(value);

					HashMap<String, Variable> variables = Cache.getArgs();
					Class[] cargs = new Class[variables.size()];
					Object[] args = new Object[variables.size()];
					int i = 0;
					for (Variable tv : variables.values()) {
						if (tv.getObject() != null) {
							cargs[i] = tv.getObject().getClass();
							args[i] = tv.getObject();
						}
						i++;
					}

					object = cl.getConstructor(cargs).newInstance(args);
				} catch (ClassNotFoundException | SecurityException | IllegalAccessException | InstantiationException | InvocationTargetException | IllegalArgumentException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					object = cl;
				}
			} else {
				object = value;
			}
		}else{
			if(value.equals("true")) {
				object = true;
				return;
			} else if(value.equals("false")) {
				object = false;
				return;
			}

			try {
				object = Integer.parseInt(value);
				return;
			} catch (NumberFormatException e) {

			}

			if(value.toLowerCase().endsWith("l")) {
				try {
					object = Long.parseLong(value.substring(0, value.length()-1));
					return;
				} catch (NumberFormatException e) {

				}
			}

			if(value.toLowerCase().endsWith("f")) {
				try {
					object = Float.parseFloat(value.substring(0, value.length()-1));
					return;
				} catch (NumberFormatException e) {

				}
			}

			object = Cache.getVariable(value).getObject();
		}
	}
}
