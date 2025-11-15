package java10.collections;

import java.util.*;

/**
 * Java 10 Collection Copy Methods
 * Demonstrates List.copyOf(), Set.copyOf(), and Map.copyOf()
 */
public class CopyOfMethods {
    public static void main(String[] args) {
        
        // 1. List.copyOf()
        System.out.println("=== List.copyOf() Examples ===");
        List<String> originalList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> copyList = List.copyOf(originalList);
        
        System.out.println("Original list: " + originalList);
        System.out.println("Copy list: " + copyList);
        
        // Modify original (copy is unaffected)
        originalList.add("D");
        System.out.println("After adding to original: " + originalList);
        System.out.println("Copy remains: " + copyList);
        
        // If original is already unmodifiable, returns same instance
        List<String> immutable = List.of("X", "Y", "Z");
        List<String> copy2 = List.copyOf(immutable);
        System.out.println("Same instance: " + (immutable == copy2)); // true
        
        // 2. Set.copyOf()
        System.out.println("\n=== Set.copyOf() Examples ===");
        Set<Integer> originalSet = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> copySet = Set.copyOf(originalSet);
        
        System.out.println("Original set: " + originalSet);
        System.out.println("Copy set: " + copySet);
        
        // 3. Map.copyOf()
        System.out.println("\n=== Map.copyOf() Examples ===");
        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("One", 1);
        originalMap.put("Two", 2);
        originalMap.put("Three", 3);
        
        Map<String, Integer> copyMap = Map.copyOf(originalMap);
        System.out.println("Original map: " + originalMap);
        System.out.println("Copy map: " + copyMap);
        
        // Try to modify copy (will throw exception)
        System.out.println("\n=== Immutability Test ===");
        try {
            // copyList.add("E"); // UnsupportedOperationException
            // copyMap.put("Four", 4); // UnsupportedOperationException
            System.out.println("Copies are immutable - modifications not allowed");
        } catch (UnsupportedOperationException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        // Practical example
        System.out.println("\n=== Practical Example ===");
        List<String> userNames = new ArrayList<>(Arrays.asList("alice", "bob", "charlie"));
        List<String> safeCopy = List.copyOf(userNames);
        
        // Safe to pass copy to untrusted code
        processUserNames(safeCopy);
        
        // Original unchanged
        System.out.println("Original after processing: " + userNames);
    }
    
    private static void processUserNames(List<String> names) {
        System.out.println("Processing: " + names);
        // This method cannot modify the list
    }
}

