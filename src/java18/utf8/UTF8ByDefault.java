package java18.utf8;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Java 18 UTF-8 by Default
 * Demonstrates UTF-8 as the default charset
 */
public class UTF8ByDefault {
    public static void main(String[] args) {
        try {
            // UTF-8 is now the default charset
            String text = "Hello, 世界! 🌍";
            
            // Write string to file - UTF-8 by default
            Path filePath = Paths.get("utf8-example.txt");
            Files.writeString(filePath, text);
            System.out.println("Written to file (UTF-8 by default): " + text);
            
            // Read string from file - UTF-8 by default
            String readText = Files.readString(filePath);
            System.out.println("Read from file (UTF-8 by default): " + readText);
            
            // No need to specify charset in most cases
            // Files.writeString(path, text, StandardCharsets.UTF_8); // Not needed
            
            // Clean up
            Files.deleteIfExists(filePath);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

