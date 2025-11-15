package java12.switchexpressions;

/**
 * Java 12 Switch Expressions (Preview)
 * Demonstrates switch expressions with arrow syntax
 */
public class SwitchExpressions {
    public static void main(String[] args) {
        
        String day = "MONDAY";
        
        // 1. Switch as expression (preview)
        System.out.println("=== Switch Expression ===");
        int numLetters = switch (day) {
            case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
            case "TUESDAY" -> 7;
            case "WEDNESDAY", "THURSDAY", "SATURDAY" -> 9;
            default -> -1;
        };
        
        System.out.println("Day: " + day + ", Letters: " + numLetters);
        
        // 2. Arrow syntax
        System.out.println("\n=== Arrow Syntax ===");
        String type = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Unknown";
        };
        
        System.out.println("Type: " + type);
        
        // 3. yield keyword for complex cases
        System.out.println("\n=== yield Keyword ===");
        int result = switch (day) {
            case "MONDAY", "FRIDAY" -> {
                System.out.println("Weekday start/end");
                yield 6;
            }
            case "WEDNESDAY" -> 9;
            default -> -1;
        };
        
        System.out.println("Result: " + result);
        
        // 4. Enum example
        System.out.println("\n=== Enum Example ===");
        Day dayEnum = Day.MONDAY;
        
        String message = switch (dayEnum) {
            case MONDAY -> "Start of week";
            case FRIDAY -> "End of week";
            case SATURDAY, SUNDAY -> "Weekend";
            default -> "Mid-week";
        };
        
        System.out.println("Message: " + message);
    }
    
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}

