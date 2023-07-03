package com.sap.cc.greetings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Supplier;

@SpringBootTest
public class GreetingServiceTest {

	@MockBean
	Supplier<Greeting> greetingSupp;

	@Autowired
	GreetingService greetingService;

	@Test
	void getGreetingForPerson() {
		String name = "Angela";

		// Here we are using a mock bean as a test double for the supplier
		// and instead of random format, we are reurning Greeting of Hello format
		Mockito.when(greetingSupp.get()).thenReturn(new Greeting("Hello %s."));
		String greeting = greetingService.greet(name);

		// assertThat(greeting).contains(name);
		assertThat(greeting).isEqualTo("Hello Angela.");
	}
}
