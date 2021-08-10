package guru.qa.restbackend.controller;

import guru.qa.restbackend.domain.Author;
import guru.qa.restbackend.domain.Book;
import guru.qa.restbackend.exception.AuthorNotFoundException;
import guru.qa.restbackend.exception.BookNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


import static java.util.stream.Collectors.toList;

@RestController
public class BookController {

    Map<Integer, Book> books = Map.of(
           1, Book.builder()
                    .bookId(1)
                    .title("A Practitioner's Guide to Software Test Design")
                    .authorId(1)
                    .build(),
            2, Book.builder()
                    .bookId(2)
                    .title("Lessons Learned in Software Testing")
                    .authorId(2)
                    .build()
    );

    @GetMapping("books/")
    @ApiOperation("Get all books")
    public List<Book> getAllBooks() {
        return books.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(toList());
    }

    @PostMapping(value = "books/add_new", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book addBook(@RequestBody Book book) {
        return Book.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .authorId(book.getAuthorId())
                .build();
    }

    @GetMapping("books/id/{bookId}")
    @ApiOperation("Get books by id")
    public List<Book> getBooksById(@PathVariable("bookId") Integer bookId) {

        if (books.containsKey(bookId)) {
            return books.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .filter(book -> book.getBookId() == bookId)
                    .collect(toList());}
        else throw new BookNotFoundException(HttpStatus.NOT_FOUND);
    }
}
