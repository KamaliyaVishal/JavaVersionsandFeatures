package java11.nestbased;

/**
 * Java 11 Nest-Based Access Control
 * 
 * Demonstrates the new nest-based access control mechanism that allows
 * classes in the same nest to access each other's private members without
 * generating synthetic bridge methods.
 * 
 * Benefits:
 * - Better performance (no synthetic methods)
 * - Cleaner bytecode
 * - More efficient reflection
 * - Better encapsulation
 */
public class NestBasedAccessControl {
    
    // Outer class private members
    private static int outerStaticField = 100;
    private int outerInstanceField = 200;
    private String outerPrivateMethod() {
        return "Outer private method called";
    }
    
    // Inner class - part of the same nest
    public class Inner {
        public void accessOuterMembers() {
            // Can directly access outer class private members
            // No synthetic bridge methods needed in Java 11+
            System.out.println("Accessing outer static field: " + outerStaticField);
            System.out.println("Accessing outer instance field: " + outerInstanceField);
            System.out.println("Calling outer private method: " + outerPrivateMethod());
        }
        
        private int innerPrivateField = 300;
        private String innerPrivateMethod() {
            return "Inner private method called from " + innerPrivateField;
        }
        
        public void demonstrateInnerPrivate() {
            System.out.println("Inner private field: " + innerPrivateField);
            System.out.println("Inner private method: " + innerPrivateMethod());
        }
    }
    
    // Static nested class - also part of the same nest
    public static class StaticNested {
        public void accessOuterMembers() {
            // Can access outer class private static members
            System.out.println("Accessing outer static field: " + outerStaticField);
            // Cannot access instance members from static context
        }
        
        private static int nestedStaticField = 400;
        private int nestedInstanceField = 500;
        
        public void demonstrateNestedFields() {
            System.out.println("Nested static field: " + nestedStaticField);
            System.out.println("Nested instance field: " + nestedInstanceField);
        }
    }
    
    // Another inner class - same nest
    private class PrivateInner {
        public void accessOuterAndOtherInner() {
            // Access outer members
            System.out.println("Outer field: " + outerInstanceField);
            
            // Access other inner class members (if accessible)
            Inner inner = new Inner();
            inner.demonstrateInnerPrivate();
            // Note: Cannot directly access inner.privateField due to access modifiers
            // But both are in the same nest, so reflection can access
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Nest-Based Access Control Demo ===");
        
        // Create outer instance
        NestBasedAccessControl outer = new NestBasedAccessControl();
        
        // Create inner class instance
        Inner inner = outer.new Inner();
        System.out.println("\n--- Inner Class Accessing Outer Members ---");
        inner.accessOuterMembers();
        
        // Create static nested class
        StaticNested nested = new StaticNested();
        System.out.println("\n--- Static Nested Class Accessing Outer Members ---");
        nested.accessOuterMembers();
        nested.demonstrateNestedFields();
        
        // Create private inner class
        PrivateInner privateInner = outer.new PrivateInner();
        System.out.println("\n--- Private Inner Class Accessing Outer and Other Inner ---");
        privateInner.accessOuterAndOtherInner();
        
        // Demonstrate reflection access (benefit of nest-based access)
        System.out.println("\n=== Reflection Benefits ===");
        try {
            // In Java 11+, reflection can access private members within same nest
            // without needing setAccessible(true) in some cases
            java.lang.reflect.Field field = NestBasedAccessControl.class
                .getDeclaredField("outerStaticField");
            field.setAccessible(true);
            int value = field.getInt(null);
            System.out.println("Reflected outer static field: " + value);
            
            // Access inner class private field
            java.lang.reflect.Field innerField = Inner.class
                .getDeclaredField("innerPrivateField");
            innerField.setAccessible(true);
            int innerValue = innerField.getInt(inner);
            System.out.println("Reflected inner private field: " + innerValue);
            
        } catch (Exception e) {
            System.err.println("Reflection error: " + e.getMessage());
        }
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. No synthetic bridge methods needed");
        System.out.println("2. Cleaner bytecode");
        System.out.println("3. Better performance");
        System.out.println("4. More efficient reflection");
        System.out.println("5. Better encapsulation support");
        
        System.out.println("\n=== Before Java 11 ===");
        System.out.println("- Required synthetic bridge methods");
        System.out.println("- More bytecode overhead");
        System.out.println("- Less efficient reflection");
        
        System.out.println("\n=== Java 11+ ===");
        System.out.println("- Direct access within nest");
        System.out.println("- No synthetic methods");
        System.out.println("- More efficient bytecode");
        System.out.println("- Better reflection support");
    }
}

