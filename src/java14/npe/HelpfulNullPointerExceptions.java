package java14.npe;

/**
 * Java 14 Helpful NullPointerExceptions
 * 
 * Demonstrates improved NullPointerException messages that show exactly
 * what was null and where in the chain of method calls/field accesses.
 * 
 * Note: Enhanced NPE messages are enabled by default in Java 14+.
 * For Java 11-13, use: -XX:+ShowCodeDetailsInExceptionMessages
 */
public class HelpfulNullPointerExceptions {
    
    static class Person {
        Address address;
        String name;
        
        Person(String name) {
            this.name = name;
        }
    }
    
    static class Address {
        String city;
        String street;
        
        Address(String city, String street) {
            this.city = city;
            this.street = street;
        }
    }
    
    public static void main(String[] args) {
        
        System.out.println("=== Helpful NullPointerExceptions ===\n");
        System.out.println("Java 14 provides detailed NPE messages showing exactly what was null.\n");
        
        // 1. Simple null reference
        System.out.println("--- Example 1: Simple Null Reference ---");
        try {
            int[] arr = null;
            int length = arr.length; // NPE here
        } catch (NullPointerException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Message shows: Cannot read the array length because \"arr\" is null");
        }
        
        // 2. Null field access
        System.out.println("\n--- Example 2: Null Field Access ---");
        try {
            Person person = new Person("John");
            // person.address is null
            String city = person.address.city; // NPE here
        } catch (NullPointerException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Message shows: Cannot read field \"city\" because \"person.address\" is null");
        }
        
        // 3. Null method call
        System.out.println("\n--- Example 3: Null Method Call ---");
        try {
            String str = null;
            int length = str.length(); // NPE here
        } catch (NullPointerException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Message shows: Cannot invoke \"String.length()\" because \"str\" is null");
        }
        
        // 4. Chained method calls
        System.out.println("\n--- Example 4: Chained Method Calls ---");
        try {
            Person person = null;
            String city = person.address.city.toUpperCase(); // NPE here
        } catch (NullPointerException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Message shows: Cannot read field \"address\" because \"person\" is null");
        }
        
        // 5. Array element access
        System.out.println("\n--- Example 5: Array Element Access ---");
        try {
            String[] array = new String[5];
            // array[0] is null
            int length = array[0].length(); // NPE here
        } catch (NullPointerException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Message shows: Cannot invoke \"String.length()\" because \"array[0]\" is null");
        }
        
        // 6. Complex chain
        System.out.println("\n--- Example 6: Complex Chain ---");
        try {
            Person person = new Person("Alice");
            // person.address is null
            String street = person.address.street.toUpperCase(); // NPE here
        } catch (NullPointerException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Message shows: Cannot read field \"street\" because \"person.address\" is null");
        }
        
        // 7. Comparison: Before vs After
        System.out.println("\n--- Comparison: Before vs After Java 14 ---");
        System.out.println("Before Java 14:");
        System.out.println("  NullPointerException");
        System.out.println("  (No details about what was null)");
        System.out.println();
        System.out.println("Java 14+:");
        System.out.println("  NullPointerException: Cannot read field \"city\" because \"person.address\" is null");
        System.out.println("  (Shows exactly what was null and where)");
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. Clear indication of what was null");
        System.out.println("2. Shows the full chain of method calls/field accesses");
        System.out.println("3. Easier debugging");
        System.out.println("4. No code changes needed");
        System.out.println("5. Enabled by default in Java 14+");
        
        System.out.println("\n=== Enabling in Older Versions ===");
        System.out.println("For Java 11-13, use JVM option:");
        System.out.println("  -XX:+ShowCodeDetailsInExceptionMessages");
    }
}

