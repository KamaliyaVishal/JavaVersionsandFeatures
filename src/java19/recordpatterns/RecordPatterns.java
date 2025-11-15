package java19.recordpatterns;

/**
 * Java 19 Record Patterns (Preview) Demonstrates pattern matching with records
 */
public class RecordPatterns
{
	public static void main(String[] args)
	{
		// Basic record
		record Point(int x, int y)
		{
		}

		// Pattern matching with records
		Object obj = new Point(5, 10);

		if (obj instanceof Point p)
		{
			System.out.println("Point: X=" + p.x() + ", Y=" + p.y());
		}

		// Destructuring pattern (preview)
		if (obj instanceof Point(int x, int y))
		{
			System.out.println("Destructured: X=" + x + ", Y=" + y);
		}

		// In switch expression
		String result = switch (obj)
		{
			case Point(int x, int y) when x > 0 && y > 0 -> "Positive quadrant: (" + x + ", " + y + ")";
			case Point(int x, int y) -> "Other quadrant: (" + x + ", " + y + ")";
			default -> "Not a point";
		};
		System.out.println("Switch result: " + result);

		// Nested patterns
		record Rectangle(Point topLeft, Point bottomRight)
		{
		}

		Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 10));

		if (rect instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2)))
		{
			int width = x2 - x1;
			int height = y2 - y1;
			System.out.println("Rectangle: width=" + width + ", height=" + height);
		}
	}
}

