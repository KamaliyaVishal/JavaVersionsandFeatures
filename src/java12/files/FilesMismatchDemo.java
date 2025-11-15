package java12.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Java 12 Files.mismatch() Demo
 * 
 * Demonstrates the new Files.mismatch() method for efficiently comparing two files.
 * Returns the position of the first differing byte, or -1 if files are identical.
 */
public class FilesMismatchDemo {
    public static void main(String[] args) {
        
        System.out.println("=== Files.mismatch() Demo ===");

        try {
            // 1. Create test files
            Path file1 = Paths.get("test1.txt");
            Path file2 = Paths.get("test2.txt");
            Path file3 = Paths.get("test3.txt");
            
            // Create identical files
            String content1 = "Hello, World!\nThis is a test file.\nJava 12 is great!";
            Files.writeString(file1, content1);
            Files.writeString(file2, content1);
            
            // Create different file
            String content2 = "Hello, World!\nThis is a different file.\nJava 12 is great!";
            Files.writeString(file3, content2);
            
            // 2. Compare identical files
            System.out.println("\n--- Comparing Identical Files ---");
            long mismatch1 = Files.mismatch(file1, file2);
            if (mismatch1 == -1) {
                System.out.println("Files are identical (mismatch = -1)");
            } else {
                System.out.println("Files differ at position: " + mismatch1);
            }
            
            // 3. Compare different files
            System.out.println("\n--- Comparing Different Files ---");
            long mismatch2 = Files.mismatch(file1, file3);
            if (mismatch2 == -1) {
                System.out.println("Files are identical");
            } else {
                System.out.println("Files differ at byte position: " + mismatch2);
                System.out.println("First difference at: " + mismatch2);
            }
            
            // 4. Compare files of different sizes
            System.out.println("\n--- Comparing Files of Different Sizes ---");
            Path file4 = Paths.get("test4.txt");
            Files.writeString(file4, "Short content");
            
            long mismatch3 = Files.mismatch(file1, file4);
            if (mismatch3 == -1) {
                System.out.println("Files are identical");
            } else {
                System.out.println("Files differ at position: " + mismatch3);
            }
            
            // 5. Practical example: File synchronization check
            System.out.println("\n=== Practical Example: File Synchronization ===");
            boolean filesMatch = Files.mismatch(file1, file2) == -1;
            if (filesMatch) {
                System.out.println("Files are synchronized - no update needed");
            } else {
                System.out.println("Files differ - synchronization required");
            }
            
            // 6. Compare with empty file
            System.out.println("\n--- Comparing with Empty File ---");
            Path emptyFile = Paths.get("empty.txt");
            Files.writeString(emptyFile, "");
            
            long mismatch4 = Files.mismatch(file1, emptyFile);
            System.out.println("Mismatch with empty file: " + mismatch4);
            // Will be 0 (first byte differs) or -1 if both are empty
            
            // 7. Performance comparison
            System.out.println("\n=== Performance Benefits ===");
            System.out.println("Files.mismatch() is optimized for:");
            System.out.println("1. Early termination (stops at first difference)");
            System.out.println("2. Efficient byte-by-byte comparison");
            System.out.println("3. Memory-efficient (doesn't load entire files)");
            
            // Cleanup
            Files.deleteIfExists(file1);
            Files.deleteIfExists(file2);
            Files.deleteIfExists(file3);
            Files.deleteIfExists(file4);
            Files.deleteIfExists(emptyFile);
            
            System.out.println("\n=== Benefits ===");
            System.out.println("1. Efficient file comparison");
            System.out.println("2. Returns first differing byte position");
            System.out.println("3. Returns -1 if files are identical");
            System.out.println("4. Useful for file synchronization");
            System.out.println("5. Better than reading entire files into memory");
            
            System.out.println("\n=== Comparison: Old Way vs New Way ===");
            System.out.println("Old way:");
            System.out.println("  byte[] bytes1 = Files.readAllBytes(file1);");
            System.out.println("  byte[] bytes2 = Files.readAllBytes(file2);");
            System.out.println("  boolean equal = Arrays.equals(bytes1, bytes2);");
            System.out.println("\nNew way (Java 12):");
            System.out.println("  long mismatch = Files.mismatch(file1, file2);");
            System.out.println("  boolean equal = (mismatch == -1);");
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

