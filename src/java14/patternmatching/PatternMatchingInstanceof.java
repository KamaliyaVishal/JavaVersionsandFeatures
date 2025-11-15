package java14.patternmatching;

/**
 * Java 14 Pattern Matching for instanceof (Preview) Demonstrates simplified instanceof checks with automatic casting
 */
public class PatternMatchingInstanceof
{
	public static void main(String[] args)
	{

		Object obj = "Hello";

		// 1. Pattern matching with instanceof
		System.out.println("=== Pattern Matching ===");
		if (obj instanceof String str)
		{
			// str is automatically available, no cast needed
			System.out.println("String: " + str.toUpperCase());
			System.out.println("Length: " + str.length());
		}

		// 2. With complex conditions
		System.out.println("\n=== With Conditions ===");
		Object value = "Hello World";

		if (value instanceof String str && str.length() > 5)
		{
			System.out.println("Long string: " + str);
		}

		// 3. Comparison with traditional approach
		System.out.println("\n=== Comparison ===");
		// Traditional way
		if (obj instanceof String)
		{
			String str = (String) obj; // Manual cast
			System.out.println("Traditional: " + str);
		}

		// Pattern matching way
		if (obj instanceof String str)
		{
			System.out.println("Pattern matching: " + str); // No cast needed
		}

		// 4. Multiple types
		System.out.println("\n=== Multiple Types ===");
		processObject("Hello");
		processObject(42);
		processObject(3.14);
		processObject(null);

		// 5. With negation
		System.out.println("\n=== With Negation ===");
		if (!(obj instanceof Integer num))
		{
			System.out.println("Not an integer");
		}
		else
		{
			System.out.println("Integer: " + num);
		}
	}

	private static void processObject(Object obj)
	{
		if (obj instanceof String str)
		{
			System.out.println("String: " + str);
		}
		else if (obj instanceof Integer num)
		{
			System.out.println("Integer: " + num);
		}
		else if (obj instanceof Double num)
		{
			System.out.println("Double: " + num);
		}
		else
		{
			System.out.println("Other type: " + (obj != null ? obj.getClass().getSimpleName() : "null"));
		}
	}
}

