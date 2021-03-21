package com.toseedata.wetripout.trip;


import com.toseedata.wetripout.trip.utils.EscapeCharacterConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Trip {

    @javax.persistence.Id
    @GeneratedValue
    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    @NotNull(message = "{NotNull.trip.userName}")
    @Email(message = "{Email.trip.userName}")
    @Unique
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

    @Column(name = "CREATED", nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(name = "UPDATED", nullable = false)
    private OffsetDateTime lastUpdated;

    public Trip(final String userName, final String name) {
        this.id = UUID.randomUUID();
        this.userName = userName;
        this.name = name;
        this.dateCreated = OffsetDateTime.now();
        this.lastUpdated = this.dateCreated;
    }

    @PrePersist
    public void prePersist() {
        this.dateCreated = OffsetDateTime.now();
        this.lastUpdated = this.dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdated = OffsetDateTime.now();
    }

}
