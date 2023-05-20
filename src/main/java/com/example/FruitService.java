package com.example;

import jakarta.ejb.ApplicationException;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@ApplicationScoped
public class FruitService {

    public List<Fruit> list() {
        return Fruit.listAll();
    }

    public void add(Fruit fruit) {
        fruit.persist();
    }
    public void removeByName(String name) {
        Fruit fruit = Fruit.find("name", name).firstResult();
        fruit.delete();
    }

    public void removeById(Long id) {
        Fruit fruit = Fruit.findById(id);
        fruit.delete();
    }

    public void update(Fruit fruit) {
        Fruit fruit1 = Fruit.findById(fruit.id);
        fruit1.name = fruit.name;
        fruit1.description = fruit.description;
        fruit1.persist();
    }

    public Optional<Fruit> get(String name) {
        return Fruit.find("name", name).firstResultOptional();
    }

    public Fruit findById(Long id) {
        return Fruit.findById(id);
    }

    public Long count() {
        return Fruit.count();
    }
}
