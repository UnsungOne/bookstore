package service;

import pojo.Book;

import java.util.List;

public class PrintByYear implements BookPrinter {
    @Override
    public void printBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book.getYear() + ", " + book.getName() + ", "  + book.getISBN());

        }
        System.out.println();
    }

}