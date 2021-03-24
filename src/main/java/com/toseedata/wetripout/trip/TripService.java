package com.toseedata.wetripout.trip;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface TripService {
    void add(@Validated @NotNull final Trip trip) throws IllegalArgumentException;

    UUID addAndGetID(@Validated @NotNull final Trip trip) throws IllegalArgumentException;

    //TODO can I do an upsert?
    void update(@Validated @NotNull final Trip trip) throws IllegalArgumentException;

    void delete(@Validated @NotNull final Trip trip) throws IllegalArgumentException;

    void deleteAll(@Validated final List<Trip> trips) throws IllegalArgumentException;

    Trip getById(@NotBlank final UUID id) throws IllegalArgumentException;

    List<Trip> getTripsByUserName(@NotBlank final String userName) throws IllegalArgumentException;

    List<Trip> getTripsByName(@NotBlank final String name) throws IllegalArgumentException;
}
