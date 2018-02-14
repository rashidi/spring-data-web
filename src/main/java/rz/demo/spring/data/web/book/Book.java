package rz.demo.spring.data.web.book;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Rashidi Zin
 */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String title;

}
