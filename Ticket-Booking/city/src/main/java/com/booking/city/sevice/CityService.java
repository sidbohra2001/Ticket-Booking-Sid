package com.booking.city.sevice;

import com.booking.city.exceptions.AlreadyExistsException;
import com.booking.city.exceptions.InvalidIdException;
import com.booking.city.exceptions.NoDataException;
import com.booking.city.model.City;

public interface CityService {
    public City add(City city) throws AlreadyExistsException;
    public City update(City city) throws InvalidIdException;
    public City get(int id) throws NoDataException;
    public void delete(int id) throws InvalidIdException;
}
