package java23.structuredconcurrency;

/**
 * Java 23 Structured Concurrency (Third Preview)
 * Demonstrates treating groups of tasks as a unit
 * 
 * NOTE: Requires --enable-preview flag for compilation and execution
 * Compile: javac --enable-preview --release 23 StructuredConcurrency.java
 * Run: java --enable-preview StructuredConcurrency
 */
public class StructuredConcurrency {
    public static void main(String[] args) {
        System.out.println("=== Java 23 Structured Concurrency (Third Preview) ===\n");
        
        System.out.println("NOTE: This is a preview feature requiring --enable-preview flag.\n");
        
        System.out.println("Example code structure:");
        System.out.println("""
            import java.util.concurrent.StructuredTaskScope;
            import java.util.concurrent.Future;
            
            public class StructuredConcurrency {
                public static void main(String[] args) {
                    // 1. Basic structured concurrency
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
                }
            }
            """);
        
        System.out.println("\nKey Features:");
        System.out.println("- Structured lifecycle: Tasks are managed as a unit");
        System.out.println("- Error propagation: Failures are properly handled");
        System.out.println("- Shutdown strategies: ShutdownOnFailure and ShutdownOnSuccess");
        System.out.println("- Automatic cleanup: Resources are cleaned up automatically");
        System.out.println("- Prevents thread leaks: All tasks complete before scope closes");
        
        System.out.println("\nBenefits:");
        System.out.println("- Better error handling");
        System.out.println("- Prevents thread leaks");
        System.out.println("- Clearer code structure");
        System.out.println("- Easier debugging");
        System.out.println("- Automatic resource management");
        
        System.out.println("\nNote: This is a preview feature in Java 23.");
        System.out.println("Requires --enable-preview flag for compilation and execution.");
    }
}

