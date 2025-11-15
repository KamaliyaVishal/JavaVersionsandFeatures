package java11.strings;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Java 11 String Methods Enhancements
 * Demonstrates isBlank(), lines(), strip(), repeat()
 */
public class StringMethods {
    public static void main(String[] args) {
        
        // 1. isBlank() - Checks if string is blank (whitespace only)
        System.out.println("=== isBlank() Examples ===");
        String str1 = "   ";
        System.out.println("'   '.isBlank(): " + str1.isBlank()); // true
        System.out.println("'   '.isEmpty(): " + str1.isEmpty()); // false
        
        String str2 = "";
        System.out.println("''.isBlank(): " + str2.isBlank()); // true
        System.out.println("''.isEmpty(): " + str2.isEmpty()); // true
        
        String str3 = "Hello";
        System.out.println("'Hello'.isBlank(): " + str3.isBlank()); // false
        
        // 2. lines() - Returns stream of lines
        System.out.println("\n=== lines() Examples ===");
        String text = "Line 1\nLine 2\nLine 3\r\nLine 4";
        System.out.println("Original text:\n" + text);
        
        long lineCount = text.lines().count();
        System.out.println("Line count: " + lineCount);
        
        text.lines().forEach(line -> System.out.println("> " + line));
        
        // Filter non-empty lines
        System.out.println("\nNon-empty lines:");
        text.lines()
            .filter(line -> !line.isBlank())
            .forEach(System.out::println);
        
        // 3. strip(), stripLeading(), stripTrailing()
        System.out.println("\n=== strip() Examples ===");
        String str = "  Hello World  ";
        System.out.println("Original: '" + str + "'");
        
        System.out.println("strip(): '" + str.strip() + "'"); // 'Hello World'
        System.out.println("stripLeading(): '" + str.stripLeading() + "'"); // 'Hello World  '
        System.out.println("stripTrailing(): '" + str.stripTrailing() + "'"); // '  Hello World'
        
        // Difference from trim() - Unicode aware
        String unicodeStr = "\u2000Hello\u2000";
        System.out.println("\nUnicode whitespace:");
        System.out.println("trim().equals('Hello'): " + unicodeStr.trim().equals("Hello")); // false
        System.out.println("strip().equals('Hello'): " + unicodeStr.strip().equals("Hello")); // true
        
        // 4. repeat() - Repeats string n times
        System.out.println("\n=== repeat() Examples ===");
        String hello = "Hello";
        System.out.println("repeat(3): " + hello.repeat(3)); // HelloHelloHello
        
        String equals = "=";
        System.out.println("repeat(10): " + equals.repeat(10)); // ==========
        
        String separator = "-".repeat(20);
        System.out.println("\nSeparator: " + separator);
        
        // Practical examples
        System.out.println("\n=== Practical Examples ===");
        String userInput = "  ";
        if (userInput.isBlank()) {
            System.out.println("User input is blank");
        }
        
        String multiLineText = "First line\nSecond line\nThird line";
        String joined = multiLineText.lines()
            .map(line -> "> " + line)
            .collect(Collectors.joining("\n"));
        System.out.println("\nFormatted lines:\n" + joined);
        
        String border = "=".repeat(30);
        System.out.println("\n" + border);
        System.out.println("  Formatted Output");
        System.out.println(border);
    }
}

