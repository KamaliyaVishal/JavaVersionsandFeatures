package java17.foreignapi;

/**
 * Java 17 Foreign Function & Memory API (Incubator)
 * Demonstrates calling native code and accessing off-heap memory
 * 
 * NOTE: This is an incubator feature and requires:
 * - --add-modules jdk.incubator.foreign
 * - --enable-preview
 * 
 * Compile: javac --add-modules jdk.incubator.foreign --enable-preview --release 17 ForeignFunctionMemoryDemo.java
 * Run: java --add-modules jdk.incubator.foreign --enable-preview ForeignFunctionMemoryDemo
 */
public class ForeignFunctionMemoryDemo {
    public static void main(String[] args) {
        System.out.println("=== Java 17 Foreign Function & Memory API (Incubator) ===\n");
        
        System.out.println("This API provides:");
        System.out.println("1. Foreign Function Interface (FFI) - Call native code");
        System.out.println("2. Foreign Memory Access - Access off-heap memory");
        System.out.println("\nExample usage:");
        
        demonstrateForeignFunctionInterface();
        demonstrateForeignMemoryAccess();
    }
    
    private static void demonstrateForeignFunctionInterface() {
        System.out.println("\n--- Foreign Function Interface ---");
        System.out.println("""
            import jdk.incubator.foreign.*;
            import java.lang.invoke.MethodHandle;
            
            // Link with native libraries
            Linker linker = Linker.nativeLinker();
            SymbolLookup stdlib = linker.defaultLookup();
            
            // Find native function (e.g., strlen)
            MethodHandle strlen = linker.downcallHandle(
                stdlib.find("strlen").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
            );
            
            // Call native function
            try (MemorySegment str = Arena.ofAuto().allocateUtf8String("Hello")) {
                long len = (long) strlen.invoke(str);
                System.out.println("Length: " + len);
            }
            """);
        
        System.out.println("\nKey Features:");
        System.out.println("- Type-safe native function calls");
        System.out.println("- No JNI boilerplate");
        System.out.println("- Automatic memory management");
        System.out.println("- Platform-independent API");
    }
    
    private static void demonstrateForeignMemoryAccess() {
        System.out.println("\n--- Foreign Memory Access ---");
        System.out.println("""
            import jdk.incubator.foreign.*;
            
            // Allocate off-heap memory
            try (Arena arena = Arena.ofConfined()) {
                MemorySegment segment = arena.allocate(100);
                
                // Write to memory
                segment.set(ValueLayout.JAVA_INT, 0, 42);
                
                // Read from memory
                int value = segment.get(ValueLayout.JAVA_INT, 0);
                System.out.println("Value: " + value);
            }
            // Memory automatically freed when arena is closed
            """);
        
        System.out.println("\nKey Features:");
        System.out.println("- Safe memory access (bounds checking)");
        System.out.println("- Automatic resource management");
        System.out.println("- Type-safe operations");
        System.out.println("- Useful for native library interop");
        
        System.out.println("\nUse Cases:");
        System.out.println("- Interfacing with native libraries");
        System.out.println("- High-performance memory operations");
        System.out.println("- System-level programming");
        System.out.println("- Zero-copy operations");
        
        System.out.println("\nBenefits:");
        System.out.println("- Safer than JNI");
        System.out.println("- Better performance");
        System.out.println("- Modern API design");
        System.out.println("- Automatic memory management");
    }
}

