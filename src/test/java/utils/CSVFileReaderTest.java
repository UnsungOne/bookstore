package utils;

import pojo.Category;
import static org.assertj.core.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

public class CSVFileReaderTest {

    @org.junit.jupiter.api.Test
    public void expectTheFileToBeImportedSuccesfully() {

        CSVFileReader csvFileReader = new CSVFileReader();

        List<Category> expectedCategory = new ArrayList<>();
        expectedCategory.add(new Category(1, "Java", 3));
        expectedCategory.add(new Category(2, "Wzorce projektowe", 1));
        expectedCategory.add(new Category(3, "Techniki programowania", 2));

        List<Category> actualCategoryList = csvFileReader.importCategoriesFromFile();
        assertThat(expectedCategory)
                .isEqualTo(actualCategoryList);
    }
}