package utils;

import pojo.Author;
import pojo.BookData;
import pojo.Category;
import service.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppInitilizer {

    static Scanner scanner = new Scanner(System.in);
    BookData bookData = BookData.getInstance();
    ImportedDataManager importedDataManager = new ImportedDataManager();
    BookFunctions bookFunctions = new BookFunctions();

    BookPrinter bookPrinter = new PrintByTitle();

    static void renderMenu() {
        System.out.println("----------------------------------------------- \n" +
                "Witaj, " + System.getProperty("user.name") + "\n" +
                "\n" +
                "Wybierz opcje programu: \n" +
                "1 - Wyświetl książki\n" +
                "2 - Wydrukuj autorów\n" +
                "3 - Wydrukuj kategorie\n" +
                "4 - Zsumuj lata \n" +
                "5 - Policz średnią lat \n" +
                "6 - Posortuj alfabetycznie \n" +
                "7 - Sprawdź, czy istnieją ksiażki wydane przed 2003 \n" +
                "8 - Dodaj nowego autora \n" +
                "9 - Dodaj nową kategorie \n" +
                "10 - Edytuj tytuł wybranej książki \n" +
                "11 - Zwróć książki z kategorii: \"Wzorcee projektowe\" \n" +
                "12 - Usuń książkę \n" +
                "13 - Kolejność - ISBN \n" +
                "14 - Kolejność - Rok \n" +
                "15 - Kolejnoś - Tytuł \n" +
                "16 - Edytuj wiek wybranej autora \n" +
                "17 - Szukaj książki wybranego autora \n" +
                "18 - Zamknij program \n" +
                "-----------------------------------------------");
    }

    public void initiateApplication() {

        boolean status = true;
        while (status) {
            renderMenu();
            try {
                String choices  = scanner.useDelimiter("\\n").next(); //TODO wywala sie na enterze
                switch (choices) {
                    case "1":
                        bookPrinter.printBooks(bookFunctions.sortBooksAlphabeticallyWithStream(bookData.getBooks()));
                        break;
                    case "2":
                        BookData.getInstance().printAuthors();
                        break;
                    case "3":
                        BookData.getInstance().printCategories();
                        break;
                    case "4":
                        System.out.println(bookFunctions.returnSumOfAllYearsFromBookWithStream(bookData.getBooks()));
                        break;
                    case "5":
                        System.out.println(bookFunctions.returnAveragePublishYear(bookData.getBooks()));
                        break;
                    case "6":
                        System.out.println(bookFunctions.sortBooksAlphabeticallyWithStream(bookData.getBooks()));
                        break;
                    case "7":
                        System.out.println(bookFunctions.checkIfAnyBookHasBeenReleasedBefore2003(bookData.getBooks()));
                        break;
                    case "8":
                        Author newAuthor = importedDataManager.createNewAuthor();
                        BookData.getInstance().getAuthors().add(newAuthor);
                        System.out.println("Nowy autor został dodany do listy.");
                        break;
                    case "9":
                        Category newCategory = importedDataManager.createNewCategory();
                        BookData.getInstance().getCategories().add(newCategory);
                        System.out.println("Nowa kategoria została dodany do listy.");
                        break;
                    case "10":
                        importedDataManager.editNameOfExistingBook();
                        break;
                    case "11":
                        System.out.println(importedDataManager.returnAllBooksFromDefinedCategory());
                        break;
                    case "12":
                        importedDataManager.removeExistingBook();
                        break;
                    case "13":
                        bookPrinter = new PrintByISBN();
                        break;
                    case "14":
                        bookPrinter = new PrintByYear();
                        break;
                    case "15":
                        bookPrinter = new PrintByTitle();
                        break;
                    case "16":
                         importedDataManager.editAgeOfExistingAuthor();
                        break;
                    case "17":
                        importedDataManager.returnBookBasedOnAuthorID();
                        break;
                    case "18":
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