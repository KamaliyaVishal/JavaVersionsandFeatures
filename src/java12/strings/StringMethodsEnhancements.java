package java12.strings;

import java.util.function.Function;

/**
 * Java 12 String Methods Enhancements
 * 
 * Demonstrates new methods added to String class:
 * - indent(): Add or remove indentation from each line
 * - transform(): Apply a function to the string
 */
public class StringMethodsEnhancements {
    public static void main(String[] args) {
        
        System.out.println("=== String.indent() ===");
        
        // 1. Basic indent() usage
        String text = "Line 1\nLine 2\nLine 3";
        System.out.println("Original:");
        System.out.println(text);
        
        // Add indentation
        String indented = text.indent(4);
        System.out.println("\nAfter indent(4):");
        System.out.println(indented);
        
        // Remove indentation (negative value)
        String unindented = indented.indent(-2);
        System.out.println("After indent(-2):");
        System.out.println(unindented);
        
        // 2. Practical indent examples
        System.out.println("\n--- Practical Indent Examples ---");
        String code = "public class Test {\n    public void method() {\n        // code\n    }\n}";
        System.out.println("Original code:");
        System.out.println(code);
        
        String indentedCode = code.indent(2);
        System.out.println("\nIndented code (2 spaces):");
        System.out.println(indentedCode);
        
        // 3. Multi-line text formatting
        System.out.println("\n--- Multi-line Formatting ---");
        String multiLine = "First\nSecond\nThird";
        String formatted = multiLine.lines()
            .map(line -> "  " + line)  // Add prefix
            .reduce((a, b) -> a + "\n" + b)
            .orElse("");
        System.out.println("Formatted:");
        System.out.println(formatted);
        
        // Using indent() is simpler
        String simpleFormatted = multiLine.indent(2);
        System.out.println("\nUsing indent(2):");
        System.out.println(simpleFormatted);
        
        System.out.println("\n=== String.transform() ===");
        
        // 4. Basic transform() usage
        String original = "Hello, World!";
        
        // Transform to uppercase
        String upper = original.transform(String::toUpperCase);
        System.out.println("Original: " + original);
        System.out.println("Uppercase: " + upper);
        
        // Transform to lowercase
        String lower = original.transform(String::toLowerCase);
        System.out.println("Lowercase: " + lower);
        
        // 5. Complex transformations
        System.out.println("\n--- Complex Transformations ---");
        
        // Reverse string
        String reversed = original.transform(s -> 
            new StringBuilder(s).reverse().toString());
        System.out.println("Reversed: " + reversed);
        
        // Add prefix and suffix
        String decorated = original.transform(s -> "[" + s + "]");
        System.out.println("Decorated: " + decorated);
        
        // Multiple transformations (chaining)
        String result = original
            .transform(String::toUpperCase)
            .transform(s -> s.replace(" ", "-"))
            .transform(s -> "***" + s + "***");
        System.out.println("Chained: " + result);
        
        // 6. Transform with custom function
        System.out.println("\n--- Custom Transform Functions ---");
        
        Function<String, String> addTimestamp = s -> 
            s + " [Generated: " + System.currentTimeMillis() + "]";
        
        String timestamped = "Data".transform(addTimestamp);
        System.out.println("Timestamped: " + timestamped);
        
        // Transform to different type (via method reference)
        Function<String, Integer> lengthTransform = String::length;
        Integer length = original.transform(lengthTransform);
        System.out.println("Length: " + length);
        
        // 7. Practical examples
        System.out.println("\n=== Practical Examples ===");
        
        // JSON formatting
        System.out.println("\n--- JSON Formatting ---");
        String json = "{\"name\":\"John\",\"age\":30}";
        String formattedJson = json
            .transform(s -> s.replace(",", ",\n"))
            .transform(s -> s.replace("{", "{\n"))
            .transform(s -> s.replace("}", "\n}"))
            .indent(2);
        System.out.println("Formatted JSON:");
        System.out.println(formattedJson);
        
        // Code formatting
        System.out.println("\n--- Code Formatting ---");
        String codeBlock = "if (condition) {\n    doSomething();\n}";
        String formattedCode = codeBlock.indent(4);
        System.out.println("Formatted code:");
        System.out.println(formattedCode);
        
        // Data transformation pipeline
        System.out.println("\n--- Data Transformation Pipeline ---");
        String data = "  user@example.com  ";
        String processed = data
            .transform(String::trim)
            .transform(String::toLowerCase)
            .transform(s -> s.replace("@", " [at] "));
        System.out.println("Original: '" + data + "'");
        System.out.println("Processed: '" + processed + "'");
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. indent(): Easy multi-line text formatting");
        System.out.println("2. transform(): Functional string operations");
        System.out.println("3. Chainable: Can chain multiple transformations");
        System.out.println("4. More readable: Clear intent in code");
        System.out.println("5. Less boilerplate: No need for temporary variables");
        
        System.out.println("\n=== Comparison: Before vs After ===");
        System.out.println("Before:");
        System.out.println("  String temp = text.toUpperCase();");
        System.out.println("  String result = temp.replace(\" \", \"-\");");
        System.out.println("\nAfter:");
        System.out.println("  String result = text");
        System.out.println("      .transform(String::toUpperCase)");
        System.out.println("      .transform(s -> s.replace(\" \", \"-\"));");
    }
}

