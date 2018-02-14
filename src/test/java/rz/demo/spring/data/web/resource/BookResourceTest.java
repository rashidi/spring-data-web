package rz.demo.spring.data.web.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rz.demo.spring.data.web.book.Book;
import rz.demo.spring.data.web.book.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Rashidi Zin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BookResourceTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        repository.save(
                new Book("Spring Data Rest With Web Demo")
        );
    }

    @Test
    public void get() {
        ResponseEntity<Book> response = restTemplate.getForEntity("/book/{id}", Book.class, 1L);

        assertThat(response.getStatusCode())
                .isEqualTo(OK);

        assertThat(response.getBody().getTitle())
                .isEqualTo("Spring Data Rest With Web Demo");
    }

    @Test
    public void getWithNonExistingId() {
        ResponseEntity<Book> response = restTemplate.getForEntity("/book/{id}", Book.class, 100L);

        assertThat(response.getStatusCode())
                .isEqualTo(NOT_FOUND);
    }

}