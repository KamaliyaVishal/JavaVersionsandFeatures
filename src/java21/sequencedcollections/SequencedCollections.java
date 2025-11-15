package java21.sequencedcollections;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;

/**
 * Java 21 Sequenced Collections
 * Demonstrates collections with defined encounter order
 */
public class SequencedCollections {
    public static void main(String[] args) {
        
        // 1. SequencedCollection
        System.out.println("=== SequencedCollection ===");
        SequencedCollection<String> collection = new LinkedHashSet<>();
        collection.add("First");
        collection.add("Second");
        collection.add("Third");
        
        System.out.println("Collection: " + collection);
        System.out.println("First: " + collection.getFirst());
        System.out.println("Last: " + collection.getLast());
        
        collection.addFirst("Zero");
        collection.addLast("Fourth");
        System.out.println("After addFirst/Last: " + collection);
        
        String removed = collection.removeFirst();
        System.out.println("Removed first: " + removed);
        System.out.println("Collection: " + collection);
        
        // Reversed view
        System.out.println("Reversed: " + collection.reversed());
        
        // 2. SequencedSet
        System.out.println("\n=== SequencedSet ===");
        SequencedSet<String> set = new LinkedHashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        
        System.out.println("Set: " + set);
        System.out.println("First: " + set.getFirst());
        System.out.println("Last: " + set.getLast());
        
        // 3. SequencedMap
        System.out.println("\n=== SequencedMap ===");
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        
        System.out.println("Map: " + map);
        System.out.println("First entry: " + map.firstEntry());
        System.out.println("Last entry: " + map.lastEntry());
        
        map.putFirst("Zero", 0);
        map.putLast("Four", 4);
        System.out.println("After putFirst/Last: " + map);
        
        System.out.println("Reversed: " + map.reversed());
    }
}

