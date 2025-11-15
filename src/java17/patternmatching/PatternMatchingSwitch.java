package java17.patternmatching;

/**
 * Java 17 Pattern Matching for switch (Preview) Demonstrates pattern matching with switch expressions
 */
public class PatternMatchingSwitch
{
	public static void main(String[] args)
	{

		Object obj = "Hello";

		// 1. Pattern matching with switch (preview - use --enable-preview)
		// For compilation without preview, using traditional instanceof checks
		//        String result;
		//        if (obj instanceof String s) {
		//            result = "String: " + s;
		//        } else if (obj instanceof Integer i) {
		//            result = "Integer: " + i;
		//        } else if (obj == null) {
		//            result = "Null";
		//        } else {
		//            result = "Unknown";
		//        }

		// Note: With --enable-preview, you can use:
		String result = switch (obj)
		{
			case String s -> "String: " + s;
			case Integer i -> "Integer: " + i;
			case null -> "Null";
			default -> "Unknown";
		};
		System.out.println("Result: " + result);

		// 2. With sealed classes
		Shape circle = new Circle(5.0);
		//        double area;
		//        if (circle instanceof Circle c) {
		//            area = Math.PI * c.radius() * c.radius();
		//        } else if (circle instanceof Rectangle r) {
		//            area = r.width() * r.height();
		//        } else if (circle instanceof Triangle t) {
		//            area = 0.5 * t.base() * t.height();
		//        } else {
		//            area = 0.0;
		//        }

		// Note: With --enable-preview, you can use:
		double area = switch (circle)
		{
			case Circle c -> Math.PI * c.radius() * c.radius();
			case Rectangle r -> r.width() * r.height();
			case Triangle t -> 0.5 * t.base() * t.height();
		};
		System.out.println("Circle area: " + area);

		// 3. Guarded patterns
		Object value = "Hello World";
		//        String output;
		//        if (value instanceof String s && s.length() > 10) {
		//            output = "Long string: " + s;
		//        } else if (value instanceof String s) {
		//            output = "Short string: " + s;
		//        } else if (value instanceof Integer i && i > 100) {
		//            output = "Large number: " + i;
		//        } else if (value instanceof Integer i) {
		//            output = "Small number: " + i;
		//        } else {
		//            output = "Unknown";
		//        }

		// Note: With --enable-preview, you can use:
		String output = switch (value)
		{
			case String s when s.length() > 10 -> "Long string: " + s;
			case String s -> "Short string: " + s;
			case Integer i when i > 100 -> "Large number: " + i;
			case Integer i -> "Small number: " + i;
			default -> "Unknown";
		};
		System.out.println("Output: " + output);
	}

	sealed interface Shape permits Circle, Rectangle, Triangle
	{
	}

	record Circle(double radius) implements Shape
	{
	}

	record Rectangle(double width, double height) implements Shape
	{
	}

	record Triangle(double base, double height) implements Shape
	{
	}
}

