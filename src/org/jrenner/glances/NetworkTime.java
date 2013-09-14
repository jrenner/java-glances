package org.jrenner.glances;

public class NetworkTime {
	private double time;

	public NetworkTime(double time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return String.format("Time since last network update: %.4f seconds", time);
	}

	public double getTimeSinceLastUpdate() {
		return time;
	}
}
