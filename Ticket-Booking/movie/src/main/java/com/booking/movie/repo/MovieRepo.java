package com.booking.movie.repo;

import com.booking.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
    boolean existsByName(String name);
}
