package de.cronus.runtime.interpreter.command;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;

import de.cronus.runtime.cache.Cache;
import de.cronus.runtime.cache.line.SourceLine;
import de.cronus.runtime.cache.variable.Variable;
import de.cronus.runtime.util.PrimitiveTypeUtil;

import javax.swing.*;

public class CallCommandhandler extends CommandHandler {

	@Override
	public SourceLine onInterprete(int pos, ArrayList<String> args) {
		if (args.size() != 3)
			return null;

		if (!args.get(0).equals("call"))
			return null;

		ArrayList<String> tempArgs = new ArrayList<>();
		tempArgs.add(args.get(0));
		tempArgs.add(args.get(1));
		tempArgs.add(args.get(2));
		return new SourceLine(tempArgs);
	}

	@Override
	public void onRun(int pos, ArrayList<String> args) {

		Variable variable = Cache.getVariable(args.get(1));
		Object object = variable.getObject();
		Class c = null;
		if(object instanceof Class)
			c = (Class) object;
		else
			c = object.getClass();

		HashMap<String, Variable> variables = Cache.getArgs();
		Class[] cargs = new Class[variables.size()];
		Object[] oargs = new Object[variables.size()];
		int i = 0;
		for (Variable tv : variables.values()) {
			if (tv.getObject() != null) {
				oargs[i] = tv.getObject();

				Class tempClass = tv.getObject().getClass();
				Class primitiveClass = PrimitiveTypeUtil.getPrimitiveClass(tempClass.getSimpleName());
				if(primitiveClass != null)
					tempClass = primitiveClass;
				cargs[i] = tempClass;
			}
			i++;
		}

//		for(Method method : c.getMethods()) {
//			if(method.getName().equals(args.get(2).replace("\"", ""))) {
//				System.out.println(method.getName());
//				for (Parameter parameter : method.getParameters()) {
//					System.out.println(" - " + parameter.getType());
//				}
//				System.out.println(" ");
//			}
//		}

		try {
			c.getMethod(args.get(2).replace("\"", ""), cargs).invoke(object, oargs);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
