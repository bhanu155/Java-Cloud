package com.sap.cc.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FizzBuzzTest {

	private FizzBuzz fizzBuzz;

	@BeforeEach
	void setUp() {
		fizzBuzz = new FizzBuzz();
	}

	@Test
	void oneShouldGiveOne() {
		String expected = "1";
		String actual = fizzBuzz.print(1);
		
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void twoShouldGiveTwo() {
		String expected = "2";
		String actual = fizzBuzz.print(2);
		
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void threeShouldGiveFizz() {
		String expected = "Fizz";
		String actual = fizzBuzz.print(3);
		
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void sixShouldGiveFizz() {
		String expected = "Fizz";
		String actual = fizzBuzz.print(6);
		
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void fiveShouldGiveBuzz() {
		String expected = "Buzz";
		String actual = fizzBuzz.print(5);
		
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void tenShouldGiveBuzz() {
		String expected = "Buzz";
		String actual = fizzBuzz.print(10);
		
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void fifteenShouldGiveFizzBuzz() {
		String expected = "FizzBuzz";
		String actual = fizzBuzz.print(15);
		
		assertThat(actual).isEqualTo(expected);
	}

}
