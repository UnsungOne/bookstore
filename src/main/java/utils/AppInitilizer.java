package utils;

import pojo.BookData;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppInitilizer {

    public void initiateApplication() {

        CSVFileReader csvFileReader = new CSVFileReader();
        Scanner scanner = new Scanner(System.in);
        boolean status = true;

        while (status) {
            System.out.println("-------------------------- \n" +
                    "Wybierz opcje programu: \n" +
                    "1 - Wyświetl książki\n" +
                    "2 - Wydrukuj autorów\n" +
                    "3 - Wydrukuj kategorie\n" +
                    "4 - Zamyka program");
            try {
                int choices = scanner.nextInt();
                switch (choices) {
                    case 1:
                        BookData.getInstance().setBooks(csvFileReader.importBooksFromFile());
                        break;
                    case 2:
                        BookData.getInstance().setAuthors(csvFileReader.importAuthorsFromFile());
                        break;
                    case 3:
                        BookData.getInstance().setCategories(csvFileReader.importCategoriesFromFile());
                        break;
                    case 4:
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