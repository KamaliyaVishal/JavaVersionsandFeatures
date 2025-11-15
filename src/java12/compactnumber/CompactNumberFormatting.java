package java12.compactnumber;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Java 12 Compact Number Formatting
 * 
 * Demonstrates compact number formatting for human-readable number display.
 * Supports both SHORT (1K, 1M) and LONG (1 thousand, 1 million) formats.
 */
public class CompactNumberFormatting {
    public static void main(String[] args) {
        
        System.out.println("=== Compact Number Formatting ===");
        
        // 1. Short Format (US Locale)
        System.out.println("\n--- Short Format (US) ---");
        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(
            Locale.US, NumberFormat.Style.SHORT);
        
        System.out.println("1000: " + shortFormat.format(1000));        // 1K
        System.out.println("1500: " + shortFormat.format(1500));          // 2K (rounded)
        System.out.println("1000000: " + shortFormat.format(1000000)); // 1M
        System.out.println("1500000: " + shortFormat.format(1500000));   // 2M
        System.out.println("1000000000: " + shortFormat.format(1000000000)); // 1B
        
        // With fraction digits
        shortFormat.setMaximumFractionDigits(2);
        System.out.println("\nWith 2 fraction digits:");
        System.out.println("1500: " + shortFormat.format(1500));      // 1.5K
        System.out.println("1234567: " + shortFormat.format(1234567));  // 1.23M
        
        // 2. Long Format (US Locale)
        System.out.println("\n--- Long Format (US) ---");
        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(
            Locale.US, NumberFormat.Style.LONG);
        
        System.out.println("1000: " + longFormat.format(1000));        // 1 thousand
        System.out.println("1000000: " + longFormat.format(1000000));   // 1 million
        System.out.println("1000000000: " + longFormat.format(1000000000)); // 1 billion
        
        // 3. Different Locales
        System.out.println("\n--- Different Locales ---");
        
        // UK Locale
        NumberFormat ukFormat = NumberFormat.getCompactNumberInstance(
            Locale.UK, NumberFormat.Style.SHORT);
        System.out.println("UK - 1000: " + ukFormat.format(1000));
        System.out.println("UK - 1000000: " + ukFormat.format(1000000));
        
        // German Locale
        NumberFormat deFormat = NumberFormat.getCompactNumberInstance(
            Locale.GERMANY, NumberFormat.Style.SHORT);
        System.out.println("DE - 1000: " + deFormat.format(1000));
        System.out.println("DE - 1000000: " + deFormat.format(1000000));
        
        // 4. Parsing Compact Numbers
        System.out.println("\n--- Parsing Compact Numbers ---");
        try {
            NumberFormat parser = NumberFormat.getCompactNumberInstance(
                Locale.US, NumberFormat.Style.SHORT);
            
            Number parsed1 = parser.parse("1K");
            System.out.println("Parsed '1K': " + parsed1);
            
            Number parsed2 = parser.parse("1.5M");
            System.out.println("Parsed '1.5M': " + parsed2);
            
        } catch (Exception e) {
            System.err.println("Parse error: " + e.getMessage());
        }
        
        // 5. Practical Examples
        System.out.println("\n=== Practical Examples ===");
        
        // Social media likes/followers
        System.out.println("\n--- Social Media Formatting ---");
        NumberFormat socialFormat = NumberFormat.getCompactNumberInstance(
            Locale.US, NumberFormat.Style.SHORT);
        socialFormat.setMaximumFractionDigits(1);
        
        System.out.println("Likes: " + socialFormat.format(1234));        // 1.2K
        System.out.println("Followers: " + socialFormat.format(567890));  // 567.9K
        System.out.println("Views: " + socialFormat.format(12345678));    // 12.3M
        
        // File sizes
        System.out.println("\n--- File Size Formatting ---");
        NumberFormat sizeFormat = NumberFormat.getCompactNumberInstance(
            Locale.US, NumberFormat.Style.SHORT);
        sizeFormat.setMaximumFractionDigits(2);
        
        long[] sizes = {1024, 1024 * 1024, 1024L * 1024 * 1024, 1024L * 1024 * 1024 * 1024};
        String[] units = {"bytes", "KB", "MB", "GB"};
        
        for (int i = 0; i < sizes.length; i++) {
            System.out.println("Size: " + sizeFormat.format(sizes[i]) + " " + units[i]);
        }
        
        // Population formatting
        System.out.println("\n--- Population Formatting ---");
        NumberFormat popFormat = NumberFormat.getCompactNumberInstance(
            Locale.US, NumberFormat.Style.LONG);
        
        System.out.println("City population: " + popFormat.format(850000));      // 850 thousand
        System.out.println("Country population: " + popFormat.format(1400000000)); // 1.4 billion
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. Human-readable number formatting");
        System.out.println("2. Locale-aware formatting");
        System.out.println("3. Short and long format options");
        System.out.println("4. Useful for UI displays");
        System.out.println("5. Supports parsing");
        
        System.out.println("\n=== Comparison: Before vs After ===");
        System.out.println("Before: 1,234,567 (hard to read at a glance)");
        System.out.println("After: 1.23M (compact and readable)");
    }
}

