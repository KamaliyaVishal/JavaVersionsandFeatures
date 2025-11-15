package java11.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Java 11 Local-Variable Syntax for Lambda Parameters
 * Demonstrates using var in lambda parameters
 */
public class VarInLambda {
    public static void main(String[] args) {
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // 1. Using var in lambda parameters
        System.out.println("=== var in Lambda Parameters ===");
        names.forEach((var name) -> System.out.println("Name: " + name));
        
        // 2. Single parameter with var
        System.out.println("\n=== Single Parameter ===");
        Function<String, Integer> lengthFunction = (var s) -> s.length();
        names.stream()
            .map(lengthFunction)
            .forEach(length -> System.out.println("Length: " + length));
        
        // 3. Multiple parameters with var
        System.out.println("\n=== Multiple Parameters ===");
        Map<String, Integer> map = Map.of("One", 1, "Two", 2, "Three", 3);
        
        // All parameters must use var if using it
        map.forEach((var key, var value) -> 
            System.out.println(key + ": " + value));
        
        // 4. With annotations
        System.out.println("\n=== With Annotations ===");
        // Note: Annotations example (conceptual - @NonNull may need custom annotation)
        names.forEach((var name) -> {
            if (name != null) {
                System.out.println("Processed: " + name.toUpperCase());
            }
        });
        
        // 5. Comparison
        System.out.println("\n=== Comparison ===");
        // Before Java 11
        names.forEach((String name) -> System.out.println("Old: " + name));
        
        // Java 11 - can use var
        names.forEach((var name) -> System.out.println("New: " + name));
        
        // Cannot mix var and explicit types
        // map.forEach((var key, String value) -> ...); // Compile error
        
        // Practical example
        System.out.println("\n=== Practical Example ===");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        numbers.stream()
            .filter((var n) -> n % 2 == 0)
            .map((var n) -> n * 2)
            .forEach((var n) -> System.out.println("Doubled even: " + n));
    }
}

