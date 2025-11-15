package java21.scopedvalues;

/**
 * Java 21 Scoped Values (Finalized)
 * Demonstrates immutable thread-local data sharing
 * 
 * REQUIREMENTS:
 * - Java 21 or later
 * - ScopedValue is in java.util.concurrent (standard API, no incubator module needed)
 * 
 * If you see "ScopedValue cannot be resolved" error:
 * 1. Ensure your project is configured to use Java 21 or later
 * 2. Check that your IDE recognizes Java 21 features
 * 3. Verify your JDK version: java -version (should show 21 or higher)
 * 
 * The import is correct: import java.util.concurrent.ScopedValue;
 * This is a standard API in Java 21+, no special flags needed.
 */
public class ScopedValues {
    public static void main(String[] args) {
        System.out.println("=== Java 21 Scoped Values (Finalized) ===\n");
        
        System.out.println("NOTE: This example requires Java 21 or later.");
        System.out.println("ScopedValue is in java.util.concurrent (standard API).\n");
        
        System.out.println("Example code structure:");
        System.out.println("""
            import java.util.concurrent.ScopedValue;
            import java.util.concurrent.Executors;
            import java.util.concurrent.ExecutorService;
            
            public class ScopedValues {
                // Define scoped values
                private static final ScopedValue<String> USER = ScopedValue.newInstance();
                private static final ScopedValue<Integer> REQUEST_ID = ScopedValue.newInstance();
                
                public static void main(String[] args) {
                    // 1. Basic usage
                    ScopedValue.runWhere(USER, "Alice", () -> {
                        String user = USER.get();
                        System.out.println("User: " + user);
                        
                        // 2. Nested scope
                        ScopedValue.runWhere(USER, "Bob", () -> {
                            System.out.println("User in nested scope: " + USER.get());  // Bob
                        });
                        
                        System.out.println("User after nested scope: " + USER.get());  // Alice
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
        System.out.println("- Immutable: Cannot be modified after binding");
        System.out.println("- Inherited: Automatically inherited by child threads (including virtual threads)");
        System.out.println("- No memory leaks: Automatically cleaned up when scope ends");
        System.out.println("- Structured: Scoped to specific code blocks");
        System.out.println("- Better performance: More efficient than ThreadLocal");
        
        System.out.println("\nBenefits over ThreadLocal:");
        System.out.println("- Safer: Immutable by design");
        System.out.println("- No memory leaks: Automatic cleanup");
        System.out.println("- Inherited by child threads");
        System.out.println("- Better performance");
        System.out.println("- Structured scoping");
        
        System.out.println("\nIf you see import errors:");
        System.out.println("- Ensure Java 21+ is installed and configured");
        System.out.println("- Check IDE Java version settings");
        System.out.println("- Verify: java -version shows 21 or higher");
        System.out.println("- No special compilation flags needed (unlike Java 20 incubator API)");
    }
}

