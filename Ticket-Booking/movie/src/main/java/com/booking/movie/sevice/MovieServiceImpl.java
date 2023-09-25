package com.booking.movie.sevice;

import com.booking.movie.exceptions.AlreadyExistsException;
import com.booking.movie.exceptions.InvalidIdException;
import com.booking.movie.exceptions.NoDataException;
import com.booking.movie.model.Movie;
import com.booking.movie.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieServiceImpl implements MovieService {
    @Autowired private MovieRepo repo;
    @Override
    public Movie add(Movie movie) throws AlreadyExistsException {
        if(repo.existsByName(movie.getName())) throw new AlreadyExistsException("Movie "+movie.getName()+" already exists");
        return repo.save(movie);
    }

    @Override
    public Movie update(Movie movie) throws InvalidIdException {
        if(!repo.existsById(movie.getId())) throw new InvalidIdException("Invalid Id "+movie.getId());
        return repo.save(movie);
    }

    @Override
    public Movie get(int id) throws NoDataException {
        return repo.findById(id).orElseThrow(()->new NoDataException("No movie found for Id "+id));
    }

    @Override
    public void delete(int id) throws InvalidIdException {
        if(!repo.existsById(id)) throw new InvalidIdException("Invalid Id "+id);
        repo.deleteById(id);
    }
}
