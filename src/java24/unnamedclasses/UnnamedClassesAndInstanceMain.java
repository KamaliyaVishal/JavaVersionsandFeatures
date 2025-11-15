package java24.unnamedclasses;

/**
 * Java 24 Implicitly Declared Classes and Instance Main Methods (Finalized) Demonstrates simplified class structure for simple programs
 * NOTE: This feature is now finalized - no preview flag needed!
 */
public class UnnamedClassesAndInstanceMain
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 24 Implicitly Declared Classes and Instance Main Methods (Finalized) ===\n");

		System.out.println("Overview:");
		System.out.println("--------");
		System.out.println("Unnamed classes allow you to write simple programs without explicit class declarations.");
		System.out.println("This feature is now finalized in Java 24.\n");

		System.out.println("Example 1: Simple Unnamed Class");
		System.out.println("------------------------------");
		System.out.println("""
				// File: Hello.java (no class declaration needed)
				void main() {
				    System.out.println("Hello, World!");
				}
				
				// Compile: javac Hello.java
				// Run: java Hello
				// No --enable-preview flag needed!
				""");

		System.out.println("\nExample 2: Unnamed Class with Fields and Methods");
		System.out.println("------------------------------------------------");
		System.out.println("""
				String greeting = "Hello";
				int count = 5;
				
				void main() {
				    System.out.println(greeting + ", World!");
				    sayHello();
				    printCount();
				}
				
				void sayHello() {
				    System.out.println("This is a method in unnamed class");
				}
				
				void printCount() {
				    System.out.println("Count: " + count);
				}
				""");

		System.out.println("\nExample 3: Instance Main Method");
		System.out.println("--------------------------------");
		System.out.println("""
				// Instance main method (non-static)
				String name = "Java";
				
				void main() {
				    System.out.println("Hello from " + name);
				    // Can access instance fields and methods
				}
				
				// Static main still works
				public static void main(String[] args) {
				    System.out.println("Hello from static main!");
				}
				""");

		System.out.println("\nKey Features:");
		System.out.println("- No explicit class declaration needed");
		System.out.println("- Instance main methods (non-static)");
		System.out.println("- Simplified syntax for simple programs");
		System.out.println("- Useful for learning and scripting");
		System.out.println("- Compiler generates class automatically");
		System.out.println("- Finalized feature (no preview flag needed)");

		System.out.println("\nBenefits:");
		System.out.println("- Reduces boilerplate code");
		System.out.println("- Easier for beginners");
		System.out.println("- Good for simple scripts");
		System.out.println("- More intuitive for small programs");
		System.out.println("- Production-ready (finalized)");

		System.out.println("\nNote: This feature is finalized in Java 24.");
		System.out.println("No --enable-preview flag needed!");
	}
}

