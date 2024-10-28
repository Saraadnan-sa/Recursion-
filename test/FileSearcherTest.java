package test;

import lab7.fileSearcher; // Ensure this import matches your main file path
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Map;

public class FileSearcherTest {

    @Test
    public void testFileFound() {
        // Test case for a file that should be found
        File directory = new File("C:/Users/saraa/Downloads");
        String[] fileNames = {"Lab 07 - Recursion.docx"};
        Map<String, Integer> results = fileSearcher.searchFiles(directory, fileNames, false);
        assertEquals(1, results.get("Lab 07 - Recursion.docx"));
    }

    @Test
    public void testFileNotFound() {
        // Test case for a file that should not be found
        File directory = new File("C:/Users/saraa/Downloads");
        String[] fileNames = {"NonExistentFile.docx"};
        Map<String, Integer> results = fileSearcher.searchFiles(directory, fileNames, false);
        assertEquals(0, results.get("NonExistentFile.docx"));
    }

    @Test
    public void testCaseSensitiveSearch() {
        // Test case for case-sensitive search
        File directory = new File("C:/Users/saraa/Downloads");
        String[] fileNames = {"lab 07 - recursion.docx"}; // Lowercase intentionally
        Map<String, Integer> results = fileSearcher.searchFiles(directory, fileNames, true);
        assertEquals(0, results.get("lab 07 - recursion.docx"));
    }

    @Test
    public void testMultipleFilesSearch() {
        // Test case for multiple files search
        File directory = new File("C:/Users/saraa/Downloads");
        String[] fileNames = {"Lab 07 - Recursion.docx", "AnotherFile.docx"};
        Map<String, Integer> results = fileSearcher.searchFiles(directory, fileNames, false);
        assertTrue(results.get("Lab 07 - Recursion.docx") > 0); // Assuming it exists
        assertEquals(0, results.get("AnotherFile.docx")); // Assuming it does not exist
    }
}
