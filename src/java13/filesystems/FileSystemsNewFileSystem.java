package java13.filesystems;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * Java 13 FileSystems.newFileSystem() Demo
 * 
 * Demonstrates the new FileSystems.newFileSystem() method for creating file systems,
 * particularly useful for working with ZIP files as file systems.
 */
public class FileSystemsNewFileSystem {
    public static void main(String[] args) {
        
        System.out.println("=== FileSystems.newFileSystem() Demo ===\n");
        
        // Note: This example demonstrates the concept
        // In a real scenario, you would have an actual ZIP file
        
        System.out.println("--- Basic Usage ---");
        System.out.println("// Create a file system from a ZIP file");
        System.out.println("Path zipPath = Paths.get(\"archive.zip\");");
        System.out.println("FileSystem fs = FileSystems.newFileSystem(zipPath, null);");
        System.out.println();
        System.out.println("// Access files within the ZIP");
        System.out.println("Path fileInZip = fs.getPath(\"file.txt\");");
        System.out.println("String content = Files.readString(fileInZip);");
        System.out.println();
        
        // Example: Working with ZIP file as file system
        System.out.println("--- Example: ZIP File as File System ---");
        try {
            // Create a temporary ZIP file for demonstration
            Path tempZip = Files.createTempFile("demo", ".zip");
            
            // Create a file system from the ZIP
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            
            try (FileSystem zipFs = FileSystems.newFileSystem(
                    URI.create("jar:" + tempZip.toUri()), env)) {
                
                System.out.println("Created file system from ZIP: " + tempZip);
                System.out.println("File system provider: " + zipFs.provider());
                System.out.println("Separator: " + zipFs.getSeparator());
                
                // Create a file inside the ZIP
                Path fileInZip = zipFs.getPath("example.txt");
                Files.writeString(fileInZip, "Hello from inside ZIP!");
                System.out.println("\nCreated file in ZIP: " + fileInZip);
                
                // Read the file back
                String content = Files.readString(fileInZip);
                System.out.println("Content: " + content);
                
                // List files in ZIP root
                System.out.println("\nFiles in ZIP:");
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(zipFs.getPath("/"))) {
                    for (Path entry : stream) {
                        System.out.println("  - " + entry);
                    }
                }
            }
            
            // Clean up
            Files.deleteIfExists(tempZip);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n--- Use Cases ===");
        System.out.println("1. Reading files from ZIP archives");
        System.out.println("2. Writing files to ZIP archives");
        System.out.println("3. Working with JAR files as file systems");
        System.out.println("4. Processing nested archives");
        System.out.println("5. Extracting files from archives");
        
        System.out.println("\n--- Benefits ===");
        System.out.println("1. Treat ZIP/JAR files as file systems");
        System.out.println("2. Use standard Files API");
        System.out.println("3. No need for special ZIP libraries");
        System.out.println("4. Cleaner code");
        System.out.println("5. Better integration with NIO.2");
        
        System.out.println("\n--- Example: Reading from Existing ZIP ---");
        System.out.println("try (FileSystem zipFs = FileSystems.newFileSystem(");
        System.out.println("        Paths.get(\"archive.zip\"), null)) {");
        System.out.println("    ");
        System.out.println("    Path file = zipFs.getPath(\"data\", \"file.txt\");");
        System.out.println("    String content = Files.readString(file);");
        System.out.println("    ");
        System.out.println("    // Process content");
        System.out.println("} catch (IOException e) {");
        System.out.println("    e.printStackTrace();");
        System.out.println("}");
    }
}

