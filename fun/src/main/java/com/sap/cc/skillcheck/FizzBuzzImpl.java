package com.sap.cc.skillcheck;

import org.springframework.stereotype.Component;

import com.sap.cc.skillcheck.donttouch.FizzBuzz;

@Component
public class FizzBuzzImpl implements FizzBuzz {

	private String previousResult;
	private boolean wasBingo;
	
	public FizzBuzzImpl() {
		previousResult = "";
		wasBingo = false;
	}

	@Override
	public String evaluate(int number) {
		String result;
		if(number%3==0 && number%5==0)	result="FizzBuzz";
		else if(number%3==0)	result="Fizz";
		else if(number%5==0)	result="Buzz";
		else result=Integer.toString(number);
		
		String temp = result;
		
		if(result.equals(previousResult) && !wasBingo) {
			result+="Bingo";
			wasBingo = true;
		}else {
			wasBingo = false;
		}
		
		previousResult = temp;
		
		return result;
	}

}
