package com.sap.cc.movies;

import java.util.List;
import java.util.Optional;
import static com.sap.cc.movies.MovieFixtures.MOVIES;

public class MovieStorageStub implements MovieStorage {

	List<Movie> movies = MOVIES;
	
	public MovieStorageStub() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Movie> getAll() {
		return movies;
	}

	@Override
	public Optional<Movie> get(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Movie save(Movie movie) {
		// TODO Auto-generated method stub
		return null;
	}

}
