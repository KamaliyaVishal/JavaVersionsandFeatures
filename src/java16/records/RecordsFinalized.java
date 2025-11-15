package java16.records;

/**
 * Java 16 Records (Finalized)
 * Demonstrates finalized records feature
 */
public class RecordsFinalized {
    public static void main(String[] args) {
        
        // Records are now standard (no longer preview)
        Person person = new Person("John", 30);
        System.out.println("Name: " + person.name());
        System.out.println("Age: " + person.age());
        System.out.println("Person: " + person);
        
        // Records are immutable
        // person.name() = "Jane"; // Compile error
        
        // Records with validation
        try {
            new Email("invalid"); // Will throw exception
        } catch (IllegalArgumentException e) {
            System.out.println("Email validation error: " + e.getMessage());
        }
        
        Email validEmail = new Email("valid@email.com");
        System.out.println("Valid email: " + validEmail.address());
    }
    
    public record Person(String name, int age) {
        public Person {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        }
        
        public boolean isAdult() {
            return age >= 18;
        }
    }
    
    public record Email(String address) {
        public Email {
            if (address == null || !address.contains("@")) {
                throw new IllegalArgumentException("Invalid email address");
            }
        }
    }
}

