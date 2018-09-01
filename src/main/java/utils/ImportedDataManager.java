package utils;


import pojo.Author;
import pojo.Book;
import pojo.BookData;
import pojo.Category;

import java.util.*;
import java.util.stream.Collectors;

public class ImportedDataManager {
    static Scanner scanner = new Scanner(System.in);


    public Author createNewAuthor() {
        List<Author> authors = BookData.getInstance().getAuthors();
        int newAuthorID = authors.size() + 1;
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

    public List<Book> returnBookBasedOnAuthorName() {
        List<Book> books = BookData.getInstance().getBooks();
        List<Book> finalBookList = new ArrayList<>();
        boolean isFound = false;
        String userNameInput;

        //STREAM
//        List<Book> filteredBook = books.stream()
//                .filter(book -> book.getAuthors().stream()
//                        .anyMatch(author -> author.getName().equals(userNameInput)))
//                .collect(Collectors.toList());

        System.out.println("Podaje imię autora:");
        userNameInput = scanner.next();
        for (Book book : books) {
            for (Author author : book.getAuthors()) {
                if (author.getName().equalsIgnoreCase(userNameInput)) {
                    finalBookList.add(book);
                    isFound = true;
                }
            }

        }

        if (isFound == false) {
            System.out.println("Nie znaleziono autora o imieniu " + userNameInput);
        }

        return finalBookList;
    }

    public void printBookBasedOnAuthorName(List<Book> listToBePrinted) {
        for (Book book : listToBePrinted) {
            System.out.println(book.toString());

        }
        System.out.println();
    }


    public Map<String, Integer> returnSurnameAndNumberOfPublishedBooks() {
        List<Book> books = BookData.getInstance().getBooks();

        Map<String, Integer> listOfSurnameAndNumberOfPublishedBooks = new HashMap<>();
        for (Book book : books) {
            for (Author author : book.getAuthors()) {
                Integer numberOfOccurence = listOfSurnameAndNumberOfPublishedBooks.get(author.getSurname());
                listOfSurnameAndNumberOfPublishedBooks.put(author.getSurname(), (numberOfOccurence == null) ? 1 : numberOfOccurence + 1);
            }

        }

        return listOfSurnameAndNumberOfPublishedBooks;
    }

    public void printSurnameAndNumberOfPublishedBooks(Map<String, Integer> mapToBePrinted) {

        for (Map.Entry<String, Integer> suranmeAndNumberOfOccurences : mapToBePrinted.entrySet()) {
            System.out.println(suranmeAndNumberOfOccurences.toString());
        }

        System.out.println();
    }

    public void editNameOfExistingCategory() {

        List<Category> categories = BookData.getInstance().getCategories();
        boolean isModified = false;
        int userSearchedId;

        do {
            System.out.println("Podaj ID kategorii, którą chcesz edytować.");
            while (!scanner.hasNextInt()) {
                System.out.println("Nie wpisujesz liczby");
                scanner.next();
            }
            userSearchedId = scanner.nextInt();
        } while (userSearchedId <= 0);

        for (int i = 0; i < categories.size(); i++) {
            if (userSearchedId == categories.get(i).getID()) {
                System.out.println("Podaj nową nazwę kategorii");
                String userDefinedName = scanner.useDelimiter("\\n").next();
                categories.get(i).setName(userDefinedName);
                System.out.println("Nazwa kategorii o ID " + userSearchedId + " została zmieniona.");
                isModified = true;
            }
        }

        if (isModified == false) {
            System.out.println("Brak kategorii o podanym ID: " + userSearchedId);
        }

    }
}