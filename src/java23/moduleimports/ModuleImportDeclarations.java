package java23.moduleimports;

/**
 * Java 23 Module Import Declarations (Preview) Demonstrates simplified syntax for importing modules
 * NOTE: Requires --enable-preview flag for compilation and execution Compile: javac --enable-preview --release 23 ModuleImportDeclarations.java Run: java --enable-preview ModuleImportDeclarations
 */
public class ModuleImportDeclarations
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 23 Module Import Declarations (Preview) ===\n");

		System.out.println("NOTE: This is a preview feature requiring --enable-preview flag.\n");

		System.out.println("Example: Module Import Declarations");
		System.out.println("----------------------------------");
		System.out.println("""
				// Current module syntax (Java 9+)
				module com.example.app {
				    requires java.base;
				    requires java.sql;
				    requires java.xml;
				    exports com.example.app.api;
				}
				
				// New import syntax (Java 23 Preview)
				import module java.base;
				import module java.sql;
				import module java.xml;
				
				module com.example.app {
				    // Modules imported above are available
				    exports com.example.app.api;
				}
				""");

		System.out.println("\nKey Features:");
		System.out.println("- Simplified module syntax");
		System.out.println("- Cleaner module descriptors");
		System.out.println("- Easier to read and maintain");
		System.out.println("- Reduces boilerplate in module-info.java");

		System.out.println("\nBenefits:");
		System.out.println("- Cleaner module descriptors");
		System.out.println("- Better readability");
		System.out.println("- Easier module management");
		System.out.println("- Less repetitive code");

		System.out.println("\nUse Cases:");
		System.out.println("- Large applications with many module dependencies");
		System.out.println("- Modular libraries");
		System.out.println("- Applications using multiple Java modules");

		System.out.println("\nNote: This is a preview feature in Java 23.");
		System.out.println("Requires --enable-preview flag for compilation and execution.");
	}
}

