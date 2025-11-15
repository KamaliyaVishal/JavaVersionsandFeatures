package java21.stringtemplates;

/**
 * Java 21 String Templates (Preview) / Java 22 String Templates (Finalized)
 * 
 * NOTE: String Templates were withdrawn in Java 23+ due to design concerns.
 * This code demonstrates the concept using traditional string formatting.
 * 
 * For Java 21-22, String Templates were available with FormatProcessor.
 * For Java 23+, use traditional string concatenation or String.format().
 */
public class StringTemplates {
    public static void main(String[] args) {
        
        String name = "John";
        int age = 30;
        
        // 1. Basic string interpolation (traditional approach)
        System.out.println("=== String Templates (Concept) ===");
        System.out.println("NOTE: String Templates were withdrawn in Java 23+");
        System.out.println("Using traditional string concatenation instead:\n");
        
        String message = "Hello, " + name + "! You are " + age + " years old.";
        System.out.println(message);
        
        // 2. Formatted template (using String.format)
        System.out.println("\n=== Formatted Templates ===");
        String formatted = String.format("Age: %03d", age);
        System.out.println(formatted); // Age: 030
        
        // 3. Complex expressions
        System.out.println("\n=== Complex Expressions ===");
        int x = 10;
        int y = 20;
        String calculation = "Sum: " + x + " + " + y + " = " + (x + y);
        System.out.println(calculation);
        
        // 4. Multi-line template
        System.out.println("\n=== Multi-line Template ===");
        String info = """
            Name: %s
            Age: %d
            Status: Active
            """.formatted(name, age);
        System.out.println(info);
        
        // 5. Comparison with old approach
        System.out.println("\n=== Comparison ===");
        // Traditional way
        String oldWay = "Hello, " + name + "! You are " + age + " years old.";
        
        // Using String.format (more readable for complex formatting)
        String formattedWay = String.format("Hello, %s! You are %d years old.", name, age);
        
        System.out.println("Concatenation: " + oldWay);
        System.out.println("String.format: " + formattedWay);
        System.out.println("\nNote: String Templates (STR.\"text{var}\") were available in Java 21-22");
        System.out.println("but were withdrawn in Java 23+ due to design concerns.");
    }
}
