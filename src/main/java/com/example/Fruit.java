package com.example;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@EqualsAndHashCode(callSuper = true)
@Data // Se genera los getters, setters, equals, hashcode, toString y contructor vacío.
public class Fruit extends PanacheEntity {
    public String name;
    public String description;
}
