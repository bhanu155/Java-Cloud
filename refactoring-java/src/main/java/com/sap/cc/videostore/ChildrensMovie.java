package com.sap.cc.videostore;

public class ChildrensMovie extends Movie {

	public ChildrensMovie(String title) {
		super(title);
	}

	@Override
	protected int determineFrequentRenterPoints(int daysRented) {
		return 1;
	}

	@Override
	protected double determineAmount(int daysRented) {
		double rentalAmount = 1.5;
		if (daysRented > 3)
			rentalAmount += (daysRented - 3) * 1.5;
		return rentalAmount;
	}

}
