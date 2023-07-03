package com.sap.cc.videostore;

public abstract class Movie {
	private String title;

	public Movie(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	protected abstract int determineFrequentRenterPoints(int daysRented);
	protected abstract double determineAmount(int daysRented);
}
