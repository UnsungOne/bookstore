package utils;

import com.opencsv.CSVReader;
import pojo.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {

    public List<Category> importCategoriesFromFile(String categoryFile) throws IOException {

        CSVReader csvReader;
        List<Category> categoryList = new ArrayList<>();
        csvReader = new CSVReader(new FileReader(categoryFile), ';'); //TODO brakuje close na csvreader
        String[] fileLine;


        while ((fileLine = csvReader.readNext()) != null) {
            String number = fileLine[0];
            int numberInt = Integer.parseInt(number);
            String priority = fileLine[2];
            int priorityInt = Integer.parseInt(priority);
            Category category = new Category(numberInt, fileLine[1], priorityInt);
            categoryList.add(category);
        }
        csvReader.close();
        return categoryList;
    }


    public List<Author> importAuthorsFromFile(String authorFile) throws IOException {

        CSVReader csvReader;
        List<Author> authorList = new ArrayList<>();
        csvReader = new CSVReader(new FileReader(authorFile), ';');
        String[] fileLine;
        while ((fileLine = csvReader.readNext()) != null) {
            String number = fileLine[0];
            int numberInt = Integer.parseInt(number);
            String age = fileLine[3];
            int ageInt = Integer.parseInt(age);
            Author author = new Author(numberInt, fileLine[1], fileLine[2], ageInt);
            authorList.add(author);
        }
        csvReader.close();
        return authorList;
    }

    public List<Book> importBooksFromFile(String bookFile) throws IOException {

        CSVReader csvReader;
        List<Book> bookList = new ArrayList<>();

        csvReader = new CSVReader(new FileReader(bookFile), ';');
        String[] fileLine;
        while ((fileLine = csvReader.readNext()) != null) {
            String number = fileLine[0];
            int numberInt = Integer.parseInt(number);
            String isbn = fileLine[2];
            int isbntobeadded = Integer.parseInt(isbn);
            String year = fileLine[3];
            int yearInt = Integer.parseInt(year);
            List<Author> authorList = new ArrayList<>();

            //authors //TODO do metody

            String[] authorsArray = fileLine[5].split(",");
            for (String s : authorsArray) {
                int idAuthor = Integer.parseInt(s);

                List<Author> authorsInImport = BookData.getInstance().getAuthors();

                for (Author anAuthorsInImport : authorsInImport) {
                    if (idAuthor == anAuthorsInImport.getId()) {
                        authorList.add(anAuthorsInImport);
                    }
                }
            }

            //cover //TODO metoda
            String stringcoverType = fileLine[6];
            CoverType finalCoverType;
            if (stringcoverType.equals(CoverType.T.name())) {
                finalCoverType = CoverType.T;
            } else {
                finalCoverType = CoverType.M;
            }

            //category //TODO metoda
            int exepctedID = Integer.parseInt(fileLine[6]);
            Category category = null;
            for (Category cat : BookData.getInstance().getCategories()) {
                if (exepctedID == cat.getID()) {
                    category = cat;
                }
            }

            Book book = new Book(numberInt, fileLine[1], isbntobeadded, yearInt, finalCoverType, authorList, category);
            bookList.add(book);
        }
        csvReader.close();
        return bookList;
    }
}