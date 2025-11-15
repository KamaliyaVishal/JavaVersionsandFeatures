package java14.textblocks;

/**
 * Java 14 Text Blocks (Second Preview)
 * 
 * Demonstrates multi-line string literals with refinements from Java 13.
 * Requires --enable-preview flag to compile and run.
 * 
 * Compile: javac --enable-preview --release 14 TextBlocks.java
 * Run: java --enable-preview TextBlocks
 */
public class TextBlocks {
    public static void main(String[] args) {
        
        System.out.println("=== Text Blocks (Second Preview) ===\n");
        
        // 1. Basic text block
        System.out.println("--- Basic Text Block ---");
        String html = """
            <html>
                <body>
                    <p>Hello, World!</p>
                </body>
            </html>
            """;
        System.out.println(html);
        
        // 2. JSON example
        System.out.println("\n--- JSON Example ---");
        String json = """
            {
                "name": "John",
                "age": 30,
                "city": "New York"
            }
            """;
        System.out.println(json);
        
        // 3. SQL example
        System.out.println("\n--- SQL Example ---");
        String sql = """
            SELECT id, name, email
            FROM users
            WHERE status = 'active'
            ORDER BY name
            """;
        System.out.println(sql);
        
        // 4. Escape sequences
        System.out.println("\n--- Escape Sequences ---");
        // Quotes don't need escaping (except for triple quotes)
        String text = """
            She said "Hello"
            """;
        System.out.println(text);
        
        // Escape newline (suppress line break)
        String text2 = """
            Line 1 \
            Line 2
            """;
        System.out.println("Escape newline: [" + text2 + "]"); // Results in "Line 1 Line 2"
        
        // Escape sequences work as expected
        String text3 = """
            Tab:\tTab
            Newline:\nNewline
            """;
        System.out.println(text3);
        
        // 5. Indentation handling
        System.out.println("\n--- Indentation Handling ---");
        String code = """
            public void method() {
                System.out.println("Hello");
            }
            """;
        System.out.println(code);
        
        // Add indentation
        String indented = """
            public void method() {
                System.out.println("Hello");
            }
            """.indent(4);
        System.out.println("With indent(4):");
        System.out.println(indented);
        
        // 6. Comparison with old approach
        System.out.println("\n--- Comparison: Old vs New ---");
        // Old way
        String oldWay = "<html>\n" +
                       "    <body>\n" +
                       "        <p>Hello</p>\n" +
                       "    </body>\n" +
                       "</html>";
        System.out.println("Old way (concatenation):");
        System.out.println(oldWay);
        
        // New way
        String newWay = """
            <html>
                <body>
                    <p>Hello</p>
                </body>
            </html>
            """;
        System.out.println("\nNew way (text blocks):");
        System.out.println(newWay);
        System.out.println("\nText blocks are much more readable!");
        
        // 7. Using formatted() with text blocks
        System.out.println("\n--- Using formatted() with Text Blocks ---");
        String name = "Java";
        int version = 14;
        String message = """
            Welcome to %s %d!
            Text blocks make multi-line strings easy.
            """.formatted(name, version);
        System.out.println(message);
        
        // 8. Practical example: API response template
        System.out.println("\n--- Practical Example: API Response ---");
        String apiResponse = """
            {
                "status": "success",
                "data": {
                    "message": "Operation completed"
                }
            }
            """;
        System.out.println(apiResponse);
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. Better readability for multi-line strings");
        System.out.println("2. Preserves formatting");
        System.out.println("3. Easier to write HTML, JSON, SQL, etc.");
        System.out.println("4. Reduces escape sequences needed");
        System.out.println("5. More maintainable code");
    }
}

