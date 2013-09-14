package org.jrenner.glances;

public class Battery {
	private int batpercent;

	public int getBatteryPercent() {
		return batpercent;
	}

	@Override
	public String toString() {
		return "Battery percent: " + batpercent;
	}
}
