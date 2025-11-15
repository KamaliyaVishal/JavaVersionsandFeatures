package java13.switchexpressions;

/**
 * Java 13 Switch Expressions (Second Preview)
 * Demonstrates refinements to switch expressions based on feedback from Java 12. Requires --enable-preview flag to compile and run.
 * Compile: javac --enable-preview --release 13 SwitchExpressions.java Run: java --enable-preview SwitchExpressions
 */
public class SwitchExpressions
{
	public static void main(String[] args)
	{

		System.out.println("=== Switch Expressions (Second Preview) ===\n");

		// 1. Basic switch expression
		System.out.println("--- Basic Switch Expression ---");
		String day = "MONDAY";
		int numLetters = switch (day)
		{
			case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
			case "TUESDAY" -> 7;
			case "WEDNESDAY" -> 9;
			case "THURSDAY", "SATURDAY" -> 8;
			default -> throw new IllegalStateException("Invalid day: " + day);
		};
		System.out.println("Day: " + day + ", Letters: " + numLetters);

		// 2. Using yield keyword for complex cases
		System.out.println("\n--- Using yield Keyword ---");
		String type = switch (day)
		{
			case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" ->
			{
				System.out.println("Processing weekday");
				yield "Weekday";
			}
			case "SATURDAY", "SUNDAY" ->
			{
				System.out.println("Processing weekend");
				yield "Weekend";
			}
			default ->
			{
				System.out.println("Unknown day");
				yield "Unknown";
			}
		};
		System.out.println("Type: " + type);

		// 3. Switch expression with enum
		System.out.println("\n--- Switch Expression with Enum ---");
		Day dayEnum = Day.MONDAY;
		String message = switch (dayEnum)
		{
			case MONDAY -> "Start of week";
			case FRIDAY -> "End of week";
			case SATURDAY, SUNDAY -> "Weekend";
			default -> "Mid-week";
		};
		System.out.println("Message: " + message);

		// 4. Switch expression returning different types
		System.out.println("\n--- Switch Expression with Different Return Types ---");
		Object result = switch (day)
		{
			case "MONDAY" -> 1;
			case "TUESDAY" -> "Two";
			case "WEDNESDAY" -> true;
			default -> 0;
		};
		System.out.println("Result: " + result + " (type: " + result.getClass().getSimpleName() + ")");

		// 5. Practical example: Day type classification
		System.out.println("\n--- Practical Example: Day Classification ---");
		String[] days = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};

		for (String d : days)
		{
			String classification = switch (d)
			{
				case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
				case "SATURDAY", "SUNDAY" -> "Weekend";
				default -> "Unknown";
			};
			System.out.println(d + " is a " + classification);
		}

		// 6. Comparison with traditional switch statement
		System.out.println("\n--- Comparison: Traditional vs Switch Expression ---");

		// Traditional switch statement
		int traditionalResult;
		switch (day)
		{
			case "MONDAY":
			case "FRIDAY":
				traditionalResult = 6;
				break;
			case "TUESDAY":
				traditionalResult = 7;
				break;
			default:
				traditionalResult = -1;
		}
		System.out.println("Traditional switch result: " + traditionalResult);

		// Switch expression (more concise)
		int expressionResult = switch (day)
		{
			case "MONDAY", "FRIDAY" -> 6;
			case "TUESDAY" -> 7;
			default -> -1;
		};
		System.out.println("Switch expression result: " + expressionResult);

		System.out.println("\n=== Benefits ===");
		System.out.println("1. More concise syntax");
		System.out.println("2. No fall-through (eliminates break bugs)");
		System.out.println("3. Can return values");
		System.out.println("4. Exhaustive (must handle all cases or have default)");
		System.out.println("5. Better readability");
	}

	enum Day
	{
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}
}

