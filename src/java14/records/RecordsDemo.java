package java14.records;

import java.util.Objects;

/**
 * Java 14 Records (Preview)
 * Demonstrates immutable data carrier classes
 */
public class RecordsDemo {
    public static void main(String[] args) {
        
        // 1. Basic record
        System.out.println("=== Basic Record ===");
        Person person = new Person("John", 30);
        System.out.println("Name: " + person.name());
        System.out.println("Age: " + person.age());
        System.out.println("Person: " + person); // Automatic toString
        
        // 2. Record with validation
        System.out.println("\n=== Record with Validation ===");
        try {
            Email email = new Email("valid@email.com");
            System.out.println("Email: " + email.address());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // 3. Record with methods
        System.out.println("\n=== Record with Methods ===");
        Person person2 = new Person("Alice", 25);
        System.out.println("Is adult: " + person2.isAdult());
        System.out.println("Full info: " + person2.getFullInfo());
        
        // 4. Record with static methods
        System.out.println("\n=== Static Methods ===");
        Person person3 = Person.of("Bob", 35);
        System.out.println("Person from factory: " + person3);
        
        // 5. Comparison with traditional class
        System.out.println("\n=== Comparison ===");
        // Records are much simpler than traditional classes
        TraditionalPerson trad = new TraditionalPerson("Charlie", 28);
        Person record = new Person("Charlie", 28);
        
        System.out.println("Traditional: " + trad);
        System.out.println("Record: " + record);
        
        // Both have equals and hashCode
        System.out.println("Records equal: " + 
            Objects.equals(new Person("John", 30), new Person("John", 30))); // true
    }
    
    // Record definition
    public record Person(String name, int age) {
        // Compact constructor with validation
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
        
        public String getFullInfo() {
            return name + " is " + age + " years old";
        }
        
        // Static methods
        public static Person of(String name, int age) {
            return new Person(name, age);
        }
    }
    
    // Record with validation
    public record Email(String address) {
        public Email {
            if (address == null || !address.contains("@")) {
                throw new IllegalArgumentException("Invalid email address");
            }
        }
    }
    
    // Traditional class for comparison
    static class TraditionalPerson {
        private final String name;
        private final int age;
        
        public TraditionalPerson(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
        
        public int getAge() {
            return age;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TraditionalPerson that = (TraditionalPerson) o;
            return age == that.age && Objects.equals(name, that.name);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
        
        @Override
        public String toString() {
            return "TraditionalPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
        }
    }
}

