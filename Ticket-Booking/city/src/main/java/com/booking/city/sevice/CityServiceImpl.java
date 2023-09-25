package com.booking.city.sevice;

import com.booking.city.exceptions.AlreadyExistsException;
import com.booking.city.exceptions.InvalidIdException;
import com.booking.city.exceptions.NoDataException;
import com.booking.city.model.City;
import com.booking.city.repo.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityServiceImpl implements CityService{
    @Autowired private CityRepo repo;
    @Override
    public City add(City city) throws AlreadyExistsException {
        if(repo.existsByName(city.getName())) throw new AlreadyExistsException("City "+city.getName()+" already exists");
        return repo.save(city);
    }

    @Override
    public City update(City city) throws InvalidIdException {
        if(!repo.existsById(city.getId())) throw new InvalidIdException("Invalid Id "+city.getId());
        return repo.save(city);
    }

    @Override
    public City get(int id) throws NoDataException {
        return repo.findById(id).orElseThrow(()->new NoDataException("No city found for Id "+id));
    }

    @Override
    public void delete(int id) throws InvalidIdException {
        if(!repo.existsById(id)) throw new InvalidIdException("Invalid Id "+id);
        repo.deleteById(id);
    }
}
