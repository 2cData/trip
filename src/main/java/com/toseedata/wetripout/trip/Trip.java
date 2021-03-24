package com.toseedata.wetripout.trip;


import com.toseedata.wetripout.trip.utils.Auditable;
import com.toseedata.wetripout.trip.utils.EscapeCharacterConstraint;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table(name = "TRIP", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Table(name = "TRIP")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Trip extends Auditable<String> {

    @javax.persistence.Id
    @GeneratedValue
    //@Id
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    //TODO the username is not unique AT THE TRIP LEVEL
    //TODO make a test for updating
    // Tomorrow, make the user object
    //@Unique
    @NotNull(message = "{NotNull.trip.userName}")
    @Email(message = "{Email.trip.userName}")
    @Column(name = "USERNAME", nullable = false, updatable = false)
    private String userName;

    @NotNull(message = "{NotNull.trip.name}")
    @Size(min = 5, max = 100, message = "{Size.trip.name}")
    @EscapeCharacterConstraint
    @Column(name = "NAME", nullable = false)
    private String name;

    @EscapeCharacterConstraint
    @Column(name = "DESCRIPTION")
    private String description;

    public Trip(final String userName, final String name) {
        this.id = UUID.randomUUID();
        this.userName = userName;
        this.name = name;
    }

}
