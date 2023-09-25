package com.booking.theatre.service;

import com.booking.theatre.exceptions.InvalidIdException;
import com.booking.theatre.exceptions.NoDataException;
import com.booking.theatre.model.Theatre;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

public interface TheatreService {
    public Theatre add(Theatre theatre) throws InvalidIdException, RestClientResponseException;
    public Theatre update(Theatre theatre) throws InvalidIdException, RestClientResponseException;
    public Theatre get(int id) throws NoDataException;
    public List<Theatre> getByCity(int city) throws NoDataException;
    public void delete(int id) throws InvalidIdException;
    public void deleteByCity(int city) throws NoDataException;
}
