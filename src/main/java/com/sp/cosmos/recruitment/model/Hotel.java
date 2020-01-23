package com.sp.cosmos.recruitment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Hotel {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String city;
    @Column
    private String hotelName;
    @JsonIgnore
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<Room> rooms;
}
