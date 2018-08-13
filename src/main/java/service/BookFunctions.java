package service;

import pojo.Book;

import java.util.*;
import java.util.stream.Collectors;

public class BookFunctions {

    public Book returnBookBasedOnProvidedISBNWIthStream(List<Book> books, int ISBNNumber) {
        return books.stream()
                .filter(e -> e.getISBN() == ISBNNumber)
                .findFirst()
                .get();
    }

    public Book returnBookBasedOnProvidedISBN(List<Book> books, int ISBNNumber) {

        for (Book book : books) {
            if (book.getISBN() == ISBNNumber) {
                return book;
            }
        }
        return null;
    }


    public int returnSumOfAllYearsFromBookWithStream(List<Book> books) {
        return  books.stream()
                .mapToInt(Book::getYear)
                .sum();
    }

    public int returnSumOfAllYearsFromBook(List<Book> books) {
        int sum = 0;

        for (Book book : books) {
            sum += book.getYear();
        }
        return sum;
    }

    public long returnNumberOfBooksReleasedAfter2007WithStream(List<Book> books) {
        return books.stream()
                .filter(e -> e.getYear() > 2007)
                .count();
    }

    public long returnNumberOfBooksReleasedAfter2007(List<Book> books) {
        long numberOfBooks = 0;

        for (Book book : books) {
            if (book.getYear() > 2007) {
                numberOfBooks++;
            }
        }
        return numberOfBooks;
    }

    public boolean returnBooleansBasedOnNumberOfBooksReleasedAfter2000(List<Book> books) {
        for (Book book : books) {
            if (book.getYear() > 2000) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean returnBooleansBasedOnNumberOfBooksReleasedAfter2000WithLambda(List<Book> books) {
        return books.stream()
                .allMatch(e -> e.getYear() > 2000);
    }

    public Book returnTheOldestBookWithStream(List<Book> books) {

        return books.stream()
                .min(Comparator.comparing(Book::getYear))
                .get();
    }
    
    public Book returnTheOldestBook(List<Book>books){

         return Collections.min(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return Integer.compare(o1.getYear(), o2.getYear());
            }
        });
    }
    

    public Book returnTheLatestBookWithStream(List<Book> books) {

        return books.stream()
                .max(Comparator.comparing(Book::getYear))
                .get();
    }

    public Book returnTheLatestBook(List<Book> books){

        return Collections.max(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return Integer.compare(o1.getYear(), o2.getYear());
            }
        });
    }


    public List<Book> returnTheLatestTwoBooksWithStream(List<Book> books) {

        return books.stream()
                .skip(books.size() - 2)
                .collect(Collectors.toList());
    }


    public List<Book> returnTheLatestTwoBooks(List<Book> books) {

        List<Book> finalList = new ArrayList<>();

        for (int i = books.size() - 2; i < books.size(); i++) {
            finalList.add(books.get(i));
        }
        return finalList;
    }
}