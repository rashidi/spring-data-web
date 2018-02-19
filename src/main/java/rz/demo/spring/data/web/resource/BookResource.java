package rz.demo.spring.data.web.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rz.demo.spring.data.web.book.Book;

import java.util.Optional;

/**
 * @author Rashidi Zin
 */
@RestController
public class BookResource {

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> get(@PathVariable("id") Book book) {
        return Optional.ofNullable(book)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
