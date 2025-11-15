package java21.recordpatterns;

/**
 * Java 21 Record Patterns (Finalized)
 * Demonstrates pattern matching with records
 */
public class RecordPatterns {
    public static void main(String[] args) {
        
        // 1. Basic record pattern
        System.out.println("=== Basic Record Pattern ===");
        Object obj = new Point(5, 10);
        
        if (obj instanceof Point(int x, int y)) {
            System.out.println("X: " + x + ", Y: " + y);
        }
        
        // 2. Nested patterns
        System.out.println("\n=== Nested Patterns ===");
        Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 10));
        
        if (rect instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
            int width = x2 - x1;
            int height = y2 - y1;
            System.out.println("Width: " + width + ", Height: " + height);
        }
        
        // 3. In switch
        System.out.println("\n=== Record Patterns in Switch ===");
        String result = switch (obj) {
            case Point(int x, int y) when x > 0 && y > 0 -> 
                "Positive quadrant: (" + x + ", " + y + ")";
            case Point(int x, int y) -> 
                "Other quadrant: (" + x + ", " + y + ")";
            default -> "Not a point";
        };
        
        System.out.println(result);
        
        // 4. Practical example
        System.out.println("\n=== Practical Example ===");
        Shape circle = new Circle(new Point(0, 0), 5.0);
        processShape(circle);
    }
    
    private static void processShape(Shape shape) {
        switch (shape) {
            case Circle(Point(int x, int y), double radius) -> {
                System.out.println("Circle at (" + x + ", " + y + ") with radius " + radius);
            }
            case Rectangle(Point topLeft, Point bottomRight) -> {
                System.out.println("Rectangle from " + topLeft + " to " + bottomRight);
            }
        }
    }
    
    record Point(int x, int y) {}
    record Rectangle(Point topLeft, Point bottomRight) implements Shape
	{}
    sealed interface Shape permits Circle, Rectangle {}
    record Circle(Point center, double radius) implements Shape {}
}

