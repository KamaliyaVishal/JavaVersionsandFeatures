package java22.unnamedvariables;

import java.util.List;
import java.util.Arrays;

/**
 * Java 22 Unnamed Variables and Patterns (Finalized) Demonstrates using _ for unused variables
 */
public class UnnamedVariables
{
	public static void main(String[] args)
	{

		// 1. Unnamed variables
		System.out.println("=== Unnamed Variables ===");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		// Count elements without using value
		int count = 0;
		for (var _ : numbers)
		{
			count++;
		}
		System.out.println("Count: " + count);

		// 2. In catch blocks
		System.out.println("\n=== Unnamed in Catch ===");
		try
		{
			riskyOperation();
		}
		catch (Exception _)
		{
			System.out.println("Error occurred (exception not used)");
		}

		// 3. In lambdas
		System.out.println("\n=== Unnamed in Lambdas ===");
		numbers.forEach((var _) -> {
			System.out.println("Processing element");
		});

		// 4. Unnamed patterns
		System.out.println("\n=== Unnamed Patterns ===");
		Point point = new Point(5, 10);

		if (point instanceof Point(int x, int _))
		{
			System.out.println("Only using X: " + x);
		}

		// 5. Multiple assignments
		System.out.println("\n=== Multiple Assignments ===");
		var _ = processData();
		// Result not used

		// 6. Practical example
		System.out.println("\n=== Practical Example ===");
		processItems(Arrays.asList("a", "b", "c", "d", "e"));
	}

	private static void riskyOperation()
	{
		throw new RuntimeException("Test exception");
	}

	private static String processData()
	{
		return "Result";
	}

	private static void processItems(List<String> items)
	{
		int processed = 0;
		for (var _ : items)
		{
			processed++;
			// Process item but don't use value
		}
		System.out.println("Processed " + processed + " items");
	}

	record Point(int x, int y)
	{
	}
}

