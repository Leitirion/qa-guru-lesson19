package guru.qa.restbackend.controller;

import guru.qa.restbackend.domain.Author;
import guru.qa.restbackend.exception.AuthorNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
public class AuthorController {
    Map<Integer, Author> authors = Map.of(
            1, Author.builder()
                    .id(1)
                    .firstName("Lee")
                    .lastName("Copeland")
                    .build(),
            2, Author.builder()
                    .id(2)
                    .firstName("Cem")
                    .lastName("Kaner")
                    .build()
    );

    @GetMapping("authors/")
    @ApiOperation("Get all authors")
    public List<Author> getAllBooks() {
        return authors.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(toList());
    }

    @PostMapping("authors/add_new")
    @ApiOperation("Add new author")
    public Author addBook(@RequestBody Author author) {
        return Author.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }

    @GetMapping("authors/id/{id}")
    @ApiOperation("Get author by id")
    public List<Author> getAuthorById(@PathVariable("id") Integer id) {

        if (authors.containsKey(id)) {
            return authors.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .filter(author -> author.getId() == id)
                    .collect(toList());}
        else throw new AuthorNotFoundException(HttpStatus.NOT_FOUND);
    }
}
