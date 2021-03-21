package com.toseedata.wetripout.trip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TripRepositoryTest {

    @Autowired
    TripRepository repository;

    @Test
    public void whenDefaultConstructor_thenTripSaved() {
        //given
        Trip trip = Trip.builder().userName("valid@email.com").name("12345").build();

        //when
        repository.deleteAll();
        repository.save(trip);

        //then
        assertNotNull(trip.getId());
        assertEquals(repository.count(), 1);
    }

    @Test
    public void whenSearchByValidID_thenRetrieveOne() {
        //given
        Trip trip = Trip.builder().userName("valid@email.com").name("12345").build();
        repository.deleteAll();
        repository.save(trip);
        UUID id = trip.getId();

        //when
        Trip found = repository.findById(id).get();

        //then
        assertEquals(trip.getId(), found.getId());
        assertEquals(repository.count(), 1);
    }

    @Test
    public void whenSearchByInvalidID_thenRetrieveNull() {
        //given
        Trip trip = Trip.builder().userName("valid@email.com").name("12345").build();
        repository.deleteAll();
        repository.save(trip);
        UUID id = trip.getId();

        //when
        Trip found = repository.findById(UUID.randomUUID()).orElse(null);

        //then
        assertNull(found);
        assertEquals(repository.count(), 1);
    }


    public void whenSearchByInvalidID_thenExisitIsFalse() {
        //given
        Trip trip = Trip.builder().userName("valid@email.com").name("12345").build();
        repository.deleteAll();
        repository.save(trip);
        UUID id = trip.getId();

        //when
        boolean found = repository.existsById(UUID.randomUUID());

        //then
        assertFalse(found);
        assertEquals(repository.count(), 1);
    }

    @Test
    public void whenSearchByNameWildcard_thenRetrieveMultiple() {
        //given
        Trip trip1 = Trip.builder().userName("seedABC@email.com").name("seedABC").build();
        Trip trip2 = Trip.builder().userName("seedDEF@email.com").name("seedDEF").build();

        //when
        repository.deleteAll();
        repository.save(trip1);
        repository.save(trip2);

        //then
        repository.findTripsByName("seed%");
        assertEquals(repository.count(), 2);
    }

    @Test()
    public void whenAddUsername_thenRetrieveOne() {
        //given
        Trip trip = Trip.builder().userName("test@email.com").name("seedABC").build();

        //when
        repository.deleteAll();
        repository.save(trip);

        Trip result = repository.findTripByUserName(trip.getUserName());
        assertEquals(result.getUserName(), trip.getUserName());
        assertEquals(repository.count(), 1);
    }

    @Test()
    public void whenAddDuplicateUsername_thenThrowError() {
        //given
        Trip trip1 = Trip.builder().userName("test@email.com").name("seedABC").build();
        Trip trip2 = Trip.builder().userName("test@email.com").name("seedDEF").build();

        //when
        repository.deleteAll();
        repository.save(trip1);

        repository.findTripByUserName(trip1.getUserName());
        assertEquals(repository.count(), 1);

        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(trip2);
            repository.flush();
        });


    }

}
