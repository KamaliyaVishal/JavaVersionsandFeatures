package java23.markdown;

/**
 * Java 23 Markdown Documentation Comments Demonstrates using Markdown syntax in Javadoc comments
 * This feature allows you to use Markdown syntax in Javadoc comments, making documentation more readable and easier to write.
 */
public class MarkdownDocumentationComments
{
	/**
	 * # Example Method
	 * This method demonstrates **Markdown** syntax in Javadoc.
	 * ## Features
	 * - **Bold text** using `**text**` - *Italic text* using `*text*` - Code blocks using backticks - Lists and nested structures
	 * ### Usage
	 * ```java MarkdownDocumentationComments example = new MarkdownDocumentationComments(); example.exampleMethod(); ```
	 *
	 * @param value The input value (must be > 0)
	 * @return The processed result
	 * @throws IllegalArgumentException if value <= 0
	 */
	public int exampleMethod(int value)
	{
		if (value <= 0)
		{
			throw new IllegalArgumentException("Value must be positive");
		}
		return value * 2;
	}

	/**
	 * ## Class Overview
	 * This class demonstrates **Markdown** in Javadoc comments.
	 * ### Key Points
	 * 1. Markdown syntax is supported 2. Makes documentation more readable 3. Easier to write and maintain
	 * ### Example
	 * ```java // Create instance MarkdownDocumentationComments obj = new MarkdownDocumentationComments();
	 * // Call method int result = obj.exampleMethod(5); ```
	 */
	public static void main(String[] args)
	{
		System.out.println("=== Java 23 Markdown Documentation Comments ===\n");

		System.out.println("Overview:");
		System.out.println("--------");
		System.out.println("Java 23 allows you to use Markdown syntax in Javadoc comments.");
		System.out.println("This makes documentation more readable and easier to write.\n");

		System.out.println("Supported Markdown Features:");
		System.out.println("- Headers (#, ##, ###)");
		System.out.println("- Bold text (**text**)");
		System.out.println("- Italic text (*text*)");
		System.out.println("- Code blocks (```)");
		System.out.println("- Inline code (`code`)");
		System.out.println("- Lists (-, *, 1.)");
		System.out.println("- Links ([text](url))");
		System.out.println("- Tables");

		System.out.println("\nExample Javadoc with Markdown:");
		System.out.println("""
				/**
				 * # Title
				 * 
				 * This is a **bold** statement and *italic* text.
				 * 
				 * ## Section
				 * 
				 * - Item 1
				 * - Item 2
				 * 
				 * ### Code Example
				 * 
				 * ```java
				 * int value = 42;
				 * ```
				 */
				""");

		System.out.println("\nBenefits:");
		System.out.println("- More readable documentation");
		System.out.println("- Easier to write");
		System.out.println("- Better formatting");
		System.out.println("- Standard Markdown syntax");
		System.out.println("- Works with existing Javadoc tools");

		System.out.println("\nNote: This feature is available in Java 23+.");
		System.out.println("No special flags needed - just use Markdown in your Javadoc comments!");
	}
}

