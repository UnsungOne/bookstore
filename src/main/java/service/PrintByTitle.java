package service;

import pojo.Book;

import java.util.List;

public class PrintByTitle implements BookPrinter {
    @Override
    public void printBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book.getName() + ", " + book.getYear() + ", " + book.getISBN());

        }
        System.out.println();
    }

}