package com.booking.city;

import com.booking.city.exceptions.AlreadyExistsException;
import com.booking.city.exceptions.InvalidIdException;
import com.booking.city.exceptions.NoDataException;
import com.booking.city.model.City;
import com.booking.city.repo.CityRepo;
import com.booking.city.sevice.CityServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityApplicationTests {

    @Mock
    private CityRepo repo;
    @InjectMocks
    private CityServiceImpl service;

    @Test
    @DisplayName("Add City Success Test")
    public void addCitySuccessTest() throws AlreadyExistsException {
        City city = City.builder().id(1).name("test_city").build();
        when(repo.existsByName(Mockito.anyString())).thenReturn(false);
        when(repo.save(Mockito.any(City.class))).thenReturn(city);
        assertEquals(city.getName(), service.add(city).getName());
    }

    @Test
    @DisplayName("Add City AlreadyExistsException Test")
    public void addCityAlreadyExistsExceptionTest() throws AlreadyExistsException {
        City city = City.builder().id(1).name("test_city").build();
        when(repo.existsByName(Mockito.anyString())).thenReturn(true);
        assertThrowsExactly(AlreadyExistsException.class, () -> service.add(city));
    }

    @Test
    @DisplayName("Update City Success")
    public void updateCitySuccessTest() throws InvalidIdException {
        City city = City.builder().id(1).name("test_city").build();
        when(repo.existsById(Mockito.anyInt())).thenReturn(true);
        when(repo.save(Mockito.any(City.class))).thenReturn(city);
        assertEquals(city.getName(), service.update(city).getName());
    }

    @Test
    @DisplayName("Update City InvalidIdException Test")
    public void updateCityInvalidIdExceptionTest() throws InvalidIdException {
        City city = City.builder().id(1).name("test_city").build();
        when(repo.existsById(Mockito.anyInt())).thenReturn(false);
        assertThrowsExactly(InvalidIdException.class, () -> service.update(city));
    }

    @Test
    @DisplayName("Get City Success")
    public void getCitySuccessTest() throws NoDataException {
        City city = City.builder().id(1).name("test_city").build();
        when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(city));
        assertEquals(city.getName(), service.get(city.getId()).getName());
    }

    @Test
    @DisplayName("Get City NoDataException Test")
    public void getCityNoDataExceptionTest() throws NoDataException {
        when(repo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertThrowsExactly(NoDataException.class, () -> service.get(1));
    }

    @Test
    @DisplayName("Delete City InvalidIdException Test")
    public void deleteCityInvalidIdExceptionTest() throws InvalidIdException {
        when(repo.existsById(Mockito.anyInt())).thenReturn(false);
        assertThrowsExactly(InvalidIdException.class, () -> service.delete(1));
    }

}
