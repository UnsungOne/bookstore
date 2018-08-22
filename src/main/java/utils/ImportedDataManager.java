package utils;

import pojo.Author;
import pojo.Book;
import pojo.BookData;
import pojo.Category;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ImportedDataManager {
    static Scanner scanner = new Scanner(System.in);

    public Author createNewAuthor() {

        int newAuthorID = BookData.getInstance().getAuthors().size() + 1;
        System.out.println("Podaj imię autora");
        String newAuthorName = scanner.useDelimiter("\\n").next();
        System.out.println("Podaj nazwisko autora");
        String newAuthorSurname = scanner.useDelimiter("\\n").next();
        int newAuthorAge;
        do {
            System.out.println("Podaj wiek autora");
            while (!scanner.hasNextInt()) {
                System.out.println("Nie wpisujesz liczby");
                scanner.next();
            }
            newAuthorAge = scanner.nextInt();
        } while (newAuthorAge <= 0);
        return new Author(newAuthorID, newAuthorName, newAuthorSurname, newAuthorAge);
    }

    public Category createNewCategory() {
        int newCategoryID = BookData.getInstance().getCategories().size() + 1;
        System.out.println("Podaj nazwę kategori");
        String newCategoryName = scanner.useDelimiter("\\n").next();
        int newCategoryPriority;
        do {
            System.out.println("Podaj nowy priorytet kategorii");
            while (!scanner.hasNextInt()) {
                System.out.println("Nie wpisujesz liczby");
                scanner.next();
            }
            newCategoryPriority = scanner.nextInt();
        } while (newCategoryPriority <= 0);
        return new Category(newCategoryID, newCategoryName, newCategoryPriority);
    }

    public void editNameOfExistingBook() {

        List<Book> books = BookData.getInstance().getBooks();
        boolean isModified = false;
        int userSearchedId;

        do {
            System.out.println("Podaj ID książki, którą chcesz edytować.");
            while (!scanner.hasNextInt()) {
                System.out.println("Nie wpisujesz liczby");
                scanner.next();
            }
            userSearchedId = scanner.nextInt();
        } while (userSearchedId <= 0);

        for (int i = 0; i < books.size(); i++) {
            if (userSearchedId == books.get(i).getId()) {
                System.out.println("Podaj nową nazwę książki");
                String userDefinedName = scanner.useDelimiter("\\n").next();
                BookData.getInstance().getBooks().get(i).setName(userDefinedName);
                System.out.println("Nazwa książki o ID " + userSearchedId + " została zmieniona.");
                isModified = true;
            }
        }

        if (isModified == false) {
            System.out.println("Brak książki o podanym ID: " + userSearchedId);
        }

    }

//    public void editExistingBookWithStream() {
//        try {
//            System.out.println("Podaj ID książki, którą chcesz edytować.");
//            int userSearchedId = scanner.nextInt();
//            System.out.println("Podaj nową nazwę książki");
//            String userDefinedName = scanner.useDelimiter("\\n").next();
//            BookData.getInstance().getBooks().stream()
//                    .filter(book -> book.getId() == userSearchedId)
//                    .findFirst()
//                    .ifPresent(book -> book.setName(userDefinedName));
//
//        } catch (InputMismatchException e) {
//            System.out.println("Nieprawidłowa wartość");
//            scanner.nextLine();
//        }
//
//    }


    public List<Book> returnAllBooksFromDefinedCategory() {
        return BookData.getInstance().getBooks().stream()
                .filter(book -> book.getCategory().getID() == 2)
                .collect(Collectors.toList());
    }

    public void removeExistingBook() {
        List<Book> books = BookData.getInstance().getBooks();
        boolean isDeleted = false;
        int userSearchedId;

        do {
            System.out.println("Podaj ID książki, którą chcesz usunąć.");
            while (!scanner.hasNextInt()) {
                System.out.println("Nie wpisujesz liczby");
                scanner.next();
            }
            userSearchedId = scanner.nextInt();
        } while (userSearchedId <= 0);

        for (int i = 0; i < books.size(); i++) {
            if (userSearchedId == books.get(i).getId()) {
                BookData.getInstance().getBooks().remove(i);
                System.out.println("Książka o ID " + userSearchedId + " została usunięta.");
                isDeleted = true;
            }

        }
        if (isDeleted == false) {
            System.out.println("Brak książki o podanym ID: " + userSearchedId);
        }
    }


    public void editAgeOfExistingAuthor() {
        List<Author> authors = BookData.getInstance().getAuthors();
        boolean isModified = false;
        int userSearchedId;

        do {
            System.out.println("Podaj ID autora, dla którego chcesz zmienić wiek.");
            while (!scanner.hasNextInt()) {
                System.out.println("Nie wpisujesz liczby");
                scanner.next();
            }
            userSearchedId = scanner.nextInt();
        } while (userSearchedId <= 0);

        for (int i = 0; i < authors.size(); i++) {
            if (userSearchedId == authors.get(i).getId()) {
                System.out.println("Podaj nowy wiek");
                int userDefinedAge = scanner.nextInt();
                BookData.getInstance().getAuthors().get(i).setAge(userDefinedAge);
                System.out.println("Wiek autora o ID " + userSearchedId + " została zmieniona na: " + userDefinedAge);
                isModified = true;
            }
        }

        if (isModified == false) {
            System.out.println("Brak autora o podanym ID: " + userSearchedId);
        }

    }

    public List<Book> returnBookBasedOnAuthorID() {
        List<Author> authors = BookData.getInstance().getAuthors();
        List<Book> books = BookData.getInstance().getBooks();

        System.out.println("Wybierz kryteria poszukiwań: ");
        boolean isFound = false;

        String name = scanner.nextLine();
        List<Book> some = books.stream()
                .filter(book -> book.getAuthors().stream()
                        .anyMatch(author -> author.getName().equals(name)))
                .collect(Collectors.toList());
        isFound = true;

        if (isFound == false) {
            System.out.println("Nie znaleziono autora o imieniu " + name);
        }


        for (Book book : some) {
            System.out.println(book.toString());

        }
        System.out.println();

        return some;
    }


}
