package java10.optional;

import java.util.Optional;

/**
 * Java 10 Optional.orElseThrow() Enhancement
 * Demonstrates the no-arg version of orElseThrow()
 */
public class OptionalOrElseThrow {
    public static void main(String[] args) {
        
        // 1. orElseThrow() with no arguments
        System.out.println("=== orElseThrow() No-Args Version ===");
        Optional<String> optional = Optional.of("Value");
        
        String value = optional.orElseThrow();
        System.out.println("Value: " + value);
        
        // 2. Empty Optional throws NoSuchElementException
        System.out.println("\n=== Empty Optional ===");
        Optional<String> empty = Optional.empty();
        
        try {
            String result = empty.orElseThrow();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Exception thrown: " + e.getClass().getSimpleName());
        }
        
        // 3. Comparison with old approach
        System.out.println("\n=== Comparison with Old Approach ===");
        Optional<String> name = findName(123);
        
        // Old way (Java 9)
        if (!name.isPresent()) {
            throw new IllegalArgumentException("Name not found");
        }
        String oldWay = name.get();
        System.out.println("Old way result: " + oldWay);
        
        // New way (Java 10)
        Optional<String> name2 = findName(456);
        try {
            String newWay = name2.orElseThrow();
            System.out.println("New way result: " + newWay);
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Name not found (new way): " + e.getClass().getSimpleName());
        }
        
        // 4. Practical example
        System.out.println("\n=== Practical Example ===");
        Optional<String> config = getConfiguration("database.url");
        
        // Cleaner code
        try {
            String url = config.orElseThrow();
            System.out.println("Database URL: " + url);
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Configuration not found");
        }
    }
    
    private static Optional<String> findName(int id) {
        if (id == 123) {
            return Optional.of("John Doe");
        }
        return Optional.empty();
    }
    
    private static Optional<String> getConfiguration(String key) {
        // Simulate configuration lookup
        if ("database.url".equals(key)) {
            return Optional.of("jdbc:mysql://localhost:3306/mydb");
        }
        return Optional.empty();
    }
}

