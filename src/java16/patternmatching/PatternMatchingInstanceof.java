package java16.patternmatching;

/**
 * Java 16 Pattern Matching for instanceof (Finalized)
 * Demonstrates simplified instanceof checks with automatic casting
 * 
 * NOTE: Pattern matching for instanceof is now a standard feature (finalized in Java 16)
 */
public class PatternMatchingInstanceof {
    public static void main(String[] args) {
        
        Object obj = "Hello";
        
        // 1. Basic pattern matching - automatic casting
        System.out.println("=== Basic Pattern Matching ===");
        if (obj instanceof String str) {
            // str is automatically available, no cast needed
            System.out.println("String: " + str.toUpperCase());
            System.out.println("Length: " + str.length());
        }
        
        // 2. With complex conditions
        System.out.println("\n=== With Conditions ===");
        Object value = "Hello World";
        
        if (value instanceof String str && str.length() > 5) {
            System.out.println("Long string: " + str);
        }
        
        // 3. Comparison with traditional approach
        System.out.println("\n=== Comparison: Traditional vs Pattern Matching ===");
        // Traditional way (before Java 16)
        if (obj instanceof String) {
            String str = (String) obj; // Manual cast required
            System.out.println("Traditional: " + str);
        }
        
        // Pattern matching way (Java 16+)
        if (obj instanceof String str) {
            System.out.println("Pattern matching: " + str); // No cast needed
        }
        
        // 4. Multiple types
        System.out.println("\n=== Processing Multiple Types ===");
        processObject("Hello");
        processObject(42);
        processObject(3.14);
        processObject(true);
        processObject(null);
        
        // 5. With negation
        System.out.println("\n=== With Negation ===");
        if (!(obj instanceof Integer num)) {
            System.out.println("Not an integer");
        } else {
            System.out.println("Integer: " + num);
        }
        
        // 6. Practical example with collections
        System.out.println("\n=== Practical Example ===");
        Object[] items = {"Hello", 42, 3.14, "World", 100};
        for (Object item : items) {
            if (item instanceof String str) {
                System.out.println("Found string: " + str.toUpperCase());
            } else if (item instanceof Integer num) {
                System.out.println("Found integer: " + (num * 2));
            } else if (item instanceof Double num) {
                System.out.println("Found double: " + (num * 2));
            }
        }
    }
    
    private static void processObject(Object obj) {
        if (obj instanceof String str) {
            System.out.println("String: " + str);
        } else if (obj instanceof Integer num) {
            System.out.println("Integer: " + num);
        } else if (obj instanceof Double num) {
            System.out.println("Double: " + num);
        } else if (obj instanceof Boolean bool) {
            System.out.println("Boolean: " + bool);
        } else {
            System.out.println("Other type: " + (obj != null ? obj.getClass().getSimpleName() : "null"));
        }
    }
}

