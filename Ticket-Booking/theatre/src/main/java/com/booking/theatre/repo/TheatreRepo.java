package com.booking.theatre.repo;

import com.booking.theatre.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepo extends JpaRepository<Theatre, Integer> {
    void deleteAllByCity(int city);
    List<Theatre> findByCity(int city);
}
