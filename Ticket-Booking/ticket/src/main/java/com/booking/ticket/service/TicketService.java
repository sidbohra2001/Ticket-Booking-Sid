package com.booking.ticket.service;

import com.booking.ticket.exceptions.InvalidIdException;
import com.booking.ticket.exceptions.NoDataException;
import com.booking.ticket.model.Ticket;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

public interface TicketService {
    public Ticket add(Ticket ticket) throws InvalidIdException, RestClientResponseException;

    public Ticket get(int id) throws InvalidIdException;

    public List<Ticket> getByUser(String user) throws NoDataException;

    public void delete(int id) throws InvalidIdException;
}
