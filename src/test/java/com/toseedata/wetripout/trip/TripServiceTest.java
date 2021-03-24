package com.toseedata.wetripout.trip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @Mock
    private TripRepository repository;

    @InjectMocks
    private TripServiceImpl service;

    @BeforeEach
    void init(@Mock TripRepository repository) {
        this.repository = repository;
        this.service = new TripServiceImpl(this.repository);
        this.repository.deleteAll();
    }

    @Test
    public void whenAddValidTrip_thenSaveTrip() {
        //given
        Trip trip = Trip.builder().userName("user@name.com").name("name").build();

        //when
        service.add(trip);

        //then
        ArgumentCaptor<Trip> argumentCaptor = ArgumentCaptor.forClass(Trip.class);
        verify(repository).save(argumentCaptor.capture());
        //assertEquals(repository.count(), 1);
    }

    @Test
    public void whenAddNullTrip_thenDoNotSave() {
        //given
        Trip trip = null;

        //when
        assertThrows(NullPointerException.class, () -> {
            service.add(trip);
        });

        //then
        ArgumentCaptor<Trip> argumentCaptor = ArgumentCaptor.forClass(Trip.class);
        verify(repository, never()).save(trip);
    }

    @Test
    public void whenAddInvalidTrip_thenDoNotSave() {
        //given
        Trip trip = Trip.builder().userName("  ").name("  ").build();

        //when
        doReturn(trip).when(repository).save(any());
        UUID uuid = service.addAndGetID(trip);

        //then
        assertNull(uuid);
        //TODO I would rather see the error message gets thrown. Maybe Spy will help
    }

}
