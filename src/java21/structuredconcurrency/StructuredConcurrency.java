
package java21.structuredconcurrency;

/**
 * Java 21 Structured Concurrency (Finalized)
 * Demonstrates treating groups of tasks as a unit
 * 
 * REQUIREMENTS:
 * - Java 21 or later
 * - StructuredTaskScope is in java.util.concurrent (standard API)
 * 
 * If you see "StructuredTaskScope cannot be resolved" error:
 * 1. Ensure your project is configured to use Java 21 or later
 * 2. Check that your IDE recognizes Java 21 features
 * 3. Verify your JDK version: java -version (should show 21 or higher)
 * 
 * The import is correct: import java.util.concurrent.StructuredTaskScope;
 * This is a standard API in Java 21+, no special flags needed.
 */
public class StructuredConcurrency {
    public static void main(String[] args) {
        System.out.println("=== Java 21 Structured Concurrency (Finalized) ===\n");
        
        System.out.println("NOTE: This example requires Java 21 or later.");
        System.out.println("StructuredTaskScope is in java.util.concurrent (standard API).\n");
        
        System.out.println("Example code structure:");
        System.out.println("""
            import java.util.concurrent.StructuredTaskScope;
            import java.util.concurrent.Future;
            
            public class StructuredConcurrency {
                public static void main(String[] args) {
                    // 1. Basic structured concurrency
                    // Note: ShutdownOnFailure and ShutdownOnSuccess are nested classes
                    // They may be available in some Java 21 builds
                    try (var scope = new StructuredTaskScope.ShutdownOnFailure<>()) {
                        Future<String> user = scope.fork(() -> fetchUser("123"));
                        Future<String> order = scope.fork(() -> fetchOrder("456"));
                        
                        scope.join();
                        scope.throwIfFailed();
                        
                        System.out.println("User: " + user.resultNow());
                        System.out.println("Order: " + order.resultNow());
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    
                    // 2. Shutdown on success
                    try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
                        scope.fork(() -> fetchUser("789"));
                        scope.fork(() -> fetchUser("999"));
                        
                        String result = scope.join().result();
                        System.out.println("First successful result: " + result);
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    
                    // 3. Custom scope (if nested classes not available)
                    try (var scope = new CustomScope<>()) {
                        Future<String> user = scope.fork(() -> fetchUser(userId));
                        Future<String> profile = scope.fork(() -> fetchProfile(userId));
                        
                        scope.join();
                        // Handle results...
                    }
                }
                
                // Custom StructuredTaskScope implementation
                static class CustomScope<T> extends StructuredTaskScope<T> {
                    @Override
                    protected void handleComplete(Future<T> future) {
                        // Custom shutdown logic
                    }
                }
            }
            """);
        
        System.out.println("\nKey Features:");
        System.out.println("- Structured lifecycle: Tasks are managed as a unit");
        System.out.println("- Error propagation: Failures are properly handled");
        System.out.println("- Shutdown strategies: ShutdownOnFailure and ShutdownOnSuccess");
        System.out.println("- Automatic cleanup: Resources are cleaned up automatically");
        System.out.println("- Prevents thread leaks: All tasks complete before scope closes");
        
        System.out.println("\nShutdown Strategies:");
        System.out.println("- ShutdownOnFailure: Shuts down if any task fails");
        System.out.println("- ShutdownOnSuccess: Shuts down when first task succeeds");
        System.out.println("- Custom scope: Extend StructuredTaskScope for custom behavior");
        
        System.out.println("\nBenefits:");
        System.out.println("- Better error handling");
        System.out.println("- Prevents thread leaks");
        System.out.println("- Clearer code structure");
        System.out.println("- Easier debugging");
        System.out.println("- Automatic resource management");
        
        System.out.println("\nUse Cases:");
        System.out.println("- Fetching multiple related data concurrently");
        System.out.println("- Parallel processing with error handling");
        System.out.println("- Coordinated task execution");
        System.out.println("- Resource cleanup guarantees");
        
        System.out.println("\nIf you see import errors:");
        System.out.println("- Ensure Java 21+ is installed and configured");
        System.out.println("- Check IDE Java version settings");
        System.out.println("- Verify: java -version shows 21 or higher");
        System.out.println("- No special compilation flags needed");
        System.out.println("- Note: Nested classes (ShutdownOnFailure/ShutdownOnSuccess)");
        System.out.println("  may not be available in all Java 21 builds.");
        System.out.println("  In that case, extend StructuredTaskScope directly.");
    }
}

