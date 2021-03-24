package com.toseedata.wetripout.trip;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@Slf4j
public final class TripServiceImpl implements TripService {

    private final TripRepository repository;

    TripServiceImpl(TripRepository repository) {
        this.repository = repository;
    }

    /**
     * Add a new Trip
     *
     * @param trip single valid Trip object
     * @throws IllegalArgumentException if parameter is null or if the argument fails validation
     */
    public void add(@Validated @NotNull final Trip trip) throws IllegalArgumentException {
        log.info("Add trip with name {}", trip.getName());

        UUID id = addAndGetID(trip);
    }

    /**
     * Add new Trip and get back ID
     *
     * @param trip single valid Trip object
     * @return UUID id of new trip
     * @throws IllegalArgumentException if parameter is null or if the argument fails validation
     */
    public UUID addAndGetID(@Validated @NotNull final Trip trip) throws IllegalArgumentException {
        log.info("Add trip and return id {}", trip.getId());
        repository.save(trip);
        repository.flush();
        return trip.getId();
    }

    @Override
    public void update(@NotNull Trip trip) throws IllegalArgumentException {
        log.info("Update trip ");
        UUID id = addAndGetID(trip);
    }

    /**
     * Delete a single trip
     *
     * @param trip single valid Trip object
     * @throws IllegalArgumentException if parameter is null or if the argument fails validation
     */
    public void delete(@Validated @NotNull final Trip trip) throws IllegalArgumentException {
        log.info("Delete trip with id {}", trip.getId());
        repository.delete(trip);
    }

    /**
     * Delete 1..n trips
     *
     * @param trips list of valid Trip object
     * @throws IllegalArgumentException if parameter is null or if any of the trips in the list fail validation
     */
    public void deleteAll(@Validated final List<Trip> trips) throws IllegalArgumentException {
        log.info("Delete {} trips", trips.size());
        repository.deleteAll(trips);
    }

    /**
     * Return a single instance of a trip by ID
     *
     * @param id
     * @return a single Trip
     * @throws IllegalArgumentException if parameter is blank or does not exist
     */
    public Trip getById(@NotBlank final UUID id) throws IllegalArgumentException {
        log.info("Get trip with id {}", id);

        return repository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("We could not find the trip you requested."));
    }

    /**
     * Return list of trips by username
     *
     * @param userName valid email
     * @return List of 0..n trips
     * @throws IllegalArgumentException if userName is blank
     */
    public List<Trip> getTripsByUserName(@NotBlank final String userName) throws IllegalArgumentException {
        log.info("Get trips with user name {}", userName);

        return repository
                .findTripsByUserName(userName);
    }

    /**
     * Return list of trips by name
     *
     * @param name valid trip name
     * @return List of 0..n trips
     * @throws IllegalArgumentException if name is blank
     */
    public List<Trip> getTripsByName(@NotBlank final String name) throws IllegalArgumentException {
        log.info("Get trips with name {}", name);

        return repository
                .findTripsByName(name);
    }
}
