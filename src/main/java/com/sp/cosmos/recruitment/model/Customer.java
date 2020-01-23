package com.sp.cosmos.recruitment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    @Column
    private String firstName;
    @NotEmpty
    @Column
    private String lastName;
    @Column
    private String description;
    @Column
    private LocalDateTime creationTime;
    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
}
