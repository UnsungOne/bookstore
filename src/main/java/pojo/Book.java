package pojo;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
public class Book {
    private int id;
    private String name;
    private String ISBN;
    private int year;
    private CoverType coverType;
    private List<Author> authors;
    private Category category;

}