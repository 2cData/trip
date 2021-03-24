package com.toseedata.wetripout.trip;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public final class TripDto {
    @NonNull
    UUID id;

    @NonNull
    String userName;

    @NonNull
    String name;

    String description;
}
