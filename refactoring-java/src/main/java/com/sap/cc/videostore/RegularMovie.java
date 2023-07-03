package com.sap.cc.videostore;

public class RegularMovie extends Movie {

	public RegularMovie(String title) {
		super(title);
	}

	@Override
	protected int determineFrequentRenterPoints(int daysRented) {
		return 1;
	}

	@Override
	protected double determineAmount(int daysRented) {
		double rentalAmount = 2;
		if (daysRented > 2)
			rentalAmount += (daysRented - 2) * 1.5;
		return rentalAmount;
	}

}
