package com.sap.cc.library.book;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;

	@BeforeEach
	void clearDb() {
		repository.deleteAll();
	}

	@Test
	void findAllBooks_should_be_empty() {
		List<Book> books = repository.findAll();
		assertThat(books).isEmpty();
	}

	@Test
	void saveBook_shouldReturnSavedBook() {
		Book cleanCodeBook = BookFixtures.cleanCode();

		repository.save(cleanCodeBook);

		List<Book> savedBooks = repository.findAll();

		assertThat(savedBooks).hasSize(1);

		assertThat(savedBooks.get(0).getAuthor().getName()).isEqualTo(cleanCodeBook.getAuthor().getName());
		assertThat(savedBooks.get(0).getTitle()).isEqualTo(cleanCodeBook.getTitle());
	}

	@Test
	void findBookByTitle() {
		Book designPatterns = BookFixtures.designPatterns();
		Book domainDrivenDesign = BookFixtures.domainDrivenDesign();

		repository.save(designPatterns);
		repository.save(domainDrivenDesign);

		Book searchResult = repository.findByTitle(designPatterns.getTitle());

		assertThat(searchResult.getTitle()).isEqualTo(designPatterns.getTitle());
	}

}
