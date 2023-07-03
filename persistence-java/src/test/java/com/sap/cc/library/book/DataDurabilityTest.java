package com.sap.cc.library.book;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DataDurabilityTest {

	@Autowired
	private BookRepository repository;

	@Test
	void populateDb() {
		int previousSize = repository.findAll().size();

		repository.save(BookFixtures.cleanCode());
		repository.save(BookFixtures.modernOperatingSystems());
		repository.save(BookFixtures.refactoring());

		List<Book> books = repository.findAll();

		assertThat(books).hasSizeGreaterThanOrEqualTo(previousSize);
	}

	@Test
	void isDbPopulated() {
		List<Book> books = repository.findAll();
		assertThat(books).hasSizeGreaterThanOrEqualTo(3);
	}

}
