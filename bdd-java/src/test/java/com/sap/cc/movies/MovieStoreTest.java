package com.sap.cc.movies;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MovieStoreTest {

	private MovieStore movieStore;
	private List<Movie> movieSearchResult;
	
	@BeforeEach
	void setUp() {
		this.movieStore = new MovieStore(new InMemoryMovieStorage());
	}

	@Test
	void exactMatch() {
		// Given two movies titled "Forrest Gump" and "Titanic"
		// When "Forrest Gump" is searched
		// Then the results list consists of "Forrest Gump"
		
		givenTwoMoviesTitled("Forrest Gump", "Titanic");
		movieSearchResult = whenIsSearched("Forrest Gump");
		thenTheResultsListConsistsOf("Forrest Gump");
	}
	
	@Test
	void partialMatch() {
		// Given two movies titled "The Godfather" and "City of God" 
		// When "God" is searched 
		// Then the results list consists of "The Godfather" and "City of God"
		
		givenTwoMoviesTitled("The Godfather", "City of God");
		movieSearchResult = whenIsSearched("God");
		thenTheResultsListConsistsOf("God");
	}
	
	private void thenTheResultsListConsistsOf(String title) {
		for(Movie movie : movieSearchResult) {
			assertThat(movie.getTitle()).contains(title);
		}
	}

	private List<Movie> whenIsSearched(String query) {
		return movieStore.search(query);
	}

	private void givenTwoMoviesTitled(String title1, String title2) {
		Movie movie1 = new Movie(title1, null);
		Movie movie2 = new Movie(title2, null);

		movieStore.addMovie(movie1);
		movieStore.addMovie(movie2);
	}

}