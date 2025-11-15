package java20.patternmatching;

/**
 * Java 20 Pattern Matching for switch (Fifth Preview) Demonstrates pattern matching with switch expressions
 * NOTE: Requires --enable-preview flag for compilation and execution Compile: javac --enable-preview --release 20 PatternMatchingSwitch.java Run: java --enable-preview PatternMatchingSwitch
 */
public class PatternMatchingSwitch
{
	public static void main(String[] args)
	{

		System.out.println("=== Java 20 Pattern Matching for switch (Fifth Preview) ===\n");

		// 1. Basic pattern matching
		System.out.println("1. Basic Pattern Matching");
		System.out.println("-------------------------");
		Object obj1 = "Hello";
		String result1 = switch (obj1)
		{
			case String s -> "String: " + s;
			case Integer i -> "Integer: " + i;
			case null -> "Null";
			default -> "Unknown type";
		};
		System.out.println("Result: " + result1);

		// 2. With sealed classes (exhaustive matching)
		System.out.println("\n2. Pattern Matching with Sealed Classes");
		System.out.println("----------------------------------------");
		Shape circle = new Circle(5.0);
		Shape rectangle = new Rectangle(10.0, 20.0);
		Shape triangle = new Triangle(8.0, 6.0);

		System.out.println("Circle area: " + calculateArea(circle));
		System.out.println("Rectangle area: " + calculateArea(rectangle));
		System.out.println("Triangle area: " + calculateArea(triangle));

		// 3. Guarded patterns
		System.out.println("\n3. Guarded Patterns");
		System.out.println("------------------");
		Object obj2 = "Hello World";
		String result2 = switch (obj2)
		{
			case String s when s.length() > 10 -> "Long string: " + s;
			case String s -> "Short string: " + s;
			case Integer i when i > 100 -> "Large number: " + i;
			case Integer i -> "Small number: " + i;
			default -> "Unknown";
		};
		System.out.println("Result: " + result2);

		// 4. Multiple types
		System.out.println("\n4. Processing Multiple Types");
		System.out.println("----------------------------");
		processObject("Hello");
		processObject(42);
		processObject(3.14);
		processObject(true);
		processObject(null);

		// 5. Comparison with traditional approach
		System.out.println("\n5. Comparison: Traditional vs Pattern Matching");
		System.out.println("---------------------------------------------");
		Object value = "Hello";

		// Traditional way
		String traditional;
		if (value instanceof String)
		{
			String s = (String) value; // Manual cast
			traditional = "String: " + s;
		}
		else if (value instanceof Integer)
		{
			Integer i = (Integer) value; // Manual cast
			traditional = "Integer: " + i;
		}
		else
		{
			traditional = "Unknown";
		}
		System.out.println("Traditional: " + traditional);

		// Pattern matching way (Java 20)
		String patternMatching = switch (value)
		{
			case String s -> "String: " + s; // Automatic cast
			case Integer i -> "Integer: " + i; // Automatic cast
			default -> "Unknown";
		};
		System.out.println("Pattern matching: " + patternMatching);
	}

	private static double calculateArea(Shape shape)
	{
		// Exhaustive pattern matching with sealed classes
		// No default case needed - compiler knows all possible types
		return switch (shape)
		{
			case Circle c -> Math.PI * c.radius() * c.radius();
			case Rectangle r -> r.width() * r.height();
			case Triangle t -> 0.5 * t.base() * t.height();
		};
	}

	private static void processObject(Object obj)
	{
		String result = switch (obj)
		{
			case String str -> "String: " + str;
			case Integer num -> "Integer: " + num;
			case Double num -> "Double: " + num;
			case Boolean bool -> "Boolean: " + bool;
			case null -> "Null value";
			default -> "Other type: " + obj.getClass().getSimpleName();
		};
		System.out.println("  " + result);
	}

	// Sealed interface for exhaustive pattern matching
	public sealed interface Shape permits Circle, Rectangle, Triangle
	{
	}

	public record Circle(double radius) implements Shape
	{
	}

	public record Rectangle(double width, double height) implements Shape
	{
	}

	public record Triangle(double base, double height) implements Shape
	{
	}
}

