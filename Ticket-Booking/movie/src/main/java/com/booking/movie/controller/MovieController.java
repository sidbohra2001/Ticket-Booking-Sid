package com.booking.movie.controller;

import com.booking.movie.dto.MovieDto;
import com.booking.movie.exceptions.AlreadyExistsException;
import com.booking.movie.exceptions.InvalidIdException;
import com.booking.movie.exceptions.NoDataException;
import com.booking.movie.model.Movie;
import com.booking.movie.sevice.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Movie")
public class MovieController {

    @Autowired private MovieService service;

    @PostMapping("/")
    @Operation(summary = "Add a new Movie")
    public ResponseEntity<Movie> add(@Valid @RequestBody MovieDto movieDto) throws AlreadyExistsException {
        Movie movie = Movie.builder().name(movieDto.getName()).build();
        return new ResponseEntity<>(service.add(movie), HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Operation(summary = "Update an existing Movie")
    public ResponseEntity<Movie> update(@Valid @RequestBody Movie movie) throws InvalidIdException {
        return new ResponseEntity<>(service.update(movie), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an existing Movie")
    public ResponseEntity<Movie> get(@PathVariable("id") int id) throws NoDataException {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an existing Movie")
    public void delete(@PathVariable("id") int id) throws InvalidIdException {
        service.delete(id);
    }

}
