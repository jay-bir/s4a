package com.example.s4abackend.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class BaggageTypeItems extends CargoSpaceItems{
}
