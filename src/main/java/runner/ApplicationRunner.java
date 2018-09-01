package runner;
import lombok.extern.slf4j.Slf4j;
import pojo.BookData;
import utils.AppInitilizer;
import utils.CSVFileReader;

import java.io.IOException;
@Slf4j
public class ApplicationRunner {

    final static String BOOKS = "books.csv";
    final static String AUTHORS = "authors.csv";
    final static String CATEGORIES = "categories.csv";

    public static void main(String[] args) {
        AppInitilizer appInitilizer = new AppInitilizer();
        CSVFileReader csvFileReader = new CSVFileReader();
        try {
            BookData.getInstance().setAuthors(csvFileReader.importAuthorsFromFile(AUTHORS));
        } catch (IOException e) {
            log.error("Nie odnaleziono pliku " + AUTHORS + " " + e.getMessage());
            System.exit(1);
        }
        try {
            BookData.getInstance().setCategories(csvFileReader.importCategoriesFromFile(CATEGORIES));
        } catch (IOException e) {
            log.error("Nie odnaleziono pliku " + CATEGORIES + " " + e.getMessage());
            System.exit(1);
        }
        try {
            BookData.getInstance().setBooks(csvFileReader.importBooksFromFile(BOOKS));
        } catch (IOException e) {
            log.error("Nie odnaleziono pliku " + BOOKS + " " + e.getMessage());
            System.exit(1);
        }
        appInitilizer.initiateApplication();
    }
}