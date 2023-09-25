package com.booking.ticket.service;

import com.booking.ticket.configuration.ApplicationConfig;
import com.booking.ticket.dto.CityDto;
import com.booking.ticket.dto.MovieDto;
import com.booking.ticket.dto.TheatreDto;
import com.booking.ticket.exceptions.InvalidIdException;
import com.booking.ticket.exceptions.NoDataException;
import com.booking.ticket.model.Ticket;
import com.booking.ticket.repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Component
public class TicketServiceImpl implements TicketService {

    private String cityUrl = "http://CITY-SERVICE/city/";
    private String movieUrl = "http://MOVIE-SERVICE/movie/";
    private String theatreUrl = "http://THEATRE-SERVICE/theatre/";
    @Autowired
    private ApplicationConfig config;
    @Autowired
    private TicketRepo repo;
    @Autowired
    private RestTemplate rest;

    @Override
    public Ticket add(Ticket ticket) throws InvalidIdException, RestClientResponseException {
        if (checkCity(ticket.getCity())) throw new InvalidIdException("Invalid city Id " + ticket.getCity());
        TheatreDto theatreDto = getTheatre(ticket.getTheatre());
        if (theatreDto == null) throw new InvalidIdException("Invalid theatre Id " + ticket.getTheatre());
        if (theatreDto.getCity() != ticket.getCity())
            throw new InvalidIdException("Invalid theatre Id " + ticket.getTheatre() + " for city Id " + ticket.getCity());
        if (checkMovie(ticket.getMovie())) throw new InvalidIdException("Invalid movie Id " + ticket.getMovie());
        ticket.setSeat((int) Math.round(Math.random() * 199 + 1));
        ticket.setDate(LocalDate.now().plusDays(5));
        return repo.save(ticket);
    }

    @Override
    public Ticket get(int id) throws InvalidIdException {
        return repo.findById(id).orElseThrow(() -> new InvalidIdException("Invalid Id " + id));
    }

    @Override
    public List<Ticket> getByUser(String user) throws NoDataException {
        return repo.findByUser(user).orElseThrow(() -> new NoDataException("No ticket(s) found for user " + user));
    }

    @Override
    public void delete(int id) throws InvalidIdException {
        if (!repo.existsById(id)) throw new InvalidIdException("Invalid ticket Id " + id);
        repo.deleteById(id);
    }

    private boolean checkCity(int city) throws RestClientResponseException {
//        if (config.getConfigValue("spring.profiles.active").equals("docker")) cityUrl = "http://city:2222/city/";
        HttpStatusCode statusCode = rest.getForEntity(cityUrl + city, CityDto.class).getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }


    private boolean checkMovie(int movie) throws RestClientResponseException {
//        if (config.getConfigValue("spring.profiles.active").equals("docker")) movieUrl = "http://movie:3333/movie/";
        HttpStatusCode statusCode = rest.getForEntity(movieUrl + movie, MovieDto.class).getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    private TheatreDto getTheatre(int theatre) throws RestClientResponseException {
//        if (config.getConfigValue("spring.profiles.active").equals("docker"))
//            theatreUrl = "http://theatre:4444/theatre/";
        return rest.getForEntity(theatreUrl + theatre, TheatreDto.class).getBody();
    }
}
