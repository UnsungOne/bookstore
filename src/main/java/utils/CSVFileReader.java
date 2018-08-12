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
        csvReader = new CSVReader(new FileReader(categoryFile), ';');
        String[] fileLine;
        while ((fileLine = csvReader.readNext()) != null) {
            String number = fileLine[0];
            int numberInt = Integer.parseInt(number);
            String priority = fileLine[2];
            int priorityInt = Integer.parseInt(priority);
            Category category = new Category(numberInt, fileLine[1], priorityInt);
            categoryList.add(category);
        }

        for (Category category : categoryList) {
            System.out.println(category.toString());
        }
        System.out.println();
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


        for (Author author : authorList) {
            System.out.println(author.toString());
        }
        System.out.println();
        return authorList;
    }

    public List<Book> importBooksFromFile(String bookFile) throws IOException {

        CSVReader csvReader;
        List<Book> bookList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        CoverType coverType = null;
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

            //authors
            String[] authorsArray = fileLine[6].split(",");

            for (String s : authorsArray) {
                int idAuthor = Integer.parseInt(s);

                List<Author> authorsInImport = BookData.getInstance().getAuthors();

                for (int i = 0; i < authorsInImport.size(); i++) {

                    if (idAuthor == authorsInImport.get(i).getId()) {
                        authorList.add(authorsInImport.get(i));
                    }
                }
            }

            //cover
            String stringcoverType = fileLine[5];
            CoverType finalCoverType;
            if (stringcoverType.equals(CoverType.T.name())) {
                finalCoverType = CoverType.T;
            } else {
                finalCoverType = CoverType.M;
            }

            //category
            int exepctedID = Integer.parseInt(fileLine[6]) - 1;
            Category category = null;
            for (Category cat : BookData.getInstance().getCategories()) {
                if (exepctedID == cat.getID()) {
                    category = cat;
                }
            }

            Book book = new Book(numberInt, fileLine[1], isbntobeadded, yearInt, finalCoverType, authorList, category);
            bookList.add(book);
        }


        for (Book book : bookList)

        {
            System.out.println(book.toString());
        }

        System.out.println();
        return bookList;
    }
}