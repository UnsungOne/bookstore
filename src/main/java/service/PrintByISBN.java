package service;

import pojo.Book;

import java.util.List;

public class PrintByISBN implements BookPrinter {
    @Override
    public void printBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book.getISBN() + ", " + book.getName() + ", " + book.getYear());

        }
        System.out.println();
    }
}