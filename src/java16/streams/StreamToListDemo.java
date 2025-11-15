package java16.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 16 Stream API Enhancements - toList() Method
 * Demonstrates the new toList() method for collecting streams into unmodifiable lists
 * 
 * NOTE: toList() is a standard feature in Java 16 (no preview flag needed)
 */
public class StreamToListDemo {
    public static void main(String[] args) {
        
        System.out.println("=== Java 16 Stream.toList() Method ===\n");
        
        // 1. Basic usage
        System.out.println("1. Basic Usage");
        System.out.println("-------------");
        List<String> names = Stream.of("Alice", "Bob", "Charlie", "David")
            .filter(name -> name.length() > 3)
            .toList();  // Returns unmodifiable list
        
        System.out.println("Filtered names: " + names);
        System.out.println("List type: " + names.getClass().getSimpleName());
        
        // 2. Comparison with traditional approach
        System.out.println("\n2. Comparison: Old vs New Way");
        System.out.println("-----------------------------");
        
        // Old way (Java 8-15)
        List<String> oldWay = Stream.of("Apple", "Banana", "Cherry")
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        
        // New way (Java 16+)
        List<String> newWay = Stream.of("Apple", "Banana", "Cherry")
            .map(String::toUpperCase)
            .toList();
        
        System.out.println("Old way (collect): " + oldWay);
        System.out.println("New way (toList): " + newWay);
        System.out.println("Results are equal: " + oldWay.equals(newWay));
        
        // 3. Unmodifiable list
        System.out.println("\n3. Unmodifiable List");
        System.out.println("-------------------");
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5)
            .filter(n -> n % 2 == 0)
            .toList();
        
        System.out.println("Even numbers: " + numbers);
        
        try {
            numbers.add(6);  // This will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify list: " + e.getClass().getSimpleName());
        }
        
        try {
            numbers.remove(0);  // This will also throw exception
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot remove from list: " + e.getClass().getSimpleName());
        }
        
        // 4. Complex pipeline
        System.out.println("\n4. Complex Pipeline");
        System.out.println("------------------");
        List<String> result = Stream.of("apple", "banana", "cherry", "date", "elderberry")
            .filter(s -> s.length() > 5)
            .map(String::toUpperCase)
            .sorted()
            .toList();
        
        System.out.println("Filtered, mapped, sorted: " + result);
        
        // 5. Empty stream
        System.out.println("\n5. Empty Stream");
        System.out.println("-------------");
        List<String> empty = Stream.<String>empty()
            .toList();
        
        System.out.println("Empty list: " + empty);
        System.out.println("Is empty: " + empty.isEmpty());
        
        // 6. Null handling
        System.out.println("\n6. Null Handling");
        System.out.println("--------------");
        List<String> withNulls = Stream.of("Hello", null, "World", null)
            .filter(s -> s != null)
            .toList();
        
        System.out.println("Filtered nulls: " + withNulls);
        
        // 7. When to use toList() vs collect()
        System.out.println("\n7. When to Use toList() vs collect()");
        System.out.println("-----------------------------------");
        
        // Use toList() when you need an unmodifiable list
        List<String> unmodifiable = Stream.of("A", "B", "C").toList();
        System.out.println("Unmodifiable list: " + unmodifiable);
        
        // Use collect() when you need a mutable list
        List<String> mutable = Stream.of("A", "B", "C")
            .collect(Collectors.toCollection(ArrayList::new));
        mutable.add("D");
        System.out.println("Mutable list: " + mutable);
        
        // 8. Performance note
        System.out.println("\n8. Benefits");
        System.out.println("---------");
        System.out.println("- Cleaner, more readable code");
        System.out.println("- Less boilerplate (no Collectors import needed)");
        System.out.println("- Returns unmodifiable list (safer)");
        System.out.println("- Standard method in Stream interface");
        System.out.println("- Type-safe (returns List<T> directly)");
    }
}

