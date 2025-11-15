package java23.streamgatherers;

import java.util.List;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * Java 23 Stream Gatherers (Second Preview) Demonstrates custom intermediate stream operations
 * NOTE: Requires --enable-preview flag for compilation and execution Compile: javac --enable-preview --release 23 StreamGatherersDemo.java Run: java --enable-preview StreamGatherersDemo
 */
public class StreamGatherersDemo
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 23 Stream Gatherers (Second Preview) ===\n");

		System.out.println("NOTE: This is a preview feature requiring --enable-preview flag.\n");

		System.out.println("Example 1: Custom Gatherer - Filter and Transform");
		System.out.println("--------------------------------------------------");
		System.out.println("Creating a custom gatherer that filters strings longer than 5 characters");
		System.out.println("and transforms them to uppercase.");
		System.out.println("Input: [apple, banana, cherry, kiwi]");

		// Custom gatherer to filter and transform
		Gatherer<String, ?, String> filterAndUpper = Gatherer.ofSequential(
				() -> new Object[1],  // State
				(state, element, downstream) -> {
					if (element.length() > 5)
					{
						return downstream.push(element.toUpperCase());
					}
					return true;  // Continue
				},
				(state, downstream) -> {
					// Final processing
				}
		);

		List<String> result = Stream.of("apple", "banana", "cherry", "kiwi")
				.gather(filterAndUpper)
				.toList();

		System.out.println("Result: " + result);
		System.out.println("Explanation: Only 'banana' (6 chars) and 'cherry' (6 chars) are > 5, so they are uppercased.");
		System.out.println("'apple' (5 chars) and 'kiwi' (4 chars) are filtered out.\n");

		System.out.println("Example 2: Window Sliding - Sliding Window");
		System.out.println("------------------------------------------");
		System.out.println("Creating sliding windows of size 3 from a stream of integers.");
		System.out.println("Input: [1, 2, 3, 4, 5]");

		// Sliding window of size 3
		List<List<Integer>> windows = Stream.of(1, 2, 3, 4, 5)
				.gather(Gatherers.windowSliding(3))
				.toList();

		System.out.println("Result: " + windows);
		System.out.println("Explanation: Each window slides by 1 position:");
		System.out.println("  Window 1: [1, 2, 3]");
		System.out.println("  Window 2: [2, 3, 4]");
		System.out.println("  Window 3: [3, 4, 5]\n");

		System.out.println("Example 3: Window Fixed - Fixed Window");
		System.out.println("--------------------------------------");
		System.out.println("Creating fixed windows of size 3 from a stream of integers.");
		System.out.println("Input: [1, 2, 3, 4, 5, 6]");

		// Fixed window of size 3
		List<List<Integer>> fixed = Stream.of(1, 2, 3, 4, 5, 6)
				.gather(Gatherers.windowFixed(3))
				.toList();

		System.out.println("Result: " + fixed);
		System.out.println("Explanation: Elements are grouped into fixed-size windows:");
		System.out.println("  Window 1: [1, 2, 3]");
		System.out.println("  Window 2: [4, 5, 6]");
		System.out.println("Note: Unlike sliding window, fixed window doesn't overlap.\n");

		System.out.println("Example 4: Fold - Accumulate Values");
		System.out.println("----------------------------------");
		System.out.println("Using fold to accumulate string values into a single result.");
		System.out.println("Input: [a, b, c]");

		// Fold operation
		String result2 = Stream.of("a", "b", "c")
				.gather(Gatherers.fold(() -> "", (acc, elem) -> acc + elem))
				.findFirst()
				.orElse("");

		System.out.println("Result: \"" + result2 + "\"");
		System.out.println("Explanation: Fold accumulates all elements:");
		System.out.println("  Step 1: \"\" + \"a\" = \"a\"");
		System.out.println("  Step 2: \"a\" + \"b\" = \"ab\"");
		System.out.println("  Step 3: \"ab\" + \"c\" = \"abc\"");
		System.out.println("Final result: \"abc\"\n");

		System.out.println("\nKey Features:");
		System.out.println("- Custom intermediate operations");
		System.out.println("- Extend Stream API functionality");
		System.out.println("- Reusable stream operations");
		System.out.println("- More flexible than existing operations");
		System.out.println("- Built-in gatherers available");

		System.out.println("\nBuilt-in Gatherers:");
		System.out.println("- windowSliding(n): Sliding window");
		System.out.println("- windowFixed(n): Fixed window");
		System.out.println("- fold(): Accumulate values");
		System.out.println("- scan(): Scan with intermediate results");

		System.out.println("\nBenefits:");
		System.out.println("- Extend Stream API");
		System.out.println("- Create reusable operations");
		System.out.println("- More expressive code");
		System.out.println("- Better performance for custom operations");

		System.out.println("\nNote: This is a preview feature in Java 23.");
		System.out.println("Requires --enable-preview flag for compilation and execution.");
	}
}

