package java23.primitivetypes;

/**
 * Java 23 Primitive Types in Patterns, instanceof, and switch (Preview) Demonstrates pattern matching with primitive types
 */
public class PrimitiveTypesInPatterns
{
	public static void main(String[] args)
	{
		// Primitive types in patterns (preview)
		Object obj = 42;

		if (obj instanceof Integer i)
		{
			System.out.println("Integer: " + i);
		}

		// In switch with primitive patterns (preview)
		String result = switch (obj)
		{
			case Integer i when i > 0 -> "Positive: " + i;
			case Integer i -> "Zero or negative: " + i;
			case Double d -> "Double: " + d;
			case Boolean b -> "Boolean: " + b;
			default -> "Other";
		};
		System.out.println("Switch result: " + result);

		// Pattern matching with primitives
		Number num = 42;
		if (num instanceof Integer i && i > 0)
		{
			System.out.println("Positive integer: " + i);
		}

		// Double example
		Object value = 3.14;
		if (value instanceof Double d)
		{
			System.out.println("Double value: " + d);
		}
	}
}

