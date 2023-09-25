package com.booking.ticket.repo;

import com.booking.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    Optional<List<Ticket>> findByUser(String user);
}
