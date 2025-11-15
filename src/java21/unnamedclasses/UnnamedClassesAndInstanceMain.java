package java21.unnamedclasses;

/**
 * Java 21 Unnamed Classes and Instance Main Methods (Preview) Demonstrates simplified class structure for simple programs
 * NOTE: Requires --enable-preview flag for compilation and execution Compile: javac --enable-preview --release 21 UnnamedClassesAndInstanceMain.java Run: java --enable-preview UnnamedClassesAndInstanceMain
 */
public class UnnamedClassesAndInstanceMain
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 21 Unnamed Classes and Instance Main Methods (Preview) ===\n");

		System.out.println("Example 1: Unnamed Class");
		System.out.println("------------------------");
		System.out.println("""
				// File: Hello.java (no class declaration needed)
				void main() {
				    System.out.println("Hello, World!");
				}
				
				// Compile: javac --enable-preview --release 21 Hello.java
				// Run: java --enable-preview Hello
				""");

		System.out.println("\nExample 2: Instance Main Method");
		System.out.println("-------------------------------");
		System.out.println("""
				// Instance main method (non-static)
				void main() {
				    System.out.println("Hello from instance main!");
				    // Can access instance fields and methods
				}
				
				// Static main still works
				public static void main(String[] args) {
				    System.out.println("Hello from static main!");
				}
				""");

		System.out.println("\nExample 3: With Fields and Methods");
		System.out.println("----------------------------------");
		System.out.println("""
				String greeting = "Hello";
				
				void main() {
				    System.out.println(greeting + ", World!");
				    sayHello();
				}
				
				void sayHello() {
				    System.out.println("This is a method in unnamed class");
				}
				""");

		System.out.println("\nKey Features:");
		System.out.println("- No explicit class declaration needed");
		System.out.println("- Instance main methods (non-static)");
		System.out.println("- Simplified syntax for simple programs");
		System.out.println("- Useful for learning and scripting");
		System.out.println("- Compiler generates class automatically");

		System.out.println("\nBenefits:");
		System.out.println("- Reduces boilerplate code");
		System.out.println("- Easier for beginners");
		System.out.println("- Good for simple scripts");
		System.out.println("- More intuitive for small programs");

		System.out.println("\nNote: This is a preview feature in Java 21.");
		System.out.println("Requires --enable-preview flag for compilation and execution.");
	}
}

