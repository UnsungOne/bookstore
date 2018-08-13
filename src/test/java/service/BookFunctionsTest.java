package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Book;
import pojo.CoverType;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookFunctionsTest {

    static List<Book> books = new ArrayList<>();
    BookFunctions bookFunctions = new BookFunctions();

    @BeforeAll
    public static void setup() {
        books.add(new Book(1, "Lambda in use", 1231231234, 2017, CoverType.M, null, null));
        books.add(new Book(2, "Effective Tomcat", 1231231235, 2012, CoverType.T, null, null));
        books.add(new Book(3, "Spring in use", 2131231234, 2002, CoverType.M, null, null));
        books.add(new Book(4, "Effective Tomcat", 1111111111, 2006, CoverType.M, null, null));
    }

    @Test
    public void shouldReturnBookBasedOnISBN() {
        int testableISBN = 1111111111;
        Book testableBookWithStream = bookFunctions.returnBookBasedOnProvidedISBNWIthStream(books, testableISBN);
        Book testableBook = bookFunctions.returnBookBasedOnProvidedISBN(books, testableISBN);
        assertThat(testableBookWithStream).isEqualTo(books.get(3));
        assertThat(testableBook).isEqualTo(books.get(3));
    }

    @Test
    public void shouldReturnSumOfYears() {
        int expectedValue = 8037;
        int actualValue = bookFunctions.returnSumOfAllYearsFromBookWithStream(books);
        int actualValueUsingStandardMethod = bookFunctions.returnSumOfAllYearsFromBook(books);
        assertThat(expectedValue).isEqualTo(actualValue);
        assertThat(expectedValue).isEqualTo(actualValueUsingStandardMethod);

    }

    @Test
    public void shouldReturnTwoBooksReleasedAfter2007() {
        long expectedValue = 2;
        long actualValue = bookFunctions.returnNumberOfBooksReleasedAfter2007WithStream(books);
        long actualValueUsingStandardMethod = bookFunctions.returnNumberOfBooksReleasedAfter2007(books);
        assertThat(expectedValue).isEqualTo(actualValue);
        assertThat(expectedValue).isEqualTo(actualValueUsingStandardMethod);
    }

    @Test
    public void shouldReturnTrueBasedOnTheNumberOfReleased() {
        boolean actualValue = bookFunctions.returnBooleansBasedOnNumberOfBooksReleasedAfter2000(books);
        boolean actualValueWithLambda = bookFunctions.returnBooleansBasedOnNumberOfBooksReleasedAfter2000WithLambda(books);
        Assertions.assertTrue(actualValue);
        Assertions.assertTrue(actualValueWithLambda);
    }

    @Test
    public void shouldReturnBookWIthYear2002() {
        Book testableBook = bookFunctions.returnTheOldestBookWithStream(books);
        Book testableBookUsingStandardMethod = bookFunctions.returnTheOldestBook(books);
        assertThat(testableBook).isEqualTo(books.get(2));
        assertThat(testableBookUsingStandardMethod).isEqualTo(books.get(2));
    }

    @Test
    public void shouldReturnBookWIthYear2017() {
        Book testableBook = bookFunctions.returnTheLatestBookWithStream(books);
        Book testableBookUsingStandardMethod = bookFunctions.returnTheLatestBook(books);
        assertThat(testableBook).isEqualTo(books.get(0));
        assertThat(testableBookUsingStandardMethod).isEqualTo(books.get(0));
    }

    @Test
    public void shouldReturnTwoLastElementsInTheList() {

        List<Book> testableBooks = bookFunctions.returnTheLatestTwoBooks(books);
        List<Book> testableBooksWithStream = bookFunctions.returnTheLatestTwoBooksWithStream(books);
        assertThat(testableBooks).isEqualTo(books.subList(2, 4));
        assertThat(testableBooksWithStream).isEqualTo(books.subList(2, 4));
    }
}