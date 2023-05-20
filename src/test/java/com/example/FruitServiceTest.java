package com.example;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
@Transactional
public class FruitServiceTest {
    @Inject
    FruitService fruitService;

    @Test
    public void testList() {
        Fruit addedFruit = new Fruit();
        addedFruit.setName("Apple");
        addedFruit.setDescription("Winter");
        fruitService.add(addedFruit);
        List<Fruit> fruits = fruitService.list();
        Assertions.assertThat(fruits).isNotEmpty();
        Assertions.assertThat(fruitService.list().stream()
                        .filter(fruit -> null != fruit.getName())
                .anyMatch(fruit -> fruit.getName().equals("Apple")))
                .isTrue();

        fruitService.removeById(addedFruit.id);
    }

    @Test
    public void testAdd() {
        Fruit addedFruit = new Fruit();
        addedFruit.setName("Apple");
        addedFruit.setDescription("Winter");
        fruitService.add(addedFruit);
        Assertions.assertThat(fruitService.list().stream()
                        .filter(fruit -> null != fruit.getName())
                .anyMatch(fruit -> fruit.getName().equals("Apple")))
                .isTrue();
        fruitService.removeById(addedFruit.id);
    }

    @Test
    public void deleteFruitById() throws Exception {
        Fruit addedFruit = new Fruit();
        addedFruit.setName("Apple");
        addedFruit.setDescription("Winter");
        fruitService.add(addedFruit);
        Assertions.assertThat(fruitService.list().stream()
                        .filter(fruit -> null != fruit.getName())
                .anyMatch(fruit -> fruit.getName().equals("Apple")))
                .isTrue();
        fruitService.removeById(addedFruit.id);
        Assertions.assertThat(fruitService.list().stream()
                        .filter(fruit -> null != fruit.getName())
                .anyMatch(fruit -> fruit.getName().equals("Apple")))
                .isFalse();
    }
    @Test
    public void deleteFruitByName() throws Exception {
        Fruit addedFruit = new Fruit();
        addedFruit.setName("Apple");
        addedFruit.setDescription("Winter");
        fruitService.add(addedFruit);
        Assertions.assertThat(fruitService.list().stream()
                        .filter(fruit -> null != fruit.getName())
                .anyMatch(fruit -> fruit.getName().equals("Apple")))
                .isTrue();
        fruitService.removeByName("Apple");
        Assertions.assertThat(fruitService.list().stream()
                        .filter(fruit -> null != fruit.getName())
                .anyMatch(fruit -> fruit.getName().equals("Apple")))
                .isFalse();
    }
}
