package com.booking.city.controller;

import com.booking.city.dto.CityDto;
import com.booking.city.exceptions.AlreadyExistsException;
import com.booking.city.exceptions.InvalidIdException;
import com.booking.city.exceptions.NoDataException;
import com.booking.city.model.City;
import com.booking.city.sevice.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "City")
public class CityController {

    @Autowired private CityService service;

    @PostMapping("/")
    @Operation(summary = "Add a new City")
    public ResponseEntity<City> add(@Valid @RequestBody CityDto cityDto) throws AlreadyExistsException {
        City city = City.builder().name(cityDto.getName()).build();
        return new ResponseEntity<>(service.add(city), HttpStatus.CREATED);
    }

    @PutMapping("/")
    @Operation(summary = "Update an existing City")
    public ResponseEntity<City> update(@Valid @RequestBody City city) throws InvalidIdException {
        return new ResponseEntity<>(service.update(city), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an existing City")
    public ResponseEntity<City> get(@PathVariable("id") int id) throws NoDataException {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an existing City")
    public void delete(@PathVariable("id") int id) throws InvalidIdException {
        service.delete(id);
    }

}
