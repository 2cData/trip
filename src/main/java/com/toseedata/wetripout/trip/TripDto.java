package com.toseedata.wetripout.trip;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TripDto {
    @NonNull
    private UUID id;

    @NonNull
    private String userName;

    @NonNull
    private String name;
}
