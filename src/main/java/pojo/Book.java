package pojo;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
public class Book {

    private static int count = 0;

    private int id;
    private String name;
    private int ISBN;
    private int year;
    private CoverType coverType;
    private List<Author> authors;
    private Category category;
}