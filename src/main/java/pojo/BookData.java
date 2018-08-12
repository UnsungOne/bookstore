package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookData {

    private static BookData INSTANCE;
    private List<Book> books;
    private List<Author> authors;

    private List<Category> categories;

    private BookData() {
    }

    public static BookData getInstance() {
        if (INSTANCE == null)
            INSTANCE = new BookData();
        return INSTANCE;
    }
}