package com.toseedata.wetripout.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface TripRepository extends JpaRepository<Trip, UUID> {
    List<Trip> findTripsByName(final String name);

    Trip findTripByUserName(final String userName);
}
