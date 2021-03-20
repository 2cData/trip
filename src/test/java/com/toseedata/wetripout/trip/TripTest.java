package com.toseedata.wetripout.trip;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Slf4j
public class TripTest {
    private final String userNameVal = "test@test.com";
    private final String nameVal = "This is a test trip";
    private final Trip baseTrip = new Trip(this.userNameVal, this.nameVal);
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final Properties properties = new Properties();

    public TripTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("ValidationMessages.properties");
        this.properties.load(inputStream);
    }

    @Test
    void whenDefaultConstructor_thenTripCreated() {
        //given
        Trip trip = baseTrip;

        //when
        UUID id = trip.getId();

        //then
        assertNotNull(id);
    }

    @Test
    void whenUserNameValidEmail_thenValidationSucceeds() {
        //given
        Trip trip = Trip.builder().userName(this.userNameVal).name(this.nameVal).build();

        //when
        Set<ConstraintViolation<Trip>> violations = validator.validate(trip);

        //then
        assertEquals(0, violations.size());
    }

    @Test
    void whenUserNameIsNull_thenValidationFails() {
        //given
        Trip trip = Trip.builder().userName(null).name(this.nameVal).build();

        //when
        Set<ConstraintViolation<Trip>> violations = validator.validate(trip);

        //then
        assertEquals(1, violations.size());
        assertEquals(properties.getProperty("NotNull.trip.username"), violations.iterator().next().getMessage());
    }

    @Test
    void whenUserNameIsEmpty_thenValidationFails() {
        //given
        Trip trip = Trip.builder().userName("  ").name(this.nameVal).build();

        //when
        Set<ConstraintViolation<Trip>> violations = validator.validate(trip);

        //then
        assertEquals(1, violations.size());
        assertEquals(properties.getProperty("Email.trip.username"), violations.iterator().next().getMessage());
    }

    @Test
    void whenUserNameNotValidEmail_thenValidationFails() {
        //given
        Trip trip = Trip.builder().userName("not an email").name(this.nameVal).build();

        //when
        Set<ConstraintViolation<Trip>> violations = validator.validate(trip);

        //then
        assertEquals(1, violations.size());
        assertEquals(properties.getProperty("Email.trip.username"), violations.iterator().next().getMessage());
    }

    @Test
    void whenNameIsNull_thenValidationFails() {
        // given
        Trip trip = Trip.builder().userName(this.userNameVal).name(null).build();

        //when
        Set<ConstraintViolation<Trip>> violations = validator.validate(trip);

        //then
        assertEquals(1, violations.size());
        assertEquals(properties.getProperty("NotNull.trip.name"), violations.iterator().next().getMessage());
    }

    @Test
    void whenNameIsEmpty_thenValidationFails() {
        // given
        Trip trip = Trip.builder().userName(this.userNameVal).name("  ").build();

        //when
        Set<ConstraintViolation<Trip>> violations = validator.validate(trip);

        //then
        assertEquals(1, violations.size());
        assertEquals(properties.getProperty("Size.trip.name"), violations.iterator().next().getMessage());
    }

    @Test
    void whenNameIsAboveMax_thenValidationFails() {
        // given
        String test = "seed";
        int size = 1;
        while (size < 100) {
            test = test.concat(String.valueOf(size));
            size++;
        }

        Trip trip = Trip.builder().userName(this.userNameVal).name(test).build();

        //when
        Set<ConstraintViolation<Trip>> violations = validator.validate(trip);

        //then
        assertEquals(1, violations.size());
        assertEquals(properties.getProperty("Size.trip.name"), violations.iterator().next().getMessage());
    }

    @Test
    void whenNameIsBelowMin_thenValidationFails() {
        // given
        Trip trip = Trip.builder().userName(this.userNameVal).name("ABC").build();

        //when
        Set<ConstraintViolation<Trip>> violations = validator.validate(trip);

        //then
        assertEquals(1, violations.size());
        assertEquals(properties.getProperty("Size.trip.name"), violations.iterator().next().getMessage());
    }

    @Test
    void whenNameHasSpecialCharacters_thenValidationFails() {
        //given
        Trip trip = Trip.builder().userName(this.userNameVal).name("~!@#$%^&*(").build();

        //when
        Set<ConstraintViolation<Trip>> violations = validator.validate(trip);

        //then
        assertEquals(1, violations.size());
        assertEquals("Invalid characters", violations.iterator().next().getMessage());
    }

}
