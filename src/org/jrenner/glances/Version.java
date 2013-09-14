package org.jrenner.glances;

public class Version {
	private String version;

	@Override
	public String toString() {
		return "Glances server version: " + version;
	}

	public String getVersion() {
		return version;
	}
}
