package java14.switchexpressions;

/**
 * Java 14 Switch Expressions (Finalized)
 * 
 * Switch expressions are now a standard feature (no longer preview).
 * No --enable-preview flag needed!
 */
public class SwitchExpressions {
    public static void main(String[] args) {
        
        System.out.println("=== Switch Expressions (Finalized) ===\n");
        
        // 1. Basic switch expression with arrow syntax
        System.out.println("--- Basic Switch Expression (Arrow Syntax) ---");
        String day = "MONDAY";
        int numLetters = switch (day) {
            case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
            case "TUESDAY" -> 7;
            case "WEDNESDAY", "THURSDAY", "SATURDAY" -> 9;
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        };
        System.out.println("Day: " + day + ", Letters: " + numLetters);
        
        // 2. Block syntax with yield
        System.out.println("\n--- Block Syntax with yield ---");
        int value = 3;
        int result = switch (value) {
            case 1 -> 10;
            case 2 -> 20;
            case 3 -> {
                System.out.println("Processing case 3");
                yield 30;
            }
            default -> 0;
        };
        System.out.println("Result: " + result);
        
        // 3. Switch expression with enum
        System.out.println("\n--- Switch Expression with Enum ---");
        Day dayEnum = Day.MONDAY;
        String type = switch (dayEnum) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
        };
        System.out.println("Day: " + dayEnum + ", Type: " + type);
        
        // 4. Returning different types
        System.out.println("\n--- Returning Different Types ---");
        Object result2 = switch (day) {
            case "MONDAY" -> 1;
            case "TUESDAY" -> "Two";
            case "WEDNESDAY" -> true;
            default -> 0;
        };
        System.out.println("Result: " + result2 + " (type: " + result2.getClass().getSimpleName() + ")");
        
        // 5. Practical example: Day classification
        System.out.println("\n--- Practical Example: Day Classification ---");
        String[] days = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        
        for (String d : days) {
            String classification = switch (d) {
                case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
                case "SATURDAY", "SUNDAY" -> "Weekend";
                default -> "Unknown";
            };
            System.out.println(d + " is a " + classification);
        }
        
        // 6. Comparison with traditional switch statement
        System.out.println("\n--- Comparison: Traditional vs Switch Expression ---");
        
        // Traditional switch statement
        int traditionalResult;
        switch (day) {
            case "MONDAY":
            case "FRIDAY":
                traditionalResult = 6;
                break;
            case "TUESDAY":
                traditionalResult = 7;
                break;
            default:
                traditionalResult = -1;
        }
        System.out.println("Traditional switch result: " + traditionalResult);
        
        // Switch expression (more concise)
        int expressionResult = switch (day) {
            case "MONDAY", "FRIDAY" -> 6;
            case "TUESDAY" -> 7;
            default -> -1;
        };
        System.out.println("Switch expression result: " + expressionResult);
        
        // 7. Exhaustive switch (no default needed for enums)
        System.out.println("\n--- Exhaustive Switch (Enum) ---");
        Day allDays = Day.SUNDAY;
        String message = switch (allDays) {
            case MONDAY -> "Start of week";
            case TUESDAY, WEDNESDAY, THURSDAY -> "Mid-week";
            case FRIDAY -> "End of week";
            case SATURDAY, SUNDAY -> "Weekend";
        };
        System.out.println("Message: " + message);
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. More concise syntax");
        System.out.println("2. No fall-through (eliminates break bugs)");
        System.out.println("3. Can return values");
        System.out.println("4. Exhaustive (must handle all cases or have default)");
        System.out.println("5. Better readability");
        System.out.println("6. Standard feature (no preview flag needed)");
    }
    
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}

