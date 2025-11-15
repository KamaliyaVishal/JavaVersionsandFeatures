package java11.optional;

import java.util.Optional;

/**
 * Java 11 Optional.isEmpty() Method
 * Demonstrates the isEmpty() method for checking empty Optionals
 */
public class OptionalIsEmpty {
    public static void main(String[] args) {
        
        // 1. isEmpty() - More readable than !isPresent()
        System.out.println("=== isEmpty() Examples ===");
        Optional<String> optional1 = Optional.of("Value");
        Optional<String> optional2 = Optional.empty();
        
        System.out.println("optional1.isEmpty(): " + optional1.isEmpty()); // false
        System.out.println("optional2.isEmpty(): " + optional2.isEmpty()); // true
        
        // 2. Comparison with isPresent()
        System.out.println("\n=== Comparison ===");
        Optional<String> name = findName(123);
        
        // Old way (before Java 11)
        if (!name.isPresent()) {
            System.out.println("Name not found (old way)");
        }
        
        // New way (Java 11) - more readable
        if (name.isEmpty()) {
            System.out.println("Name not found (new way)");
        }
        
        // 3. Practical example
        System.out.println("\n=== Practical Example ===");
        Optional<String> config = getConfiguration("app.name");
        
        if (config.isEmpty()) {
            System.out.println("Configuration not found, using default");
            config = Optional.of("DefaultApp");
        }
        
        System.out.println("App name: " + config.get());
        
        // 4. Chain with orElse
        System.out.println("\n=== Chaining ===");
        Optional<String> value = Optional.empty();
        
        if (value.isEmpty()) {
            value = Optional.of("Default");
        }
        
        System.out.println("Value: " + value.get());
        
        // Or more concisely
        String result = value.orElse("Fallback");
        System.out.println("Result: " + result);
    }
    
    private static Optional<String> findName(int id) {
        if (id == 123) {
            return Optional.of("John Doe");
        }
        return Optional.empty();
    }
    
    private static Optional<String> getConfiguration(String key) {
        if ("app.name".equals(key)) {
            return Optional.of("MyApplication");
        }
        return Optional.empty();
    }
}

