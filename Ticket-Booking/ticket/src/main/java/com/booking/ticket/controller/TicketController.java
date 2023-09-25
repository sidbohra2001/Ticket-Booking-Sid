package com.booking.ticket.controller;

import com.booking.ticket.dto.TicketDto;
import com.booking.ticket.exceptions.*;
import com.booking.ticket.model.Ticket;
import com.booking.ticket.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@RestController
@Tag(name = "Ticket")
public class TicketController {
    
    @Autowired private TicketService service;

    @PostMapping("/")
    @Operation(summary = "Add a new Ticket")
    public ResponseEntity<Ticket> add(@Valid @RequestBody TicketDto ticketDto) throws InvalidIdException, RestClientResponseException {
        Ticket ticket = Ticket.builder().user(ticketDto.getUser()).city(ticketDto.getCity()).theatre(ticketDto.getTheatre()).movie(ticketDto.getMovie()).build();
        return new ResponseEntity<>(service.add(ticket), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an existing Ticket")
    public ResponseEntity<Ticket> get(@PathVariable("id") int id) throws InvalidIdException {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all Ticket(s) by User")
    public ResponseEntity<List<Ticket>> getByCity(@RequestParam("user") String user) throws NoDataException {
        return new ResponseEntity<>(service.getByUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an existing Ticket")
    public void delete(@PathVariable("id") int id) throws InvalidIdException {
        service.delete(id);
    }
    
}
