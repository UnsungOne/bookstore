package runner;

import pojo.BookData;
import utils.AppInitilizer;
import utils.CSVFileReader;

import java.io.IOException;

public class ApplicationRunner {

    public static void main(String[] args) {
        AppInitilizer appInitilizer = new AppInitilizer();
        String booksFile = "books.csv";
        String authorFile = "authors.csv";
        String categoryFile = "categories.csv";
        CSVFileReader csvFileReader = new CSVFileReader();
        try {
            BookData.getInstance().setAuthors(csvFileReader.importAuthorsFromFile(authorFile));
        } catch (IOException e) {
            System.out.println("Brak pliku");
        }
        try {
            BookData.getInstance().setCategories(csvFileReader.importCategoriesFromFile(categoryFile));
        } catch (IOException e) {
            System.out.println("Brak pliku");
        }
        try {
            BookData.getInstance().setBooks(csvFileReader.importBooksFromFile(booksFile));
        } catch (IOException e) {
            System.out.println("Brak pliku");
        }
        appInitilizer.initiateApplication();
    }
}