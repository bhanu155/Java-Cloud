package com.sap.cc.movies;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieStore {
    private InMemoryMovieStorage movieStorage;

    public MovieStore(InMemoryMovieStorage movieStorage) {
        this.movieStorage = movieStorage;
    }

    public void addMovie(Movie movie) {
        movieStorage.save(movie);
    }

    public List<Movie> search(String query) {
    	Predicate<Movie> byTitle = movie -> movie.getTitle().contains(query);
        return movieStorage.getAll().stream().filter(byTitle).collect(Collectors.toList());
    }
}
