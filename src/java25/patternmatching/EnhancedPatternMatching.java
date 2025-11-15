package java25.patternmatching;

/**
 * Java 25 Enhanced Pattern Matching Demonstrates advanced pattern matching capabilities
 * NOTE: Pattern matching features are finalized in Java 25
 */
public class EnhancedPatternMatching
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 25 Enhanced Pattern Matching ===\n");

		System.out.println("Example 1: Record Patterns");
		System.out.println("--------------------------");
		record Point(int x, int y)
		{
		}
		record Rectangle(Point topLeft, Point bottomRight)
		{
		}

		Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 10));

		String result = switch (rect)
		{
			case Rectangle(Point(int x1, int y1), Point(int x2, int y2))
					when x2 > x1 && y2 > y1 ->
					"Valid rectangle: width=" + (x2 - x1) + ", height=" + (y2 - y1);
			case Rectangle(Point(_, _), Point(_, _)) ->
					"Rectangle with zero or negative dimensions";
			default -> "Not a rectangle";
		};
		System.out.println(result);

		System.out.println("\nExample 2: Primitive Type Patterns");
		System.out.println("----------------------------------");
		Object obj = 42;
		if (obj instanceof Integer i && i > 0)
		{
			System.out.println("Positive integer: " + i);
		}

		System.out.println("\nExample 3: Pattern Matching in instanceof");
		System.out.println("------------------------------------------");
		String result2 = obj instanceof Integer i
				? "Integer: " + i
				: "Not an integer";
		System.out.println(result2);

		System.out.println("\nExample 4: Nested Patterns");
		System.out.println("-------------------------");
		Object nested = new Point(5, 10);
		if (nested instanceof Point(int x, int y) && x > 0 && y > 0)
		{
			System.out.println("Point in first quadrant: (" + x + ", " + y + ")");
		}

		System.out.println("\nKey Features:");
		System.out.println("- Record patterns with nested matching");
		System.out.println("- Primitive type patterns");
		System.out.println("- Pattern guards (when clauses)");
		System.out.println("- Exhaustive pattern matching with sealed classes");
		System.out.println("- More concise and type-safe code");

		System.out.println("\nBenefits:");
		System.out.println("- More concise code");
		System.out.println("- Better type safety");
		System.out.println("- Exhaustive pattern matching");
		System.out.println("- Nested patterns for complex data structures");
		System.out.println("- Finalized feature (no preview flag needed)");
	}
}

