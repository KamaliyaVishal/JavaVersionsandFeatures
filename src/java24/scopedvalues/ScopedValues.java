package java24.scopedvalues;

/**
 * Java 24 Scoped Values (Finalized) Demonstrates immutable thread-local data sharing
 * NOTE: This feature is now finalized - no preview flag needed!
 * 
 * If you encounter "ScopedValue cannot be resolved" errors, ensure:
 * 1. You're using Java 21 or later (ScopedValue was finalized in Java 21)
 * 2. Your IDE is configured to use Java 21+ JDK
 * 3. Your project is set to Java 21+ language level
 */
public class ScopedValues
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 24 Scoped Values (Finalized) ===\n");

		System.out.println("Overview:");
		System.out.println("--------");
		System.out.println("Scoped Values provide a way to share immutable data within and across threads,");
		System.out.println("offering a safer alternative to ThreadLocal.");
		System.out.println("This feature is now finalized in Java 24.\n");

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
				        });
				        
				        // 2. Nested scope
				        ScopedValue.runWhere(USER, "Alice", () -> {
				            System.out.println("User in outer scope: " + USER.get()); // Alice
				            
				            ScopedValue.runWhere(USER, "Bob", () -> {
				                System.out.println("User in nested scope: " + USER.get()); // Bob
				            });
				            
				            System.out.println("User after nested scope: " + USER.get()); // Alice
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
				                    System.out.println("User in virtual thread: " + USER.get()); // Inherited
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
		System.out.println("- Finalized feature (no preview flag needed)");

		System.out.println("\nBenefits over ThreadLocal:");
		System.out.println("- Safer: Immutable by design");
		System.out.println("- No memory leaks: Automatic cleanup");
		System.out.println("- Inherited by child threads");
		System.out.println("- Better performance");
		System.out.println("- Structured scoping");
		System.out.println("- Production-ready (finalized)");

		System.out.println("\nTroubleshooting:");
		System.out.println("If you see 'ScopedValue cannot be resolved' errors:");
		System.out.println("1. Ensure you're using Java 21 or later");
		System.out.println("2. Check your IDE's Java version settings");
		System.out.println("3. Verify your project's language level is Java 21+");
		System.out.println("4. ScopedValue is in java.util.concurrent package (standard API)");

		System.out.println("\nNote: This feature is finalized in Java 21+ (including Java 24).");
		System.out.println("No --enable-preview flag needed!");
	}
}

