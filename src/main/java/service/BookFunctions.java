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
        return books.stream()
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

    public Book returnTheOldestBook(List<Book> books) {

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

    public Book returnTheLatestBook(List<Book> books) {

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

    public double returnAveragePublishYear(List<Book> books) {

        double yearSum = 0;
        double averageYearValue = 0;
        for (int i = 0; i < books.size(); i++) {
            yearSum += books.get(i).getYear();
            averageYearValue = yearSum / books.size();
        }

        return averageYearValue;

    }

    public double returnAveragePublishYearWithStream(List<Book> books) {

        return books.stream()
                .mapToDouble(Book::getYear)
                .average()
                .getAsDouble();
    }

    public boolean checkIfAnyBookHasBeenReleasedBefore2003WithStream(List<Book> books) {

        return books.stream()
                .anyMatch(e -> e.getYear() < 2003);
    }

    public boolean checkIfAnyBookHasBeenReleasedBefore2003(List<Book> books) {

        for (Book book : books) {
            if (book.getYear() < 2003) {
                return true;

            }
        }
        return false;

    }

    public Book returnAllBooksMeetingConditionsWithStream(List<Book> books) {

        return books.stream()
                .filter(e -> e.getName().startsWith("C") && e.getYear() > 2010)
                .findAny()
                .get();
    }

    public Book returnAllBooksMeetingConditions(List<Book> books) {

             //TODO

        return null;
    }
}