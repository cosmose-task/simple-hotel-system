package com.sp.cosmos.recruitment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.cosmos.recruitment.enums.RoomType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn
    private Hotel hotel;
    @Column
    private Boolean availability;
    @Column
    private Integer roomNumber;
    @Enumerated(EnumType.STRING)
    @Column
    private RoomType roomType;
    @Column
    private Integer dailyPrice;
    @JsonIgnore
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Reservation> reservations;

}
