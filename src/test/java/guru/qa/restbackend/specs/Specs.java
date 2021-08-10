package guru.qa.restbackend.specs;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class Specs {
    public static RequestSpecification booksRequestSpec = with()
            .baseUri("http://localhost:8080")
            .basePath("/books");

    public static RequestSpecification authorsRequestSpec = with()
            .baseUri("http://localhost:8080")
            .basePath("/authors");
}
