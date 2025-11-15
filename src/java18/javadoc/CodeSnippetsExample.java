package java18.javadoc;

/**
 * Java 18 Code Snippets in Java API Documentation
 * Demonstrates the @snippet tag in Javadoc
 * 
 * NOTE: The @snippet tag is used in Javadoc comments, not in regular code
 * This file shows examples of how to use @snippet in your documentation
 */
public class CodeSnippetsExample {
    
    /**
     * Calculates the sum of two numbers.
     * 
     * <p>Example usage:</p>
     * {@snippet :
     * Calculator calc = new Calculator();
     * int result = calc.add(5, 3);
     * System.out.println(result); // Output: 8
     * }
     * 
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }
    
    /**
     * Checks if a string is empty or null.
     * 
     * <p>Example:</p>
     * {@snippet :
     * String str = "Hello";
     * if (isEmpty(str)) {
     *     System.out.println("String is empty");
     * }
     * }
     * 
     * @param str the string to check
     * @return true if string is null or empty
     */
    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
    
    /**
     * External snippet file example.
     * 
     * <p>You can also reference external snippet files:</p>
     * {@snippet file="examples.java" region="calculator"}
     * 
     * The external file would contain:
     * <pre>
     * // @start region="calculator"
     * Calculator calc = new Calculator();
     * int result = calc.add(5, 3);
     * // @end
     * </pre>
     * 
     * @param value the value to process
     * @return processed value
     */
    public int process(int value) {
        return value * 2;
    }
    
    /**
     * Highlights specific parts of code.
     * 
     * <p>Example with highlighting:</p>
     * {@snippet :
     * String name = "Java";  // @highlight substring="name"
     * int version = 18;      // @highlight substring="version"
     * System.out.println(name + " " + version);
     * }
     * 
     * @param name the name
     * @param version the version
     */
    public void display(String name, int version) {
        System.out.println(name + " " + version);
    }
    
    /**
     * Shows how to use attributes in snippets.
     * 
     * <p>Example with attributes:</p>
     * {@snippet lang="java" :
     * public class Example {
     *     public void method() {
     *         System.out.println("Hello");
     *     }
     * }
     * }
     * 
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}

/**
 * Benefits of @snippet tag:
 * 
 * 1. Syntax highlighting in generated documentation
 * 2. Compile-time validation of code snippets
 * 3. Better integration with IDE
 * 4. Embedded examples in API documentation
 * 5. External snippet files for complex examples
 * 6. Highlighting specific parts of code
 * 7. Language-specific formatting
 * 
 * Usage:
 * - Inline snippets: {@snippet : code here }
 * - External snippets: {@snippet file="file.java" region="regionName"}
 * - With attributes: {@snippet lang="java" : code here }
 * - With highlighting: // @highlight substring="variableName"
 */

