package com.booking.city.repo;

import com.booking.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Integer> {
    boolean existsByName(String name);
}
