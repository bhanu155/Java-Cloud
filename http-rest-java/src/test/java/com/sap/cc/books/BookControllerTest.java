package com.sap.cc.books;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAll_noBooks_returnsEmptyList() throws Exception {
		this.mockMvc.perform(get("/api/v1/books")).andExpect(status().isOk()).andExpect(content().json("[]"));
	}

	private MvcResult createBook(String author) throws Exception {
		String bookData = "{\"author\": \"" + author + "\"}";

		// Send POST request to add a book
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/v1/books").contentType(MediaType.APPLICATION_JSON)
						.content(bookData))
				.andExpect(status().isCreated()) // Expecting CREATED status code
				.andExpect(jsonPath("$.author").value(author)) // Expecting author to match
				.andReturn();
		return result;
	}

	@Test
	public void addBook_returnsCreatedBook() throws Exception {
		// Prepare the request payload
		MvcResult createdBookResult = createBook("Author1");

		// Verify the response
		String locationHeader = createdBookResult.getResponse().getHeader("Location");
		String returnedBookId = locationHeader.substring((int) (locationHeader.lastIndexOf("/") + 1L));

		// Expecting non-null book ID
		assertNotNull(returnedBookId);
		// Expecting correct Location header
		assertTrue(locationHeader.contains("/api/v1/books/" + returnedBookId));
	}

	@Test
	public void addBookAndGetSingle_returnsBook() throws Exception {
		MvcResult createdBookResult = createBook("Author2");

		String locationHeader = createdBookResult.getResponse().getHeader("Location");

		MvcResult getResult = mockMvc
				.perform(MockMvcRequestBuilders.get(locationHeader).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		// match the book returned by GET and POST
		String expectedBookJson = createdBookResult.getResponse().getContentAsString();
		String actualBookJson = getResult.getResponse().getContentAsString();

		assertEquals(expectedBookJson, actualBookJson);
	}

	@Test
	public void getSingle_noBooks_returnNotFound() throws Exception {
		MvcResult getResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/books/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
	}

	@Test
	public void addMultipleAndGetAll_returnsAddedBooks() throws Exception {
		MvcResult bookData1 = createBook("TestAuthor1");

		MvcResult getAllResult1 = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/books").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		List<Book> getAllBooksResult1 = objectMapper.readValue(getAllResult1.getResponse().getContentAsString(),
				new com.fasterxml.jackson.core.type.TypeReference<List<Book>>() {
				});

		assertEquals(1, getAllBooksResult1.size());

		MvcResult bookData2 = createBook("TestAuthor2");

		MvcResult getAllResult2 = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/books").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		List<Book> getAllBooksResult2 = objectMapper.readValue(getAllResult2.getResponse().getContentAsString(),
				new com.fasterxml.jackson.core.type.TypeReference<List<Book>>() {
				});

		assertEquals(2, getAllBooksResult2.size());

	}

	@Test
	public void getSingle_idLessThanOne_returnsBadRequest() throws Exception {
		MvcResult getSingleResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/books/0").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals(getSingleResult.getResponse().getStatus(), 400);
	}

}