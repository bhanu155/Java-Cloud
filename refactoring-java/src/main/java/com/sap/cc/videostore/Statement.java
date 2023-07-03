package com.sap.cc.videostore;

import java.util.ArrayList;
import java.util.List;

class Statement {
	private String _name;
	private List<Rental> rentals = new ArrayList<>();
	private double totalAmount;
	private int frequentRenterPoints;

	private void clearTotals() {
		totalAmount = 0;
		frequentRenterPoints = 0;
	}

	private String header() {
		return "Rental Record for " + getName() + "\n";
	}

	private String formatRentalLine(Rental rental, double rentalAmount) {
		// show figures for this rental
		return "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rentalAmount) + "\n";
	}

	private String rentalLine(Rental rental) {

		double rentalAmount = rental.determineAmount();
		frequentRenterPoints += rental.determineFrequentRenterPoints();
		totalAmount += rentalAmount;
		return formatRentalLine(rental, rentalAmount);

	}

	private String rentalLines() {
		String rentalLines = "";
		for (Rental rental : rentals) {
			rentalLines += rentalLine(rental);
		}
		return rentalLines;
	}

	private String footer() {
		// add footer lines
		String footer = "Amount owed is " + String.valueOf(totalAmount) + "\n";
		footer += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";

		return footer;
	}

	public Statement(String name) {
		_name = name;
	}

	public void addRental(Rental arg) {
		rentals.add(arg);
	}

	public String getName() {
		return _name;
	}

	public String generate() {
		clearTotals();
		String statementText = header();
		statementText += rentalLines();
		statementText += footer();
		return statementText;
	}
}
