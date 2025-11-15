package java23.scopedvalues;

/**
 * Java 23 Scoped Values (Third Preview)
 * Demonstrates immutable thread-local data sharing
 * 
 * NOTE: Requires --enable-preview flag for compilation and execution
 * Compile: javac --enable-preview --release 23 ScopedValues.java
 * Run: java --enable-preview ScopedValues
 * 
 * In Java 23, ScopedValue is in java.util.concurrent (preview API)
 * In Java 21+, it's finalized in java.util.concurrent (standard API)
 */
public class ScopedValues {
    public static void main(String[] args) {
        System.out.println("=== Java 23 Scoped Values (Third Preview) ===\n");
        
        System.out.println("NOTE: This is a preview feature requiring --enable-preview flag.\n");
        
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
        
        System.out.println("\nNote: This is a preview feature in Java 23.");
        System.out.println("Requires --enable-preview flag for compilation and execution.");
        System.out.println("In Java 21+, ScopedValue is finalized (no preview flag needed).");
    }
}

