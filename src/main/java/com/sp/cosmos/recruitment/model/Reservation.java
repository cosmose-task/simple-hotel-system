package com.sp.cosmos.recruitment.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn
    private Room room;
    @ManyToOne
    @JoinColumn
    private Customer customer;
    @NotNull
    @Column
    private LocalDate startTime;
    @NotNull
    @Column
    private LocalDate endTime;
    @Column
    private LocalDateTime creationTime;
    @Column
    private Boolean cancelled;

    @PrePersist
    public void prePersist(){
        this.setCreationTime(LocalDateTime.now());
        this.setCancelled(false);
    }
}
