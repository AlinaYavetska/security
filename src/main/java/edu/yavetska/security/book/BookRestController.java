package edu.yavetska.security.book;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@author   Admin
@project   security
@class  BookRestController
@version  1.0.0
@since 21.03.2025 - 16.29
*/
@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getOneBook(@PathVariable String id) {
        return bookService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.update(book);
    }

    @GetMapping("/hello/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("hello/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "Hello Admin";
    }

    @GetMapping("hello/unknown")
    public String helloUnknown() {
        return "Hello Unknown";
    }
}
