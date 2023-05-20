package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class FruitResourceTest {
    @Inject
    FruitService fruitService;


    ObjectMapper mapper = new ObjectMapper();



    @Test
    public void testHelloEndpoint() throws JsonProcessingException {
        List<Fruit> fruits = fruitService.list();
        String json = mapper.writeValueAsString(fruits);
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200)
             .body(is(json));
    }

    @Test
    @Transactional
    public void testCreateFruit() {
        Fruit newFruit = given()
                .contentType("application/json")
                .body("{\"name\":\"Banana\",\"description\":\"Summer\"}")
                .when()
                .post("/fruits")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(Fruit.class);

        Fruit fruit = fruitService.findById(newFruit.id);
        Assertions.assertThat(fruit).isNotNull();
        Assertions.assertThat(newFruit.id).isNotNull();
        Assertions.assertThat(fruit.getName()).isEqualTo("Banana");
        Assertions.assertThat(fruit.getDescription()).isEqualTo("Summer");
        fruitService.removeById(newFruit.id);
    }

    @Test
    @Transactional
    public void testDeleteFruit() throws Exception {
        Fruit newFruit = given()
                .contentType("application/json")
                .body("{\"name\":\"Banana\",\"description\":\"Summer\"}")
                .when()
                .post("/fruits")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(Fruit.class);


        long count = fruitService.count();
        given()
                .when().delete("/fruits/" + newFruit.id)
                .then()
                .statusCode(200);


        given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .body("size()", is((int)count-1));

    }
}
