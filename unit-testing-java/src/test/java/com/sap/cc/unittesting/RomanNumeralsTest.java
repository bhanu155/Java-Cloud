package com.sap.cc.unittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralsTest {

	private RomanNumerals romanNumerals;

	// @Before each will run before execution of each test case
	@BeforeEach
	public void setup() {
		romanNumerals = new RomanNumerals();
	}

	@Test
	public void exampleTest() {
		assertThat(40 + 2).isEqualTo(42);
		// TODO: Add test cases to evaluate the productive code in class RomanNumerals.
	}

	// Happy Path (Positive Test cases)
	@Test
	public void singleDigitRomanTest() {
		int actual, expected;

		actual = romanNumerals.toArabic("I");
		expected = 1;
		assertThat(actual).isEqualTo(expected);

		actual = romanNumerals.toArabic("V");
		expected = 5;
		assertThat(actual).isEqualTo(expected);

		actual = romanNumerals.toArabic("M");
		expected = 1000;
		assertThat(actual).isEqualTo(expected);

	}

	// Error Path (Negative Test cases)
	@Test
	public void Should_ThrowException_When_InvalidNumeral() {
		int ERROR_VALUE = -1;
		int actual;

		actual = romanNumerals.toArabic("WWI");
		assertThat(actual).isEqualTo(ERROR_VALUE);

		actual = romanNumerals.toArabic("");
		assertThat(actual).isEqualTo(ERROR_VALUE);

		actual = romanNumerals.toArabic(null);
		assertThat(actual).isEqualTo(ERROR_VALUE);

		actual = romanNumerals.toArabic("VVIIXIV");
		assertThat(actual).isEqualTo(ERROR_VALUE);
	}

	// additive and subtractive numerals
	@Test
	public void additiveRomanNumeralTest() {
		int actual, expected;

		actual = romanNumerals.toArabic("II");
		expected = 2;
		assertThat(actual).isEqualTo(expected);

		actual = romanNumerals.toArabic("VI");
		expected = 6;
		assertThat(actual).isEqualTo(expected);

		actual = romanNumerals.toArabic("CXI");
		expected = 111;
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void subtractiveRomanNumeralTest() {
		int actual, expected;

		actual = romanNumerals.toArabic("IV");
		expected = 4;
		assertThat(actual).isEqualTo(expected);

		actual = romanNumerals.toArabic("XL");
		expected = 40;
		assertThat(actual).isEqualTo(expected);

		actual = romanNumerals.toArabic("XC");
		expected = 90;
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void complexRomanNumeralTest() {
		int actual, expected;

		actual = romanNumerals.toArabic("XIV");
		expected = 14;
		assertThat(actual).isEqualTo(expected);

		actual = romanNumerals.toArabic("CMXL");
		expected = 940;
		assertThat(actual).isEqualTo(expected);

		actual = romanNumerals.toArabic("MMXXI");
		expected = 2021;
		assertThat(actual).isEqualTo(expected);
	}

}
