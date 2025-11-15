package java11.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Java 11 Predicate.not() Method
 * Demonstrates negating predicates with Predicate.not()
 */
public class PredicateNot {
    public static void main(String[] args) {
        
        List<String> names = Arrays.asList("Alice", "", "Bob", "  ", "Charlie", "");
        
        // 1. Predicate.not() - Negate a predicate
        System.out.println("=== Predicate.not() Examples ===");
        
        // Filter non-blank strings
        List<String> nonBlank = names.stream()
            .filter(Predicate.not(String::isBlank))
            .collect(Collectors.toList());
        
        System.out.println("Original: " + names);
        System.out.println("Non-blank: " + nonBlank);
        
        // 2. Comparison with old approach
        System.out.println("\n=== Comparison ===");
        // Old way (before Java 11)
        List<String> oldWay = names.stream()
            .filter(s -> !s.isBlank())
            .collect(Collectors.toList());
        
        // New way (Java 11)
        List<String> newWay = names.stream()
            .filter(Predicate.not(String::isBlank))
            .collect(Collectors.toList());
        
        System.out.println("Old way: " + oldWay);
        System.out.println("New way: " + newWay);
        
        // 3. With method references
        System.out.println("\n=== With Method References ===");
        List<String> nonEmpty = names.stream()
            .filter(Predicate.not(String::isEmpty))
            .collect(Collectors.toList());
        
        System.out.println("Non-empty: " + nonEmpty);
        
        // 4. Complex predicates
        System.out.println("\n=== Complex Predicates ===");
        Predicate<String> isShort = s -> s.length() <= 3;
        
        List<String> notShort = names.stream()
            .filter(Predicate.not(isShort))
            .collect(Collectors.toList());
        
        System.out.println("Not short (length > 3): " + notShort);
        
        // 5. Practical example
        System.out.println("\n=== Practical Example ===");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter even numbers
        List<Integer> evens = numbers.stream()
            .filter(Predicate.not(n -> n % 2 != 0))
            .collect(Collectors.toList());
        
        System.out.println("Even numbers: " + evens);
        
        // Filter numbers not divisible by 3
        List<Integer> notDivisibleBy3 = numbers.stream()
            .filter(Predicate.not(n -> n % 3 == 0))
            .collect(Collectors.toList());
        
        System.out.println("Not divisible by 3: " + notDivisibleBy3);
    }
}

