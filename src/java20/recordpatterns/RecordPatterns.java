package java20.recordpatterns;

/**
 * Java 20 Record Patterns (Second Preview) Demonstrates refinements to record patterns
 */
public class RecordPatterns
{
	public static void main(String[] args)
	{
		record Point(int x, int y)
		{
		}
		record Rectangle(Point topLeft, Point bottomRight)
		{
		}

		// Pattern matching with records
		Object obj = new Point(5, 10);

		if (obj instanceof Point(int x, int y))
		{
			System.out.println("X: " + x + ", Y: " + y);
		}

		// Nested patterns
		Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 10));

		if (rect instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2)))
		{
			System.out.println("Width: " + (x2 - x1) + ", Height: " + (y2 - y1));
		}

		// In switch
		String result = switch (obj)
		{
			case Point(int x, int y) when x > 0 && y > 0 -> "Positive: (" + x + ", " + y + ")";
			case Point(int x, int y) -> "Other: (" + x + ", " + y + ")";
			default -> "Not a point";
		};
		System.out.println("Result: " + result);
	}
}

