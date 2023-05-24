package com.example;

import jakarta.ejb.ApplicationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@ApplicationScoped
public class FruitService {

    @Inject
    FruitRepository fruitRepository;
    public List<Fruit> list() {
        return fruitRepository.listAll();
    }

    public void add(Fruit fruit) {
        fruitRepository.persist(fruit);
    }
    public void removeByName(String name) {
        fruitRepository.deleteByName(name);
    }

    public void removeById(Long id) {
        fruitRepository.deleteById(id);
    }

    public void update(Fruit fruit) {
        fruitRepository.update(fruit);
    }

    public Optional<Fruit> get(String name) {
        return fruitRepository.findByName(name);
    }

    public Fruit findById(Long id) {
        return fruitRepository.findById(id);
    }

    public Long count() {
        return fruitRepository.count();
    }
}
