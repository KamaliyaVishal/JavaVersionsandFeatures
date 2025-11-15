package java25.constructors;

/**
 * Java 25 Flexible Constructor Bodies Demonstrates statements before super() or this()
 * NOTE: This feature is finalized in Java 25 - no preview flag needed!
 */
public class FlexibleConstructorBodies
{
	public static void main(String[] args)
	{
		Child child = new Child("  john  ");
		System.out.println("Child name: " + child.getName());
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
		// Statements before super() - now allowed in Java 25
		// Note: Requires Java 25+ (finalized feature)
		// Important: Cannot access instance fields (this.field) before super()
		// Can only use local variables and static methods
		if (name == null || name.isBlank())
		{
			throw new IllegalArgumentException("Name cannot be null or blank");
		}

		// Use local variable instead of instance field
		String processed = name.trim().toUpperCase();
		String validated = validate(processed);

		super(validated); // Now allowed after statements

		// After super(), can now access instance fields
		this.processedName = processed;
		System.out.println("Child initialized with: " + processedName);
	}

	private static String validate(String s)
	{
		return s.length() > 0 ? s : "Default";
	}

	String getName()
	{
		return processedName;
	}
}
