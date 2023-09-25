package com.booking.movie;

import com.booking.movie.exceptions.AlreadyExistsException;
import com.booking.movie.exceptions.InvalidIdException;
import com.booking.movie.exceptions.NoDataException;
import com.booking.movie.model.Movie;
import com.booking.movie.repo.MovieRepo;
import com.booking.movie.sevice.MovieServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieApplicationTests {

	@Mock
	private MovieRepo repo;
	@InjectMocks
	private MovieServiceImpl service;

	@Test
	@DisplayName("Add Movie Success Test")
	public void addMovieSuccessTest() throws AlreadyExistsException {
		Movie movie = Movie.builder().id(1).name("test_movie").build();
		when(repo.existsByName(Mockito.anyString())).thenReturn(false);
		when(repo.save(Mockito.any(Movie.class))).thenReturn(movie);
		assertEquals(movie.getName(), service.add(movie).getName());
	}

	@Test
	@DisplayName("Add Movie AlreadyExistsException Test")
	public void addMovieAlreadyExistsExceptionTest() throws AlreadyExistsException {
		Movie movie = Movie.builder().id(1).name("test_movie").build();
		when(repo.existsByName(Mockito.anyString())).thenReturn(true);
		assertThrowsExactly(AlreadyExistsException.class, () -> service.add(movie));
	}

	@Test
	@DisplayName("Update Movie Success")
	public void updateMovieSuccessTest() throws InvalidIdException {
		Movie movie = Movie.builder().id(1).name("test_movie").build();
		when(repo.existsById(Mockito.anyInt())).thenReturn(true);
		when(repo.save(Mockito.any(Movie.class))).thenReturn(movie);
		assertEquals(movie.getName(), service.update(movie).getName());
	}

	@Test
	@DisplayName("Update Movie InvalidIdException Test")
	public void updateMovieInvalidIdExceptionTest() throws InvalidIdException {
		Movie movie = Movie.builder().id(1).name("test_movie").build();
		when(repo.existsById(Mockito.anyInt())).thenReturn(false);
		assertThrowsExactly(InvalidIdException.class, () -> service.update(movie));
	}

	@Test
	@DisplayName("Get Movie Success")
	public void getMovieSuccessTest() throws NoDataException {
		Movie movie = Movie.builder().id(1).name("test_movie").build();
		when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(movie));
		assertEquals(movie.getName(), service.get(movie.getId()).getName());
	}

	@Test
	@DisplayName("Get Movie NoDataException Test")
	public void getMovieNoDataExceptionTest() throws NoDataException {
		when(repo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		assertThrowsExactly(NoDataException.class, () -> service.get(1));
	}

	@Test
	@DisplayName("Delete Movie InvalidIdException Test")
	public void deleteMovieInvalidIdExceptionTest() throws InvalidIdException {
		when(repo.existsById(Mockito.anyInt())).thenReturn(false);
		assertThrowsExactly(InvalidIdException.class, () -> service.delete(1));
	}

}
