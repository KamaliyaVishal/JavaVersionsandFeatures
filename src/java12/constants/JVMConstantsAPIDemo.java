package java12.constants;

import java.lang.constant.*;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Java 12 JVM Constants API Demo
 * 
 * Demonstrates the Constants API for modeling class-file and runtime artifacts.
 * This API is primarily useful for tools and frameworks that manipulate class files.
 * 
 * NOTE: This is a low-level API for framework developers and tools.
 * Requires Java 12+.
 */
public class JVMConstantsAPIDemo {
    
    public static void main(String[] args) {
        System.out.println("=== JVM Constants API Demo ===\n");
        
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            
            // 1. ClassDesc - Class constant descriptors
            demonstrateClassDesc(lookup);
            
            // 2. MethodTypeDesc - Method type descriptors
            demonstrateMethodTypeDesc();
            
            // 3. Using ConstantDescs utility class
            demonstrateConstantDescs(lookup);
            
            // 4. Array descriptors
            demonstrateArrayDescriptors();
            
            // 5. Primitive type descriptors
            demonstratePrimitiveDescriptors();
            
            // 6. Practical example: Building method signatures
            demonstrateMethodSignatures();
            
            // 7. StringDesc (conceptual)
            demonstrateStringDesc(lookup);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstrates String as ConstantDesc for string constants
     * Note: String class implements ConstantDesc interface directly
     */
    private static void demonstrateStringDesc(MethodHandles.Lookup lookup) 
            throws ReflectiveOperationException {
        System.out.println("--- 7. String as ConstantDesc (String Constants) ---");
        System.out.println("NOTE: In Java 12, String class implements ConstantDesc interface directly.");
        System.out.println("There is no separate StringDesc class - use String itself.\n");
        
        // String implements ConstantDesc, so we can use it directly
        String constantString = "Hello, Java 12!";
        ConstantDesc stringDesc = constantString; // String is a ConstantDesc
        
        System.out.println("String constant: " + constantString);
        System.out.println("Is ConstantDesc: " + (stringDesc instanceof ConstantDesc));
        System.out.println("ConstantDesc type: " + stringDesc.getClass().getName());
        
        // Resolve to actual String (though it's already a String)
        String resolved = (String) stringDesc.resolveConstantDesc(lookup);
        System.out.println("Resolved String: " + resolved);
        System.out.println("String length: " + resolved.length());
        
        System.out.println("\n--- Usage Example ---");
        System.out.println("// String implements ConstantDesc directly");
        System.out.println("String constantString = \"Hello, Java 12!\";");
        System.out.println("ConstantDesc stringDesc = constantString;");
        System.out.println("String resolved = (String) stringDesc.resolveConstantDesc(lookup);");
        
        System.out.println("\nUse Cases:");
        System.out.println("  - Model string constants in class files");
        System.out.println("  - Type-safe string constant descriptors");
        System.out.println("  - Useful for code generation tools");
        System.out.println("  - Can be used wherever ConstantDesc is expected");
        
        System.out.println();
    }
    
    /**
     * Demonstrates ClassDesc for class references
     */
    private static void demonstrateClassDesc(MethodHandles.Lookup lookup) 
            throws ReflectiveOperationException {
        System.out.println("--- 1. ClassDesc (Class References) ---");
        
        // Create ClassDesc for String class
        ClassDesc stringClassDesc = ClassDesc.of("java.lang.String");
        System.out.println("ClassDesc for String: " + stringClassDesc);
        System.out.println("Descriptor: " + stringClassDesc.descriptorString());
        
        // Resolve to actual Class
        Class<?> stringClass = (Class<?>) stringClassDesc.resolveConstantDesc(lookup);
        System.out.println("Resolved Class: " + stringClass.getName());
        System.out.println("Is String class: " + (stringClass == String.class));
        
        // Create ClassDesc for ArrayList
        ClassDesc arrayListDesc = ClassDesc.of("java.util.ArrayList");
        System.out.println("\nClassDesc for ArrayList: " + arrayListDesc);
        Class<?> arrayListClass = (Class<?>) arrayListDesc.resolveConstantDesc(lookup);
        System.out.println("Resolved Class: " + arrayListClass.getName());
        
        System.out.println();
    }
    
    /**
     * Demonstrates MethodTypeDesc for method signatures
     */
    private static void demonstrateMethodTypeDesc() {
        System.out.println("--- 2. MethodTypeDesc (Method Signatures) ---");
        
        // Method: String substring(int, int)
        ClassDesc stringClass = ClassDesc.of("java.lang.String");
        ClassDesc intClass = ClassDesc.ofDescriptor("I");
        
        MethodTypeDesc substringType = MethodTypeDesc.of(
            stringClass,  // Return type: String
            intClass,     // Parameter 1: int
            intClass      // Parameter 2: int
        );
        System.out.println("Method: String substring(int, int)");
        System.out.println("Descriptor: " + substringType.descriptorString());
        System.out.println("Return type: " + substringType.returnType());
        System.out.println("Parameter count: " + substringType.parameterCount());
        
        // Method: void println(String)
        ClassDesc voidClass = ClassDesc.ofDescriptor("V");
        MethodTypeDesc printlnType = MethodTypeDesc.of(
            voidClass,    // Return type: void
            stringClass  // Parameter: String
        );
        System.out.println("\nMethod: void println(String)");
        System.out.println("Descriptor: " + printlnType.descriptorString());
        
        // Method: int compareTo(String)
        MethodTypeDesc compareToType = MethodTypeDesc.of(
            intClass,    // Return type: int
            stringClass  // Parameter: String
        );
        System.out.println("\nMethod: int compareTo(String)");
        System.out.println("Descriptor: " + compareToType.descriptorString());
        
        System.out.println();
    }
    
    /**
     * Demonstrates ConstantDescs utility class
     */
    private static void demonstrateConstantDescs(MethodHandles.Lookup lookup) 
            throws ReflectiveOperationException {
        System.out.println("--- 3. ConstantDescs Utility Class ---");
        
        // Predefined ClassDesc constants
        System.out.println("Predefined ClassDesc constants:");
        System.out.println("  String: " + ConstantDescs.CD_String);
        System.out.println("  Object: " + ConstantDescs.CD_Object);
        System.out.println("  Integer: " + ConstantDescs.CD_int);
        System.out.println("  Long: " + ConstantDescs.CD_long);
        
        // Resolve a predefined constant
        Class<?> resolvedClass = (Class<?>) ConstantDescs.CD_String.resolveConstantDesc(lookup);
        System.out.println("\nResolved String class: " + resolvedClass.getName());
        
        // Create MethodTypeDesc from MethodType
        MethodType methodType = MethodType.methodType(void.class, String.class);
        // Convert MethodType to MethodTypeDesc manually
        ClassDesc voidClass = ClassDesc.ofDescriptor("V");
        ClassDesc stringClass = ClassDesc.of("java.lang.String");
        MethodTypeDesc methodTypeDesc = MethodTypeDesc.of(voidClass, stringClass);
        System.out.println("\nMethodTypeDesc from MethodType:");
        System.out.println("  Original MethodType: " + methodType);
        System.out.println("  MethodTypeDesc: " + methodTypeDesc.descriptorString());
        
        System.out.println();
    }
    
    /**
     * Demonstrates array type descriptors
     */
    private static void demonstrateArrayDescriptors() {
        System.out.println("--- 4. Array Type Descriptors ---");
        
        // String array - using arrayType() method
        ClassDesc stringClass = ClassDesc.of("java.lang.String");
        ClassDesc stringArrayDesc = stringClass.arrayType();
        System.out.println("String[] descriptor: " + stringArrayDesc.descriptorString());
        System.out.println("Component type: " + stringArrayDesc.componentType());
        
        // int array - using ofDescriptor with correct format
        ClassDesc intArrayDesc = ClassDesc.ofDescriptor("[I");
        System.out.println("\nint[] descriptor: " + intArrayDesc.descriptorString());
        System.out.println("Component type: " + intArrayDesc.componentType());
        
        // Multi-dimensional array - using arrayType() multiple times
        ClassDesc multiArrayDesc = stringClass.arrayType().arrayType();
        System.out.println("\nString[][] descriptor: " + multiArrayDesc.descriptorString());
        System.out.println("Component type: " + multiArrayDesc.componentType());
        System.out.println("Component of component: " + multiArrayDesc.componentType().componentType());
        
        // Alternative: Using ofDescriptor with forward slashes (correct format)
        System.out.println("\n--- Alternative: Using ofDescriptor with correct format ---");
        ClassDesc stringArrayDesc2 = ClassDesc.ofDescriptor("[Ljava/lang/String;");
        System.out.println("String[] (using ofDescriptor): " + stringArrayDesc2.descriptorString());
        
        System.out.println();
    }
    
    /**
     * Demonstrates primitive type descriptors
     */
    private static void demonstratePrimitiveDescriptors() {
        System.out.println("--- 5. Primitive Type Descriptors ---");
        
        // Primitive descriptors
        ClassDesc[] primitives = {
            ClassDesc.ofDescriptor("B"),  // byte
            ClassDesc.ofDescriptor("C"),  // char
            ClassDesc.ofDescriptor("D"),  // double
            ClassDesc.ofDescriptor("F"),  // float
            ClassDesc.ofDescriptor("I"),  // int
            ClassDesc.ofDescriptor("J"),  // long
            ClassDesc.ofDescriptor("S"),  // short
            ClassDesc.ofDescriptor("Z")   // boolean
        };
        
        String[] names = {"byte", "char", "double", "float", "int", "long", "short", "boolean"};
        
        for (int i = 0; i < primitives.length; i++) {
            System.out.println(names[i] + ": " + primitives[i].descriptorString());
        }
        
        System.out.println();
    }
    
    /**
     * Demonstrates building method signatures
     */
    private static void demonstrateMethodSignatures() {
        System.out.println("--- 6. Practical Example: Building Method Signatures ---");
        
        ClassDesc stringClass = ClassDesc.of("java.lang.String");
        ClassDesc voidClass = ClassDesc.ofDescriptor("V");
        
        // Example 1: String concat(String)
        MethodTypeDesc concatType = MethodTypeDesc.of(stringClass, stringClass);
        System.out.println("String concat(String)");
        System.out.println("  Descriptor: " + concatType.descriptorString());
        
        // Example 2: boolean equals(Object)
        ClassDesc objectClass = ClassDesc.of("java.lang.Object");
        ClassDesc booleanClass = ClassDesc.ofDescriptor("Z");
        MethodTypeDesc equalsType = MethodTypeDesc.of(booleanClass, objectClass);
        System.out.println("\nboolean equals(Object)");
        System.out.println("  Descriptor: " + equalsType.descriptorString());
        
        // Example 3: void main(String[])
        // Use arrayType() method for String array
        ClassDesc stringArrayClass = stringClass.arrayType();
        MethodTypeDesc mainType = MethodTypeDesc.of(voidClass, stringArrayClass);
        System.out.println("\nvoid main(String[])");
        System.out.println("  Descriptor: " + mainType.descriptorString());
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. Type-safe method signature construction");
        System.out.println("2. Compile-time validation");
        System.out.println("3. Useful for code generation tools");
        System.out.println("4. Framework-friendly API");
        System.out.println("5. Better than string-based descriptors");
    }
}

