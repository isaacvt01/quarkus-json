package com.example;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
@ApplicationScoped
public class FruitRepository implements PanacheRepository<Fruit> {
    public Optional<Fruit> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public void update(Fruit entity) {
        Fruit fruit = findById(entity.id);
        fruit.setName(entity.getName());
        fruit.setDescription(entity.getDescription());
    }

    public void deleteByName(String name) {
        delete("name", name);
    }
}
