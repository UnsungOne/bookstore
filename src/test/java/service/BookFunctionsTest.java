package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Book;
import pojo.CoverType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

public class BookFunctionsTest {

    static List<Book> books = new ArrayList<>();
    BookFunctions bookFunctions = new BookFunctions();

    @BeforeAll
    public static void setup() {
        books.add(new Book(1, "Lambda in use", 1231231234, 2017, CoverType.M, null, null));
        books.add(new Book(2, "Creative programming", 1231231235, 2012, CoverType.T, null, null));
        books.add(new Book(3, "Spring in use", 2131231234, 2002, CoverType.M, null, null));
        books.add(new Book(4, "Effective Tomcat", 1111111111, 2006, CoverType.M, null, null));
    }

    @Test
    public void shouldReturnBookBasedOnISBN() {
        int testableISBN = 1111111111;
        Book testableBook = bookFunctions.returnBookBasedOnProvidedISBN(books, testableISBN);
        assertThat(testableBook.getISBN()).isEqualTo(1111111111);
    }

    @Test
    void shouldNotReturnAnyData() {
        int testableISBN = 1111111112;
        Optional<Book> testableBookWithStream = bookFunctions.returnBookBasedOnProvidedISBNWIthStream(books, testableISBN);
        assertFalse(testableBookWithStream.isPresent());
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

    @Test
    public void shouldReturnAverageNumberForYear() {

        double expectedAverageYearValue = 2009.25;
        double actualAverageYearValue = bookFunctions.returnAveragePublishYearWithStream(books);
        double actualAverageYearValueUsingStandardMethod = bookFunctions.returnAveragePublishYear(books);
        assertThat(expectedAverageYearValue).isEqualTo(actualAverageYearValue);
        assertThat(expectedAverageYearValue).isEqualTo(actualAverageYearValueUsingStandardMethod);

    }

    @Test
    public void shouldReturnTrueIfAnyBookIsPublishedBefore2003() {
        boolean actualValueWithLambda = bookFunctions.checkIfAnyBookHasBeenReleasedBefore2003WithStream(books);
        boolean actualValue = bookFunctions.checkIfAnyBookHasBeenReleasedBefore2003(books);
        Assertions.assertTrue(actualValueWithLambda);
        Assertions.assertTrue(actualValue);
    }

    @Test
    public void shouldBookAtIndexOne() {
        Book testableBooksWithStream = bookFunctions.returnAllBooksMeetingConditionsWithStream(books);
        Book testableBook = bookFunctions.returnAllBooksMeetingConditions(books);
        assertThat(testableBook).isEqualTo(books.get(1));
        assertThat(testableBooksWithStream).isEqualTo(books.get(1));
    }

    @Test
    public void shouldReturnTheBookSortedFromTheOldestToTheLatest() {
        List<Book> testableBooks = bookFunctions.sortBooksByTheOldestBookWithStream(books);
        List<Book> testableBooksUsingStandardWay = bookFunctions.sortBooksByTheOldest(books);
        assertThat(testableBooks).isEqualTo(books.stream()
                .sorted(Comparator.comparing(Book::getYear))
                .collect(Collectors.toList()));
        assertThat(testableBooksUsingStandardWay).isEqualTo(books.stream()
                .sorted(Comparator.comparing(Book::getYear))
                .collect(Collectors.toList()));
    }

    @Test
    public void shouldReturnTheBookSortedFromTheLatestToTheOldest() {
        List<Book> testableBooks = bookFunctions.sortBooksByTheLatestBookWithStream(books);
        List<Book> testableBooksUsingStandardWay = bookFunctions.sortBooksByTheLatest(books);
        assertThat(testableBooks).isEqualTo(books.stream()
                .sorted(Comparator.comparing(Book::getYear).reversed())
                .collect(Collectors.toList()));
        assertThat(testableBooksUsingStandardWay).isEqualTo(books.stream()
                .sorted(Comparator.comparing(Book::getYear).reversed())
                .collect(Collectors.toList()));
    }

    @Test
    public void shouldReturnBooksWith100PlusYears() {
        List<Book> testableBooks = bookFunctions.add100YearsToAllBooksWIthStream(books);
        assertThat(testableBooks).isNotEqualTo(books);
    }

    @Test
    public void shouldReturnAllBooksDividedByTwo() {
        List<String> testableBooks = bookFunctions.returnBooksThatCanBeDividedByTwoWIthStream(books);
        assertThat(testableBooks).isEqualTo(books.stream());

    }

    @Test
    public void shouldReturnTheBooksSortedAlphabeticallyWithStream() {
        List<Book> testableBooks = bookFunctions.sortBooksAlphabeticallyWithStream(books);
        assertThat(testableBooks).isEqualTo(books.stream()
                .sorted(Comparator.comparing(Book::getName))
                .collect(Collectors.toList()));
    }
}