package java15.records;

import java.util.Objects;

/**
 * Java 15 Records (Second Preview)
 * 
 * Demonstrates refinements to records feature.
 * Requires --enable-preview flag to compile and run.
 * 
 * Compile: javac --enable-preview --release 15 RecordsDemo.java
 * Run: java --enable-preview RecordsDemo
 */
public class RecordsDemo {
    public static void main(String[] args) {
        
        System.out.println("=== Records (Second Preview) ===\n");
        
        // 1. Basic record
        System.out.println("--- Basic Record ---");
        Point point = new Point(3, 4);
        System.out.println("Point: " + point);
        System.out.println("X: " + point.x());
        System.out.println("Y: " + point.y());
        
        // 2. Record with validation
        System.out.println("\n--- Record with Validation ---");
        try {
            Person person = new Person("John", 30);
            System.out.println("Person: " + person);
            System.out.println("Name: " + person.name());
            System.out.println("Age: " + person.age());
            System.out.println("Is adult: " + person.isAdult());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // 3. Record with static factory method
        System.out.println("\n--- Static Factory Method ---");
        Person person2 = Person.of("Alice", 25);
        System.out.println("Person from factory: " + person2);
        
        // 4. Record equality
        System.out.println("\n--- Record Equality ---");
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);
        
        System.out.println("p1.equals(p2): " + p1.equals(p2)); // true
        System.out.println("p1.equals(p3): " + p1.equals(p3)); // false
        System.out.println("p1.hashCode() == p2.hashCode(): " + (p1.hashCode() == p2.hashCode())); // true
        
        // 5. Record with additional methods
        System.out.println("\n--- Record with Additional Methods ---");
        Email email = new Email("user@example.com");
        System.out.println("Email: " + email.address());
        System.out.println("Domain: " + email.domain());
        System.out.println("Is valid: " + email.isValid());
        
        // 6. Comparison with traditional class
        System.out.println("\n--- Comparison: Traditional vs Record ---");
        TraditionalPoint trad = new TraditionalPoint(3, 4);
        Point record = new Point(3, 4);
        
        System.out.println("Traditional: " + trad);
        System.out.println("Record: " + record);
        System.out.println("Both have equals, hashCode, toString automatically!");
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. Less boilerplate code");
        System.out.println("2. Automatic equals(), hashCode(), toString()");
        System.out.println("3. Immutable by default");
        System.out.println("4. Clear intent (data carrier)");
        System.out.println("5. Can add methods and validation");
    }
    
    // Simple record
    public record Point(int x, int y) {}
    
    // Record with validation
    public record Person(String name, int age) {
        // Compact constructor - validates before assignment
        public Person {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be null or blank");
            }
        }
        
        // Additional methods
        public boolean isAdult() {
            return age >= 18;
        }
        
        // Static factory method
        public static Person of(String name, int age) {
            return new Person(name, age);
        }
    }
    
    // Record with computed methods
    public record Email(String address) {
        public Email {
            if (address == null || !address.contains("@")) {
                throw new IllegalArgumentException("Invalid email address");
            }
        }
        
        public String domain() {
            return address.substring(address.indexOf("@") + 1);
        }
        
        public boolean isValid() {
            return address.contains("@") && address.contains(".");
        }
    }
    
    // Traditional class for comparison
    static class TraditionalPoint {
        private final int x;
        private final int y;
        
        public TraditionalPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int getX() {
            return x;
        }
        
        public int getY() {
            return y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TraditionalPoint that = (TraditionalPoint) o;
            return x == that.x && y == that.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        @Override
        public String toString() {
            return "TraditionalPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }
}

