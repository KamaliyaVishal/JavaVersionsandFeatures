package java25.instancemain;

/**
 * Java 25 Instance Main Methods
 * Demonstrates main methods without static keyword
 */
public class InstanceMainMethods {
    
    // Instance field
    String greeting = "Hello from instance main!";
    
    // Instance main method (no static keyword)
    void main() {
        System.out.println(greeting);
        displayMessage();
        processData();
    }
    
    // Instance method
    void displayMessage() {
        System.out.println("This is an instance method");
    }
    
    // Another instance method
    void processData() {
        System.out.println("Processing data in instance method");
    }
    
    // Can also have static main
    public static void main(String[] args) {
        System.out.println("Traditional static main still works");
        
        // Can create instance and call instance main
        var instance = new InstanceMainMethods();
        instance.main(); // Call instance main
    }
}

