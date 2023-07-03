package com.sap.cc.skillcheck;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sap.cc.skillcheck.donttouch.FizzBuzz;

@SpringBootTest
public class FizzBuzzTest {

	@Autowired
	private FizzBuzz fizzbuzz;

	@Test
	void divisibleByThree() {
		int num = 3;
		assertThat(fizzbuzz.evaluate(num)).isEqualTo("Fizz");
	}

	@Test
	void divisibleByFive() {
		int num = 5;
		assertThat(fizzbuzz.evaluate(num)).isEqualTo("Buzz");
	}

	@Test
	void divisibleByThreeAndFive() {
		int num = 15;
		assertThat(fizzbuzz.evaluate(num)).isEqualTo("FizzBuzz");
	}

	@Test
	void notDivisibleByThreeOrFive() {
		int num = 1;
		assertThat(fizzbuzz.evaluate(num)).isEqualTo(Integer.toString(num));
	}

	@Test
	void sameResult() {

		int num = 3;
		assertThat(fizzbuzz.evaluate(num)).isEqualTo("Fizz");
		num = 9;
		assertThat(fizzbuzz.evaluate(num)).isEqualTo("FizzBingo");
		num = 6;
		assertThat(fizzbuzz.evaluate(num)).isEqualTo("Fizz");
		num = 12;
		assertThat(fizzbuzz.evaluate(num)).isEqualTo("FizzBingo");
		num = 1;
		assertThat(fizzbuzz.evaluate(num)).isEqualTo("1");
		assertThat(fizzbuzz.evaluate(num)).isEqualTo("1Bingo");

	}

}
