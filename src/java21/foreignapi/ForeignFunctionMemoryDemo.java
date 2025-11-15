package java21.foreignapi;

/**
 * Java 21 Foreign Function & Memory API (Third Preview)
 * Demonstrates calling native code and managing native memory
 * 
 * NOTE: This is a preview feature requiring --enable-preview flag
 * Also requires --add-modules jdk.incubator.foreign
 * 
 * Compile: javac --enable-preview --add-modules jdk.incubator.foreign --release 21 ForeignFunctionMemoryDemo.java
 * Run: java --enable-preview --add-modules jdk.incubator.foreign ForeignFunctionMemoryDemo
 */
public class ForeignFunctionMemoryDemo {
    public static void main(String[] args) {
        System.out.println("=== Java 21 Foreign Function & Memory API (Third Preview) ===\n");
        
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
            
            MemorySegment segment = Arena.ofAuto().allocate(100);
            // Use segment for native memory operations
            segment.close(); // Automatic cleanup with Arena
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
            // Confined arena (single-threaded)
            try (Arena arena = Arena.ofConfined()) {
                MemorySegment segment = arena.allocate(1024);
                // Use segment
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
        
        System.out.println("\nKey Features:");
        System.out.println("- Type-safe native interop");
        System.out.println("- Memory safety with arenas");
        System.out.println("- No JNI boilerplate");
        System.out.println("- Better performance than JNI");
        System.out.println("- Structured memory management");
        
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
        
        System.out.println("\nNote: This is a preview feature in Java 21.");
        System.out.println("Requires --enable-preview and --add-modules jdk.incubator.foreign flags.");
    }
}

