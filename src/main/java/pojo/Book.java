package pojo;


import lombok.*;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Data
public class Book {
    private int id;
    private String name;
    private int ISBN;
    private int year;
    private CoverType coverType;
    private List<Author> authors;
    private Category category;
}