package java22.stringtemplates;

/**
 * Java 22 String Templates (Second Preview) Demonstrates string interpolation with template processors
 * NOTE: String Templates were withdrawn in Java 23+ due to design concerns. This code demonstrates the concept using traditional string formatting.
 * For Java 22, String Templates were available with FormatProcessor. For Java 23+, use traditional string concatenation or String.format().
 */
public class StringTemplates
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 22 String Templates (Second Preview) ===\n");

		String name = "John";
		int age = 30;

		System.out.println("NOTE: String Templates were withdrawn in Java 23+");
		System.out.println("Using traditional string formatting instead:\n");

		// 1. Basic string interpolation (traditional approach)
		System.out.println("1. Basic String Interpolation");
		System.out.println("-----------------------------");
		String message = "Hello, " + name + "! You are " + age + " years old.";
		System.out.println(message);

		// 2. Formatted template (using String.format)
		System.out.println("\n2. Formatted Templates");
		System.out.println("---------------------");
		String formatted = String.format("Age: %03d", age);
		System.out.println(formatted); // Age: 030

		// 3. Complex expressions
		System.out.println("\n3. Complex Expressions");
		System.out.println("---------------------");
		int x = 10;
		int y = 20;
		String calculation = "Sum: " + x + " + " + y + " = " + (x + y);
		System.out.println(calculation);

		// 4. Multi-line template
		System.out.println("\n4. Multi-line Template");
		System.out.println("---------------------");
		String info = """
				Name: %s
				Age: %d
				Status: Active
				""".formatted(name, age);
		System.out.println(info);

		// 5. Comparison with old approach
		System.out.println("\n5. Comparison");
		System.out.println("-------------");
		// Traditional way
		String oldWay = "Hello, " + name + "! You are " + age + " years old.";

		// Using String.format (more readable for complex formatting)
		String formattedWay = String.format("Hello, %s! You are %d years old.", name, age);

		System.out.println("Concatenation: " + oldWay);
		System.out.println("String.format: " + formattedWay);

		System.out.println("\nNote: String Templates (STR.\"text{var}\") were available in Java 21-22");
		System.out.println("but were withdrawn in Java 23+ due to design concerns.");
		System.out.println("\nExample of what String Templates looked like (Java 22):");
		System.out.println("  String message = STR.\"Hello, \\{name}! You are \\{age} years old.\";");
		System.out.println("  String formatted = FMT.\"Age: %03d\\{age}\";");
	}
}

