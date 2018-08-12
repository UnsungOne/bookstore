package utils;

import pojo.Category;
import static org.assertj.core.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReaderTest {

    CSVFileReader csvFileReader = new CSVFileReader();

    @org.junit.jupiter.api.Test
    public void expectListsToBeEqual() throws IOException {
        String categoryFile = "categories.csv";
        List<Category> expectedCategory = new ArrayList<>();
        expectedCategory.add(new Category(1, "Java", 3));
        expectedCategory.add(new Category(2, "Wzorce projektowe", 1));
        expectedCategory.add(new Category(3, "Techniki programowania", 2));


        List<Category> actualCategoryList = csvFileReader.importCategoriesFromFile(categoryFile);
        assertThat(expectedCategory).isEqualTo(actualCategoryList);
    }

    @org.junit.Test(expected = IOException.class)
    public void shouldThrowFileNotFoundException() throws IOException {
        String categoryFile = "whatever.csv";
        csvFileReader.importCategoriesFromFile(categoryFile);
    }

}