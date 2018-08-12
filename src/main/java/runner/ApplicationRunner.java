package runner;

import pojo.BookData;
import utils.AppInitilizer;
import utils.CSVFileReader;

public class ApplicationRunner {

    public static void main(String[] args) {
        AppInitilizer appInitilizer = new AppInitilizer();

        CSVFileReader csvFileReader = new CSVFileReader();
        BookData.getInstance().setAuthors(csvFileReader.importAuthorsFromFile());
        BookData.getInstance().setCategories(csvFileReader.importCategoriesFromFile());
        BookData.getInstance().setBooks(csvFileReader.importBooksFromFile());
        appInitilizer.initiateApplication();
    }
}