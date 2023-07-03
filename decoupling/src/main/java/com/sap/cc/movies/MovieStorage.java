package com.sap.cc.movies;

import java.util.List;
import java.util.Optional;

public interface MovieStorage {

	void delete(Long id);

	void deleteAll();

	List<Movie> getAll();

	Optional<Movie> get(Long id);

	Movie save(final Movie movie);

}
