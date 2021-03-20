package com.toseedata.wetripout.trip;


import com.toseedata.wetripout.trip.utils.EscapeCharacterConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
public class Trip {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;
    @NotNull(message = "{NotNull.trip.username}")
    @Email(message = "{Email.trip.username}")
    @Column()
    private String userName;
    @NotNull(message = "{NotNull.trip.name}")
    @Size(min = 5, max = 100, message = "{Size.trip.name}")
    @EscapeCharacterConstraint
    @Column()
    private String name;
    @EscapeCharacterConstraint
    private String description;
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;
    @Column(nullable = false)
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
