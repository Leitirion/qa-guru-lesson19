package guru.qa.restbackend.tests;

import guru.qa.restbackend.specs.Specs;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class AuthorControllerTests {
    @Test
    void getAllAuthors() {
        Specs.authorsRequestSpec
                .given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    @Deprecated
    void getBookByExistingAuthorById() {
        Specs.authorsRequestSpec
                .given()
                .when()
                .get("/id/2")
                .then()
                .statusCode(200)
                .body("id[0]", is(2));
    }

    @Test
    void getBookByNotExistingAuthorId() {
        Specs.authorsRequestSpec
                .given()
                .when()
                .get("/id/8")
                .then()
                .statusCode(404);
    }

    @Test
    @Deprecated
    void addNewAuthor() {
        Specs.authorsRequestSpec
                .given()
                .contentType(ContentType.JSON)
                .body("{" +
                        "\"id\": \"4\",\n" +
                        "\"first_name\": \"Ron\",\n" +
                        "\"last_name\": \"Patton\"" +
                        "}")
                .log().body()
                .when()
                .post("/add_new")
                .then()
                .statusCode(200)
                .body("id", is(4),
                        "first_name", is("Ron"),
                        "last_name", is("Patton"));
    }
}