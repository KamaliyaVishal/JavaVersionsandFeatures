package java21.unnamedvariables;

/**
 * Java 21 Unnamed Variables and Patterns (Preview)
 * Demonstrates placeholder for unused variables
 * 
 * NOTE: Requires --enable-preview flag for compilation and execution
 * Compile: javac --enable-preview --release 21 UnnamedVariablesAndPatterns.java
 * Run: java --enable-preview UnnamedVariablesAndPatterns
 */
public class UnnamedVariablesAndPatterns {
    public static void main(String[] args) {
        System.out.println("=== Java 21 Unnamed Variables and Patterns (Preview) ===\n");
        
        // 1. Unnamed variables
        System.out.println("1. Unnamed Variables");
        System.out.println("-------------------");
        
        // Traditional way (unused variable warning)
        System.out.println("Traditional approach (may cause warnings):");
        for (int i = 0; i < 5; i++) {
            String result = process(i);
            // 'result' is not used - compiler warning
        }
        
        // With unnamed variable (no warning)
        System.out.println("\nWith unnamed variable (no warning):");
        for (int i = 0; i < 5; i++) {
            var _ = process(i);  // Unnamed variable
        }
        
        // 2. Catch block
        System.out.println("\n2. Unnamed Variable in Catch Block");
        System.out.println("----------------------------------");
        try {
            riskyOperation();
        } catch (Exception _) {
            // Don't use exception - unnamed variable
            System.out.println("Error occurred (exception details not needed)");
        }
        
        // 3. Assignment
        System.out.println("\n3. Unnamed Variable in Assignment");
        System.out.println("---------------------------------");
        int count = 0;
        for (int i = 0; i < 10; i++) {
            var _ = process(i);  // Side effect only
            count++;
        }
        System.out.println("Processed " + count + " items");
        
        // 4. Unnamed patterns
        System.out.println("\n4. Unnamed Patterns");
        System.out.println("-------------------");
        Object obj = new Point(5, 10);
        
        // Traditional way
        if (obj instanceof Point p) {
            int x = p.x();  // Only need x
            System.out.println("X coordinate: " + x);
        }
        
        // With unnamed pattern
        if (obj instanceof Point(int x, _)) {
            // Only use x, not y - unnamed pattern component
            System.out.println("X coordinate (unnamed pattern): " + x);
        }
        
        // 5. Nested patterns
        System.out.println("\n5. Unnamed Patterns in Nested Structures");
        System.out.println("----------------------------------------");
        Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 20));
        
        if (rect instanceof Rectangle(Point(int x1, _), Point(int x2, _))) {
            int width = x2 - x1;
            System.out.println("Width: " + width + " (y coordinates not needed)");
        }
        
        // 6. Switch expressions
        System.out.println("\n6. Unnamed Patterns in Switch");
        System.out.println("----------------------------");
        String result = switch (obj) {
            case Point(int x, _) when x > 0 -> "Positive X: " + x;
            case Point(int x, _) -> "Non-positive X: " + x;
            default -> "Not a point";
        };
        System.out.println("Result: " + result);
        
        System.out.println("\nBenefits:");
        System.out.println("- Eliminates compiler warnings for unused variables");
        System.out.println("- Makes intent clear (variable intentionally unused)");
        System.out.println("- Cleaner code in catch blocks");
        System.out.println("- Better pattern matching with partial deconstruction");
    }
    
    private static String process(int i) {
        // Simulate processing
        return "Result-" + i;
    }
    
    private static void riskyOperation() {
        // Simulate operation that might throw exception
        if (Math.random() > 0.5) {
            throw new RuntimeException("Random error");
        }
    }
    
    record Point(int x, int y) {}
    record Rectangle(Point topLeft, Point bottomRight) {}
}

