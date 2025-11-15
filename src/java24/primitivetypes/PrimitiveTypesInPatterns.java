package java24.primitivetypes;

/**
 * Java 24 Primitive Types in Patterns, instanceof, and switch (Second Preview) Demonstrates pattern matching with primitive types
 * NOTE: Requires --enable-preview flag for compilation and execution Compile: javac --enable-preview --release 24 PrimitiveTypesInPatterns.java Run: java --enable-preview PrimitiveTypesInPatterns
 */
public class PrimitiveTypesInPatterns
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 24 Primitive Types in Patterns (Second Preview) ===\n");

		System.out.println("NOTE: This is a preview feature requiring --enable-preview flag.\n");

		System.out.println("Example 1: instanceof with Primitive Types");
		System.out.println("-----------------------------------------");
		Object obj = 42;
		if (obj instanceof Integer i)
		{
			System.out.println("Integer: " + i);
		}

		System.out.println("\nExample 2: Switch with Primitive Patterns");
		System.out.println("----------------------------------------");
		String result = switch (obj)
		{
			case Integer i when i > 0 -> "Positive: " + i;
			case Integer i -> "Zero or negative: " + i;
			case Double d -> "Double: " + d;
			case Boolean b -> "Boolean: " + b;
			default -> "Other";
		};
		System.out.println("Switch result: " + result);

		System.out.println("\nExample 3: Pattern Matching with Primitives");
		System.out.println("-------------------------------------------");
		Number num = 42;
		if (num instanceof Integer i && i > 0)
		{
			System.out.println("Positive integer: " + i);
		}

		System.out.println("\nExample 4: Double Pattern");
		System.out.println("------------------------");
		Object value = 3.14;
		if (value instanceof Double d)
		{
			System.out.println("Double value: " + d);
		}

		System.out.println("\nKey Features:");
		System.out.println("- Pattern matching with primitive types");
		System.out.println("- Consistent pattern matching");
		System.out.println("- Better type safety");
		System.out.println("- Unified approach for all types");

		System.out.println("\nBenefits:");
		System.out.println("- More consistent pattern matching");
		System.out.println("- Better type safety");
		System.out.println("- Eliminates need for wrapper types in patterns");
		System.out.println("- More expressive code");

		System.out.println("\nNote: This is a preview feature in Java 24.");
		System.out.println("Requires --enable-preview flag for compilation and execution.");
	}
}

