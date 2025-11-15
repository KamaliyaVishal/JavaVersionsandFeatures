package java10.var;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java 10 Local-Variable Type Inference (var)
 * Demonstrates the var keyword for type inference
 */
public class TypeInferenceDemo {
    public static void main(String[] args) {
        
        // 1. Basic var usage
        System.out.println("=== Basic var Usage ===");
        var message = "Hello, World!";
        System.out.println("message type: " + message.getClass().getSimpleName());
        System.out.println("message: " + message);
        
        var number = Integer.valueOf(42);  // Use wrapper type to call getClass()
        System.out.println("number type: " + number.getClass().getSimpleName());
        
        var decimal = Double.valueOf(3.14);  // Use wrapper type to call getClass()
        System.out.println("decimal type: " + decimal.getClass().getSimpleName());
        
        // 2. var with Collections
        System.out.println("\n=== var with Collections ===");
        var names = new ArrayList<String>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        System.out.println("names: " + names);
        
        var map = new HashMap<String, Integer>();
        map.put("One", 1);
        map.put("Two", 2);
        System.out.println("map: " + map);
        
        // 3. var in loops
        System.out.println("\n=== var in Loops ===");
        for (var name : names) {
            System.out.println("Name: " + name);
        }
        
        for (var i = 0; i < 5; i++) {
            System.out.println("i: " + i);
        }
        
        // 4. var with complex types
        System.out.println("\n=== var with Complex Types ===");
        var entries = Map.ofEntries(
            Map.entry("key1", "value1"),
            Map.entry("key2", "value2")
        );
        System.out.println("entries: " + entries);
        
        // 5. var with method return types
        System.out.println("\n=== var with Method Return Types ===");
        var result = calculateComplexResult();
        System.out.println("Result: " + result);
        
        // 6. When NOT to use var
        System.out.println("\n=== When NOT to use var ===");
        // ❌ Bad: Type not obvious
        // var data = process();  // What type is data? (method doesn't exist - example only)
        
        // ✅ Good: Type is obvious
        var person = new Person("John", 30);
        System.out.println("Person: " + person);
        
        // ✅ Good: Complex generic types
        var complexMap = new HashMap<String, List<Integer>>();
        System.out.println("Complex map type: " + complexMap.getClass().getSimpleName());
    }
    
    private static String calculateComplexResult() {
        return "Complex calculation result";
    }
    
    static class Person {
        String name;
        int age;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}

