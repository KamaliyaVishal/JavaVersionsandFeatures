package java13.textblocks;

/**
 * Java 13 Text Blocks (Preview) Demonstrates multi-line string literals
 */
public class TextBlocks
{
	public static void main(String[] args)
	{

		// 1. Basic text block
		System.out.println("=== Basic Text Block ===");
		String html = """
				<html>
				    <body>
				        <p>Hello, World!</p>
				    </body>
				</html>
				""";

		System.out.println(html);

		// 2. JSON example
		System.out.println("\n=== JSON Example ===");
		String json = """
				{
				    "name": "John",
				    "age": 30,
				    "city": "New York"
				}
				""";

		System.out.println(json);

		// 3. SQL example
		System.out.println("\n=== SQL Example ===");
		String sql = """
				SELECT id, name, email
				FROM users
				WHERE status = 'active'
				ORDER BY name
				""";

		System.out.println(sql);

		// 4. Escape sequences
		System.out.println("\n=== Escape Sequences ===");
		String text = """
				She said "Hello"
				""";

		System.out.println(text);

		// Escape newline
		String text2 = """
				Line 1 \
				Line 2
				""";

		System.out.println("Escape newline: " + text2); // Results in "Line 1 Line 2"

		// 5. Comparison with old approach
		System.out.println("\n=== Comparison ===");
		// Old way
		String oldWay = "<html>\n" +
				"    <body>\n" +
				"        <p>Hello</p>\n" +
				"    </body>\n" +
				"</html>";
		System.out.println("Old way (concatenation):");
		System.out.println(oldWay);

		// New way
		String newWay = """
				<html>
				    <body>
				        <p>Hello</p>
				    </body>
				</html>
				""";
		System.out.println("\nNew way (text blocks):");
		System.out.println(newWay);
		System.out.println("\nText blocks are much more readable!");
	}
}

