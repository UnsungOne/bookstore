package runner;

import pojo.BookData;
import utils.AppInitilizer;
import utils.CSVFileReader;

import java.io.IOException;

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
            System.out.println("Brak pliku autorów");
        }
        try {
            BookData.getInstance().setCategories(csvFileReader.importCategoriesFromFile(CATEGORIES));
        } catch (IOException e) {
            System.out.println("Brak pliku kategorii");
        }
        try {
            BookData.getInstance().setBooks(csvFileReader.importBooksFromFile(BOOKS));
        } catch (IOException e) {
            System.out.println("Brak pliku książek");
        }
        appInitilizer.initiateApplication();
    }
}