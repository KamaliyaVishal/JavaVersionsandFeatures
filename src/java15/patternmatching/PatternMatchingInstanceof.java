package java15.patternmatching;

/**
 * Java 15 Pattern Matching for instanceof (Second Preview)
 *
 * Demonstrates refinements to pattern matching based on feedback.
 * Requires --enable-preview flag to compile and run.
 *
 * Compile: javac --enable-preview --release 15 PatternMatchingInstanceof.java
 * Run: java --enable-preview PatternMatchingInstanceof
 */
public class PatternMatchingInstanceof {
    public static void main(String[] args) {

        System.out.println("=== Pattern Matching for instanceof (Second Preview) ===\n");

        // 1. Basic pattern matching
        System.out.println("--- Basic Pattern Matching ---");
        Object obj = "Hello, Java 15!";

        if (obj instanceof String str) {
            // str is automatically cast and available
            System.out.println("String: " + str.toUpperCase());
            System.out.println("Length: " + str.length());
        }

        // 2. With conditions
        System.out.println("\n--- Pattern Matching with Conditions ---");
        Object value = "Hello World";

        if (value instanceof String str && str.length() > 5) {
            System.out.println("Long string: " + str);
        }

        // 3. Multiple types
        System.out.println("\n--- Multiple Types ---");
        processObject("Hello");
        processObject(42);
        processObject(3.14);
        processObject(true);
        processObject(null);

        // 4. With negation
        System.out.println("\n--- Pattern Matching with Negation ---");
        Object testObj = "Test";

        if (!(testObj instanceof Integer num)) {
            System.out.println("Not an integer");
        } else {
            System.out.println("Integer: " + num);
        }

        // 5. Complex example
        System.out.println("\n--- Complex Example ---");
        Object[] items = {"apple", 10, "banana", 20.5, "cherry", true, null};

        for (Object item : items) {
            if (item instanceof String s) {
                System.out.println("String item: " + s.toUpperCase());
            } else if (item instanceof Integer i) {
                System.out.println("Integer item: " + (i * 10));
            } else if (item instanceof Double d) {
                System.out.println("Double item: " + (d + 1.0));
            } else if (item instanceof Boolean b) {
                System.out.println("Boolean item: " + !b);
            } else {
                System.out.println("Unknown or null item: " + item);
            }
        }

        // 6. Comparison with traditional approach
        System.out.println("\n--- Comparison: Traditional vs Pattern Matching ---");
        Object obj2 = "Hello";

        // Traditional way
        if (obj2 instanceof String) {
            String str = (String) obj2; // Manual cast
            System.out.println("Traditional: " + str.toUpperCase());
        }

        // Pattern matching way
        if (obj2 instanceof String str) {
            System.out.println("Pattern matching: " + str.toUpperCase()); // No cast needed
        }

        System.out.println("\n=== Benefits ===");
        System.out.println("1. Eliminates manual casting");
        System.out.println("2. Reduces boilerplate code");
        System.out.println("3. Prevents cast errors");
        System.out.println("4. Improves readability");
        System.out.println("5. Type-safe operations");
    }

    private static void processObject(Object obj) {
        if (obj instanceof String str) {
            System.out.println("Processed String: " + str.length() + " chars");
        } else if (obj instanceof Integer i) {
            System.out.println("Processed Integer: Value " + i);
        } else if (obj instanceof Double d) {
            System.out.println("Processed Double: Value " + d);
        } else if (obj instanceof Boolean b) {
            System.out.println("Processed Boolean: " + b);
        } else if (obj == null) {
            System.out.println("Processed Null object");
        } else {
            System.out.println("Processed Unknown type: " + obj.getClass().getSimpleName());
        }
    }
}

