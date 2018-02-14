# Spring Data JPA: Retrieve Directly From Web
Look Ma! No `repository` in between!

## Motivation
Inspired by a [tweet from Josh Long][1].

## Implementation

### Maven
```xml
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>
```

### Resource
```java
@RestController
public class BookResource {

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> get(@PathVariable("id") Book book) {
        return Objects.nonNull(book) ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

}
```

## Verification
```java
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
```

[1]: https://twitter.com/starbuxman/status/963035061812645888