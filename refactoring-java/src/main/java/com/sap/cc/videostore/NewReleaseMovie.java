package com.sap.cc.videostore;

public class NewReleaseMovie extends Movie {

	public NewReleaseMovie(String title) {
		super(title);
	}

	@Override
	protected int determineFrequentRenterPoints(int daysRented) {
		int frequentRenterPoints = 1;
		if (daysRented > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	@Override
	protected double determineAmount(int daysRented) {
		return daysRented * 3;
	}

}
