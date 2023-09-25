package com.booking.movie.sevice;

import com.booking.movie.exceptions.*;
import com.booking.movie.model.Movie;

public interface MovieService {
    public Movie add(Movie city) throws AlreadyExistsException;
    public Movie update(Movie city) throws InvalidIdException;
    public Movie get(int id) throws NoDataException;
    public void delete(int id) throws InvalidIdException;
}
