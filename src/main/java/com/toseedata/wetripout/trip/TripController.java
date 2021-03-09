package com.toseedata.wetripout.trip;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final Trip trip = new Trip("hello");

    @GetMapping
    public Trip getTrip() {
        return trip;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrip(@RequestBody final String name) {
        Trip newTrip = new Trip(name);
    }

    @DeleteMapping
    public void deleteTrip() {
        // nothing
    }
}
