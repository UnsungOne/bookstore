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

    public List<Book> printBooks() {

        for (Book book : books)

        {
            System.out.println(book.toString());
        }

        System.out.println();
        return books;
    }

    public List<Author> printAuthors() {

        for (Author author : authors)

        {
            System.out.println(author.toString());
        }

        System.out.println();
        return authors;
    }

    public List<Category> printCategories() {

        for (Category category : categories) {
            System.out.println(category.toString());
        }

        System.out.println();
        return categories;
    }


}