package com.example.s4abackend.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("c")
public class CargoTypeItems extends CargoSpaceItems {
}
