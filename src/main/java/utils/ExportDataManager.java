package utils;

import lombok.extern.slf4j.Slf4j;
import pojo.Author;
import pojo.Book;
import pojo.BookData;
import pojo.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ExportDataManager {

    static PrintWriter printWriter = null;
    static final File AUTHORS_FILE = new File("authors3.csv");
    static final File CATEGORIES_FILE = new File("categories3.csv");
    static final File BOOKS_FILE = new File("books3.csv");

    public void saveAuthorsToCSV(List<Author> authors) {

        try {
            StringBuilder stringBuilderForAuthors = new StringBuilder();
            printWriter = new PrintWriter(AUTHORS_FILE);
            for (Author author : authors) {
                stringBuilderForAuthors.append(author.getId());
                stringBuilderForAuthors.append(";");
                stringBuilderForAuthors.append(author.getName());
                stringBuilderForAuthors.append(";");
                stringBuilderForAuthors.append(author.getSurname());
                stringBuilderForAuthors.append(";");
                stringBuilderForAuthors.append(author.getAge());
                stringBuilderForAuthors.append("\n");
            }
            printWriter.write(stringBuilderForAuthors.toString().trim());

        } catch (FileNotFoundException e) {
            log.error("Nie odnaleziono pliku " + AUTHORS_FILE + " " + e.getMessage());

        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

    }

    public void saveCategoriesToCSV(List<Category> categories) {
        try {
            StringBuilder stringBuilderForCategories = new StringBuilder();
            printWriter = new PrintWriter(CATEGORIES_FILE);
            for (Category category : categories) {
                stringBuilderForCategories.append(category.getID());
                stringBuilderForCategories.append(";");
                stringBuilderForCategories.append(category.getName());
                stringBuilderForCategories.append(";");
                stringBuilderForCategories.append(category.getPriority());
                stringBuilderForCategories.append("\n");
            }
            printWriter.write(stringBuilderForCategories.toString().trim());

        } catch (FileNotFoundException e) {
            log.error("Nie odnaleziono pliku " + CATEGORIES_FILE + " " + e.getMessage());

        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

    }

    public void saveBooksToCSV(List<Book> books) {
        try {
            StringBuilder stringBuilderForBooks = new StringBuilder();
            printWriter = new PrintWriter(BOOKS_FILE);
            for (Book book : books) {
                stringBuilderForBooks.append(book.getId());
                stringBuilderForBooks.append(";");
                stringBuilderForBooks.append(book.getName());
                stringBuilderForBooks.append(";");
                stringBuilderForBooks.append(book.getISBN());
                stringBuilderForBooks.append(";");
                stringBuilderForBooks.append(book.getYear());
                stringBuilderForBooks.append(";");
                stringBuilderForBooks.append(book.getCoverType());
                stringBuilderForBooks.append(";");
                stringBuilderForBooks.append(book.getAuthors().stream()
                        .map(author -> String.valueOf(author.getId()))
                        .collect(Collectors.joining(";")));
                stringBuilderForBooks.append(";");
                stringBuilderForBooks.append(book.getCategory().getID());
                stringBuilderForBooks.append("\n");
            }
            printWriter.write(stringBuilderForBooks.toString().trim());
        } catch (FileNotFoundException e) {
            log.error("Nie odnaleziono pliku " + BOOKS_FILE + " " + e.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

    }
}