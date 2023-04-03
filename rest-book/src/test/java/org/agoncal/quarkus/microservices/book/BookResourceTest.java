package org.agoncal.quarkus.microservices.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasKey;


@QuarkusTest
public class BookResourceTest {

    @Test
    public void shouldCreateNewBookEndpoint() {
        given()
        .formParam("title", "test")
        .formParam("author", "abc")
        .formParam("yearOfPublication", 123)
        .when().post("/api/books")
        .then()
           .statusCode(201)
           .body("title", is("test"))
           .body("author", is("abc"))
           .body("year_of_publication", is(123))
           .body("isbn_13", startsWith("13-"));

    }

}