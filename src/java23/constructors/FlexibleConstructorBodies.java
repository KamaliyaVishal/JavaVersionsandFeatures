package java23.constructors;

/**
 * Java 23 Flexible Constructor Bodies (Second Preview) Demonstrates statements allowed before super() call
 * NOTE: This is a preview feature that requires special compilation flags. The linter errors you see are expected when preview is not enabled.
 * To compile and run: javac --enable-preview --release 23 FlexibleConstructorBodies.java java --enable-preview FlexibleConstructorBodies
 * The code structure is correct for Java 23 preview feature. Instance methods cannot be called before super() - only static methods are allowed.
 */
public class FlexibleConstructorBodies
{
	public static void main(String[] args)
	{
		Child child = new Child("  John  ");
		System.out.println("Child created: " + child.getName());
	}
}

class Parent
{
	private String name;

	Parent(String name)
	{
		this.name = name;
		System.out.println("Parent: " + name);
	}

	String getName()
	{
		return name;
	}
}

class Child extends Parent
{
	private String processedName;

	Child(String name)
	{
		// Statements before super() - preview feature in Java 23
		// Note: Requires --enable-preview flag to compile
		if (name == null || name.isBlank())
		{
			throw new IllegalArgumentException("Name cannot be null or blank");
		}

		String processed = name.trim().toUpperCase();
		String validated = validate(processed);  // Static method call allowed

		super(validated);  // Now allowed after statements (preview)

		// After super()
		this.processedName = validated;
		System.out.println("Child initialized with: " + processedName);
	}

	private static String validate(String s)
	{
		return s.length() > 0 ? s : "Default";
	}
}

