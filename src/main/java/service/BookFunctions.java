package service;

import pojo.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookFunctions {

    public Book returnBookBasedOnProvidedISBNWIthStream(List<Book> books, int ISBNNumber) {
        Book resultBook = books.stream()
                .filter(e -> e.getISBN() == ISBNNumber)
                .findFirst().get();
        return resultBook;
    }


    public int returnSumOfAllYearsFromBookWithStream(List<Book> books) {
        int resultBook = books.stream()
                .mapToInt(Book::getYear).sum();
        return resultBook;
    }

    public long returnNumberOfBooksReleasedAfter2007WithStream(List<Book> books) {
        long numberOfBooks = books.stream()
                .filter(e -> e.getYear() > 2007)
                .count();
        return numberOfBooks;
    }

    public boolean reutrnBooleansBasedOnNumberOfBooksReleasedAfter2000(List<Book> books) {
        for (Book book : books) {
            if (book.getYear() > 2000) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Book returnTheOldestBookWithStream(List<Book> books) {

        Book oldestBook = books.stream()
                .min(Comparator.comparing(Book::getYear))
                .get();
        return oldestBook;
    }

    public Book returnTheLatestBookWithStream(List<Book> books) {

        Book latestBook = books.stream()
                .max(Comparator.comparing(Book::getYear))
                .get();
        return latestBook;
    }

    public List<Book> returnTheLatestTwoBooks(List<Book> books) {

        List<Book> finalList = new ArrayList<>();

        for (int i = books.size() -2 ; i < books.size() ; i++) {
            finalList.add(books.get(i));
        }
        return finalList;
    }
}