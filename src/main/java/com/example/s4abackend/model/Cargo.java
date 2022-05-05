package com.example.s4abackend.model;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @Column(name = "flight_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
