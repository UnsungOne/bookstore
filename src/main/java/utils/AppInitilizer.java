package utils;

import pojo.BookData;
import service.BookFunctions;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppInitilizer {

    public void initiateApplication() {
        BookFunctions bookFunctions = new BookFunctions();
        CSVFileReader csvFileReader = new CSVFileReader();
        Scanner scanner = new Scanner(System.in);
        boolean status = true;
        String booksFile = "books.csv";
        String authorFile = "authors.csv";
        String categoryFile = "categories.csv";

        while (status) {
            System.out.println("-------------------------- \n" +
                    "Wybierz opcje programu: \n" +
                    "1 - Wyświetl książki\n" +
                    "2 - Wydrukuj autorów\n" +
                    "3 - Wydrukuj kategorie\n" +
                    "4 - Poszukaj po ISBN \n" +
                    "5 - Zamyka program");
            try {
                int choices = scanner.nextInt();
                switch (choices) {
                    case 1:
                        try {
                            BookData.getInstance().setBooks(csvFileReader.importBooksFromFile(booksFile));
                        } catch (IOException e) {
                            System.out.println("Brak pliku do książki");
                        }
                        break;
                    case 2:
                        try {
                            BookData.getInstance().setAuthors(csvFileReader.importAuthorsFromFile(authorFile));
                        } catch (IOException e) {
                            System.out.println("Brak pliku do autorów");
                        }
                        break;
                    case 3:
                        try {
                            BookData.getInstance().setCategories(csvFileReader.importCategoriesFromFile(categoryFile));
                        } catch (IOException e) {
                            System.out.println("Brak pliku do kategorii");
                        }
                        break;

                    case 4:
                        System.out.println("");
                        break;
                    case 5:
                        status = false;
                        break;
                    default:
                        System.out.println();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Nieprawidłowa wartość");
                scanner.nextLine();
            }
        }
    }
}