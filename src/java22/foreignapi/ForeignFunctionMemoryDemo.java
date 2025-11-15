package java22.foreignapi;

/**
 * Java 22 Foreign Function & Memory API (Finalized)
 * Demonstrates calling native code and managing native memory
 * 
 * NOTE: This API was finalized in Java 22 (JEP 454)
 * No preview flag needed!
 */
public class ForeignFunctionMemoryDemo {
    public static void main(String[] args) {
        System.out.println("=== Java 22 Foreign Function & Memory API (Finalized) ===\n");
        
        System.out.println("Overview:");
        System.out.println("--------");
        System.out.println("The Foreign Function & Memory API provides:");
        System.out.println("1. Foreign Function Interface (FFI) - Call native code");
        System.out.println("2. Foreign Memory Access - Manage off-heap memory");
        System.out.println("3. Type-safe native interop");
        System.out.println("4. Replacement for JNI");
        
        System.out.println("\nExample 1: Foreign Memory Allocation");
        System.out.println("-----------------------------------");
        System.out.println("""
            import java.lang.foreign.*;
            
            // Confined arena (single-threaded)
            try (Arena arena = Arena.ofConfined()) {
                MemorySegment segment = arena.allocate(100);
                // Use segment for native memory operations
            } // Automatically freed
            
            // Shared arena (multi-threaded)
            try (Arena arena = Arena.ofShared()) {
                MemorySegment segment = arena.allocate(1024);
                // Use segment from multiple threads
            }
            
            // Automatic arena (GC-managed)
            Arena arena = Arena.ofAuto();
            MemorySegment segment = arena.allocate(1024);
            // Freed when GC determines it's safe
            """);
        
        System.out.println("\nExample 2: Calling Native Functions");
        System.out.println("------------------------------------");
        System.out.println("""
            import java.lang.foreign.*;
            import java.lang.foreign.FunctionDescriptor;
            import java.lang.foreign.Linker;
            
            Linker linker = Linker.nativeLinker();
            SymbolLookup stdlib = linker.defaultLookup();
            
            // Find native function
            MemorySegment strlen = stdlib.find("strlen").orElseThrow();
            
            // Create function descriptor
            FunctionDescriptor descriptor = FunctionDescriptor.of(
                ValueLayout.JAVA_LONG,
                ValueLayout.ADDRESS
            );
            
            // Create method handle
            MethodHandle strlenHandle = linker.downcallHandle(strlen, descriptor);
            
            // Call native function
            try (Arena arena = Arena.ofConfined()) {
                MemorySegment cString = arena.allocateUtf8String("Hello");
                long length = (long) strlenHandle.invoke(cString);
                System.out.println("Length: " + length);
            }
            """);
        
        System.out.println("\nExample 3: Memory Segments");
        System.out.println("-------------------------");
        System.out.println("""
            // Allocate array of doubles
            try (Arena arena = Arena.ofConfined()) {
                MemorySegment segment = arena.allocate(5 * Double.BYTES);
                for (int i = 0; i < 5; i++) {
                    segment.setAtIndex(ValueLayout.JAVA_DOUBLE, i, i * 1.1);
                }
                
                // Read values
                for (int i = 0; i < 5; i++) {
                    double value = segment.getAtIndex(ValueLayout.JAVA_DOUBLE, i);
                    System.out.println("Value[" + i + "] = " + value);
                }
            }
            """);
        
        System.out.println("\nKey Features:");
        System.out.println("- Type-safe native interop");
        System.out.println("- Memory safety with arenas");
        System.out.println("- No JNI boilerplate");
        System.out.println("- Better performance than JNI");
        System.out.println("- Structured memory management");
        System.out.println("- Finalized in Java 22 (no preview flag needed)");
        
        System.out.println("\nUse Cases:");
        System.out.println("- Calling C/C++ libraries");
        System.out.println("- System-level programming");
        System.out.println("- High-performance native code integration");
        System.out.println("- Memory-mapped files");
        System.out.println("- Direct memory access");
        
        System.out.println("\nBenefits:");
        System.out.println("- Safer than JNI");
        System.out.println("- More efficient");
        System.out.println("- Better error handling");
        System.out.println("- Automatic memory management");
        System.out.println("- Type safety");
        System.out.println("- Finalized API (stable)");
        
        System.out.println("\nNote: This API was finalized in Java 22 (JEP 454).");
        System.out.println("No --enable-preview flag needed!");
    }
}

