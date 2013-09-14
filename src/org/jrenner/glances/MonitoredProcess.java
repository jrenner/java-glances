package org.jrenner.glances;

public class MonitoredProcess {
	private String regex;
	private int countmin;
	private int countmax;
	private String command;
	private String description;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Monitored Process ------------- " + description);
		sb.append("\nmin: " + countmin + ", max: " + countmax);
		sb.append("\nregex: " + regex);
		sb.append("\ncommand: '" + command + "'");
		return sb.toString();
	}

	public String getDescription() {
		return description;
	}

	public String getRegex() {
		return regex;
	}

	public int getCountmin() {
		return countmin;
	}

	public int getCountmax() {
		return countmax;
	}

	public String getCommand() {
		return command;
	}
}
