package guru.qa.restbackend.tests;


import guru.qa.restbackend.specs.Specs;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.*;

public class BookControllerTests {
    @Test
    void getAllBooks() {
        Specs.booksRequestSpec
            .given()
            .when()
                .get("/")
            .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    void getBookByExistingId() {
        Specs.booksRequestSpec
                .given()
                .when()
                    .get("/id/1")
                .then()
                    .statusCode(200)
                    .body("book_id[0]", is(1));
    }

    @Test
    void getBookByNotExistingId() {
        Specs.booksRequestSpec
                .given()
                .when()
                    .get("/id/5")
                .then()
                    .statusCode(404);
    }

    @Test
    void addNewBook() {
        Specs.booksRequestSpec
                .given()
                    .contentType(ContentType.JSON)
                    .body("{" +
                            "\"bookId\": \"3\",\n" +
                            "\"author_id\": \"3\",\n" +
                            "\"title\": \"Software Testing\"" +
                            "}")
                    .log().body()
                .when()
                    .post("/add_new")
                .then()
                    .statusCode(200)
                    .body("title", is("Software Testing"));
    }
}
