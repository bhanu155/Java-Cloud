package com.sap.cc.books;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

	private BookStorage bookStorage;
	private CustomExceptionMapper customExceptionMapper;

	public BookController(BookStorage bookStorage) {
		super();
		this.bookStorage = bookStorage;
	}

	@GetMapping
	public List<Book> getAllBooks() {
		return bookStorage.getAll();
	}

	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book createdBook = bookStorage.save(book); // save book in the in-memory database

		String locationHeader = "/api/v1/books/" + createdBook.getId();

		return ResponseEntity.status(HttpStatus.CREATED).header("Location", locationHeader).body(createdBook);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getSingle(@PathVariable("id") long id) {
		if (id < 1) {
			throw new IllegalArgumentException("Id must not be less than 1");
		} else if (bookStorage.get(id).isPresent()) {
			Book book = bookStorage.get(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(book);
		} else {
			throw new NotFoundException();
		}

	}

}
