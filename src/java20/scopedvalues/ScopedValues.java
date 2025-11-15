package java20.scopedvalues;

/**
 * Java 20 Scoped Values (Incubator)
 * Demonstrates immutable thread-local data sharing
 * 
 * REQUIREMENTS: Java 20 or later
 * 
 * Package History:
 * - Java 20: jdk.incubator.concurrent.ScopedValue (incubator API)
 *   Compile: javac --add-modules jdk.incubator.concurrent ScopedValues.java
 *   Run: java --add-modules jdk.incubator.concurrent ScopedValues
 * 
 * - Java 21+: java.util.concurrent.ScopedValue (finalized, standard API)
 *   No special flags needed
 * 
 * If you see import errors:
 * - Your Java version may be too old (requires Java 20+)
 * - For Java 20: Ensure incubator module is accessible
 * - For Java 21+: Use java.util.concurrent.ScopedValue (current import)
 */

// IMPORT NOTE:
// - Java 20: ScopedValue was in jdk.incubator.concurrent (incubator API)
//   Requires: --add-modules jdk.incubator.concurrent
// - Java 21+: ScopedValue is in java.util.concurrent (finalized, standard API)
//
// Using finalized API for IDE compatibility. For Java 20, change to:
// import jdk.incubator.concurrent.ScopedValue;
// and compile with: javac --add-modules jdk.incubator.concurrent ScopedValues.java

/**
 * Java 20 Scoped Values Example Code
 * 
 * NOTE: This is a conceptual example. The actual implementation requires:
 * - Java 20 or later
 * - jdk.incubator.concurrent module access
 * - Compilation with: javac --add-modules jdk.incubator.concurrent ScopedValues.java
 * - Execution with: java --add-modules jdk.incubator.concurrent ScopedValues
 * 
 * For Java 21+, use: java.util.concurrent.ScopedValue (no incubator module needed)
 */
public class ScopedValues {
    public static void main(String[] args) {
        System.out.println("=== Java 20 Scoped Values (Incubator) ===\n");
        
        System.out.println("Example code structure:");
        System.out.println("""
            // For Java 20, use jdk.incubator.concurrent.ScopedValue
            import jdk.incubator.concurrent.ScopedValue;
            import java.util.concurrent.Executors;
            import java.util.concurrent.ExecutorService;
            
            public class ScopedValues {
                // Define scoped value
                private static final ScopedValue<String> USER = ScopedValue.newInstance();
                private static final ScopedValue<Integer> REQUEST_ID = ScopedValue.newInstance();
                
                public static void main(String[] args) {
                    // 1. Basic usage
                    ScopedValue.runWhere(USER, "Alice", () -> {
                        String user = USER.get();
                        System.out.println("User: " + user);
                        
                        // 2. Nested scope
                        ScopedValue.runWhere(USER, "Bob", () -> {
                            System.out.println("User: " + USER.get());  // Bob
                        });
                        
                        System.out.println("User: " + USER.get());  // Alice (back to outer scope)
                    });
                    
                    // 3. Multiple scoped values
                    ScopedValue.runWhere(
                        USER, "Charlie",
                        REQUEST_ID, 12345,
                        () -> {
                            System.out.println("User: " + USER.get() + ", Request ID: " + REQUEST_ID.get());
                        }
                    );
                    
                    // 4. With virtual threads
                    try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                        ScopedValue.runWhere(USER, "David", () -> {
                            executor.submit(() -> {
                                System.out.println("User in virtual thread: " + USER.get());  // Inherited
                            });
                        });
                    }
                }
            }
            """);
        
        System.out.println("\nKey Features:");
        System.out.println("- Immutable thread-local data");
        System.out.println("- Inherited by child threads (virtual threads)");
        System.out.println("- More efficient than ThreadLocal");
        System.out.println("- Structured scoping");
        System.out.println("- No memory leaks");
        
        System.out.println("\nCompilation:");
        System.out.println("  javac --add-modules jdk.incubator.concurrent ScopedValues.java");
        System.out.println("  java --add-modules jdk.incubator.concurrent ScopedValues");
        
        System.out.println("\nNote: For Java 21+, ScopedValue is in java.util.concurrent (no incubator module needed)");
    }
}
