package java11.programs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Practical examples using Java 11 string methods
 */
public class StringProcessing {
    public static void main(String[] args) {
        
        // Example 1: Process multi-line text
        System.out.println("=== Process Multi-line Text ===");
        String text = "Line 1\nLine 2\n  \nLine 3\nLine 4";
        
        List<String> nonBlankLines = text.lines()
            .filter(line -> !line.isBlank())
            .map(line -> "> " + line)
            .collect(Collectors.toList());
        
        System.out.println("Non-blank lines:");
        nonBlankLines.forEach(System.out::println);
        
        // Example 2: Clean user input
        System.out.println("\n=== Clean User Input ===");
        String[] inputs = {"  hello  ", "\t\nworld\t\n", "  ", "test"};
        
        List<String> cleaned = Arrays.stream(inputs)
            .map(String::strip)
            .filter(s -> !s.isBlank())
            .collect(Collectors.toList());
        
        System.out.println("Cleaned inputs: " + cleaned);
        
        // Example 3: Format output
        System.out.println("\n=== Format Output ===");
        String separator = "=".repeat(30);
        System.out.println(separator);
        System.out.println("  Formatted Output");
        System.out.println(separator);
        
        String[] items = {"Item 1", "Item 2", "Item 3"};
        for (var item : items) {
            System.out.println("  - " + item);
        }
        
        // Example 4: Count lines
        System.out.println("\n=== Count Lines ===");
        String document = "Paragraph 1\n\nParagraph 2\n\nParagraph 3";
        long paragraphCount = document.lines()
            .filter(line -> !line.isBlank())
            .count();
        
        System.out.println("Paragraph count: " + paragraphCount);
    }
}

