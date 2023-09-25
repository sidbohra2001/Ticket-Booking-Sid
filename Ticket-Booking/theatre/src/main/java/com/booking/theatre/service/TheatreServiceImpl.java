package com.booking.theatre.service;

import com.booking.theatre.configuration.ApplicationConfig;
import com.booking.theatre.dto.CityDto;
import com.booking.theatre.dto.MovieDto;
import com.booking.theatre.exceptions.InvalidIdException;
import com.booking.theatre.exceptions.NoDataException;
import com.booking.theatre.model.Theatre;
import com.booking.theatre.repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TheatreServiceImpl implements TheatreService {
    @Autowired private ApplicationConfig config;
    @Autowired private TheatreRepo repo;
    @Autowired private RestTemplate rest;
    private String cityUrl = "http://CITY-SERVICE/city/";
    private String movieUrl = "http://MOVIE-SERVICE/movie/";


    @Override
    public Theatre add(Theatre theatre) throws InvalidIdException, RestClientResponseException {
        if(checkCity(theatre.getCity())) throw new InvalidIdException("Invalid city Id "+theatre.getCity());
        for(int movie : theatre.getMovies()){
            if(checkMovie(movie)) throw new InvalidIdException("Invalid movie Id "+movie);
        }
        return repo.save(theatre);
    }

    @Override
    public Theatre update(Theatre theatre) throws InvalidIdException, RestClientResponseException {
        if(!repo.existsById(theatre.getId())) throw new InvalidIdException("Invalid theatre Id "+theatre.getId());
        if(checkCity(theatre.getCity())) throw new InvalidIdException("Invalid city Id "+theatre.getCity());
        for(int movie : theatre.getMovies()){
            if(checkMovie(movie)) throw new InvalidIdException("Invalid movie Id "+movie);
        }
        return repo.save(theatre);
    }

    @Override
    public Theatre get(int id) throws NoDataException {
        return repo.findById(id).orElseThrow(()->new NoDataException("No theatre found for id "+id));
    }

    @Override
    public List<Theatre> getByCity(int city) throws NoDataException {
        List<Theatre> theatres = repo.findByCity(city);
        if(theatres.isEmpty()) throw new NoDataException("No theatre(s) found for city "+city);
        return theatres;
    }

    @Override
    public void delete(int id) throws InvalidIdException {
        if(!repo.existsById(id)) throw new InvalidIdException("Invalid theatre Id "+id);
        repo.deleteById(id);
    }

    @Override
    public void deleteByCity(int city) throws NoDataException {
        List<Theatre> theatres = repo.findByCity(city);
        if(theatres.isEmpty()) throw new NoDataException("No theatre(s) found for city "+city);
        repo.deleteAllByCity(city);
    }

    private boolean checkCity(int city) throws RestClientResponseException{
//        if(config.getConfigValue("spring.profiles.active").equals("docker")) cityUrl = "http://city:2222/city/";
        HttpStatusCode statusCode = rest.getForEntity(cityUrl+city, CityDto.class).getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }


    private boolean checkMovie(int movie) {
//        if(config.getConfigValue("spring.profiles.active").equals("docker")) movieUrl = "http://movie:3333/movie/";
        HttpStatusCode statusCode = rest.getForEntity(movieUrl+movie, MovieDto.class).getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }
}
