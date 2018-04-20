package de.cronus.runtime.cache.line;

import java.util.ArrayList;

public class SourceLine {

	private ArrayList<String> args;
	private int lineNo;
	private String sourceCode;

	public SourceLine(ArrayList<String> args) {
		this(args, -1, null);
	}

	public SourceLine(ArrayList<String> args, int lineNo, String sourceCode) {
		this.args = args;
		this.lineNo = lineNo;
		this.sourceCode = sourceCode;
	}

	/**
	 * @return the args
	 */
	public ArrayList<String> getArgs() {
		return args;
	}

	/**
	 * @return the lineNo
	 */
	public int getLineNo() {
		return lineNo;
	}

	/**
	 * @return the sourceCode
	 */
	public String getSourceCode() {
		return sourceCode;
	}

}
