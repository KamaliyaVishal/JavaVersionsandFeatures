package java10.programs;

import java.util.*;

/**
 * Practical examples using Java 10 var keyword
 */
public class VarExamples {
    public static void main(String[] args) {
        
        // Example 1: Complex generic types
        System.out.println("=== Complex Generic Types ===");
        var map = new HashMap<String, List<Integer>>();
        map.put("numbers", Arrays.asList(1, 2, 3));
        map.put("evens", Arrays.asList(2, 4, 6));
        System.out.println("Map: " + map);
        
        // Example 2: Processing collections
        System.out.println("\n=== Processing Collections ===");
        var entries = Map.ofEntries(
            Map.entry("one", 1),
            Map.entry("two", 2),
            Map.entry("three", 3)
        );
        
        entries.forEach((var key, var value) -> 
            System.out.println(key + ": " + value));
        
        // Example 3: Loop variables
        System.out.println("\n=== Loop Variables ===");
        var names = Arrays.asList("Alice", "Bob", "Charlie");
        for (var name : names) {
            System.out.println("Name: " + name);
        }
        
        for (var i = 0; i < 5; i++) {
            System.out.println("i: " + i);
        }
    }
}

