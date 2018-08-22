package service;

import pojo.Book;

import java.util.List;

public interface BookPrinter {

    void printBooks(List<Book> books);
}