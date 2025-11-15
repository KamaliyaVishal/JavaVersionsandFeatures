package java11.files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;

/**
 * Java 11 Files Methods Enhancements
 * Demonstrates readString() and writeString()
 */
public class FilesMethods {
    public static void main(String[] args) {
        
        try {
            // Create a temporary file for demonstration
            Path tempFile = Files.createTempFile("demo", ".txt");
            
            // 1. writeString() - Write string to file
            System.out.println("=== writeString() Examples ===");
            String content = "Hello, World!\nThis is a test file.\nJava 11 is great!";
            Files.writeString(tempFile, content);
            System.out.println("Content written to: " + tempFile);
            
            // Append mode
            Files.writeString(tempFile, "\nNew line added", StandardOpenOption.APPEND);
            System.out.println("Content appended");
            
            // 2. readString() - Read entire file as string
            System.out.println("\n=== readString() Examples ===");
            String readContent = Files.readString(tempFile);
            System.out.println("Read content:");
            System.out.println(readContent);
            
            // With charset
            String readWithCharset = Files.readString(tempFile, StandardCharsets.UTF_8);
            System.out.println("Read with charset: " + readWithCharset.length() + " characters");
            
            // Comparison with old approach
            System.out.println("\n=== Comparison ===");
            // Old way (before Java 11)
            // String oldWay = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            // Files.write(path, text.getBytes(StandardCharsets.UTF_8));
            
            // New way (Java 11) - much simpler
            String newWay = Files.readString(tempFile);
            Files.writeString(tempFile, "Updated content");
            
            System.out.println("New way is simpler and more readable");
            
            // Practical example
            System.out.println("\n=== Practical Example ===");
            Path configFile = Files.createTempFile("config", ".properties");
            String configContent = "app.name=MyApplication\napp.version=1.0\napp.debug=true";
            Files.writeString(configFile, configContent);
            
            String loadedConfig = Files.readString(configFile);
            System.out.println("Loaded config:");
            System.out.println(loadedConfig);
            
            // Clean up
            Files.deleteIfExists(tempFile);
            Files.deleteIfExists(configFile);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

