package com.booking.theatre.controller;

import com.booking.theatre.dto.TheatreDto;
import com.booking.theatre.exceptions.InvalidIdException;
import com.booking.theatre.exceptions.NoDataException;
import com.booking.theatre.model.Theatre;
import com.booking.theatre.service.TheatreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@RestController
@Tag(name = "Theatre")
public class TheatreController {

    @Autowired
    private TheatreService service;

    @PostMapping("/")
    @Operation(summary = "Add a new Theatre")
    public ResponseEntity<Theatre> add(@Valid @RequestBody TheatreDto theatreDto) throws InvalidIdException, RestClientResponseException {
        Theatre theatre = Theatre.builder().name(theatreDto.getName()).city(theatreDto.getCity()).movies(theatreDto.getMovies()).build();
        return new ResponseEntity<>(service.add(theatre), HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Operation(summary = "Update an existing Theatre")
    public ResponseEntity<Theatre> update(@Valid @RequestBody Theatre theatre) throws InvalidIdException, RestClientResponseException {
        return new ResponseEntity<>(service.update(theatre), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an existing Theatre")
    public ResponseEntity<Theatre> get(@PathVariable("id") int id) throws NoDataException {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all Theatre(s) by City")
    public ResponseEntity<List<Theatre>> getByCity(@RequestParam("city") int city) throws NoDataException {
        return new ResponseEntity<>(service.getByCity(city), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an existing Theatre")
    public void delete(@PathVariable("id") int id) throws InvalidIdException {
        service.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Get all Theatre(s) by City")
    public void deleteByCity(@RequestParam("city") int city) throws NoDataException {
        service.deleteByCity(city);
    }

}
