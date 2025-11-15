package java17.sealedclasses;

/**
 * Java 17 Sealed Classes (Finalized)
 * Demonstrates sealed classes and interfaces - now a standard feature
 * 
 * NOTE: Sealed classes are finalized in Java 17 (no preview flag needed)
 */
public class SealedClassesDemo {
    public static void main(String[] args) {
        
        System.out.println("=== Java 17 Sealed Classes (Finalized) ===\n");
        
        // 1. Sealed class hierarchy
        System.out.println("1. Sealed Class Hierarchy");
        System.out.println("-------------------------");
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(10.0, 20.0);
        Shape triangle = new Triangle(8.0, 6.0);
        
        System.out.println("Circle area: " + circle.area());
        System.out.println("Rectangle area: " + rectangle.area());
        System.out.println("Triangle area: " + triangle.area());
        
        // 2. Pattern matching with sealed classes (exhaustive)
        System.out.println("\n2. Pattern Matching with Sealed Classes");
        System.out.println("----------------------------------------");
        processShape(circle);
        processShape(rectangle);
        processShape(triangle);
        
        // 3. Sealed interfaces
        System.out.println("\n3. Sealed Interfaces");
        System.out.println("-------------------");
        Expression constant = new Constant(5.0);
        Expression variable = new Variable("x");
        Expression add = new Add(constant, variable);
        
        System.out.println("Constant: " + constant.evaluate());
        System.out.println("Variable: " + variable.evaluate());
        System.out.println("Add: " + add.evaluate());
        
        // 4. Non-sealed classes
        System.out.println("\n4. Non-Sealed Classes");
        System.out.println("--------------------");
        Animal dog = new Dog("Buddy");
        Animal cat = new Cat("Whiskers");
        Animal bird = new Parrot("Polly"); // Parrot extends Bird (non-sealed)
        
        processAnimal(dog);
        processAnimal(cat);
        processAnimal(bird);
    }
    
    private static void processShape(Shape shape) {
        // Pattern matching with sealed classes
        // The compiler knows all possible types, enabling exhaustive matching
        String result = switch (shape) {
            case Circle c -> "Circle with radius " + c.radius();
            case Rectangle r -> "Rectangle " + r.width() + "x" + r.height();
            case Triangle t -> "Triangle with base " + t.base() + " and height " + t.height();
        };
        
        System.out.println(result);
    }
    
    private static void processAnimal(Animal animal) {
        // Pattern matching with sealed classes and non-sealed subclasses
        String result = switch (animal) {
            case Dog dog -> "Dog: " + dog.name() + " barks";
            case Cat cat -> "Cat: " + cat.name() + " meows";
            case Bird bird -> "Bird: " + bird.name() + " chirps";
        };
        
        System.out.println(result);
    }
    
    // Sealed class - only permits specific classes to extend
    public sealed static abstract class Shape permits Circle, Rectangle, Triangle {
        public abstract double area();
    }
    
    // Final permitted class
    public static final class Circle extends Shape {
        private final double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        public double radius() {
            return radius;
        }
        
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }
    
    // Final permitted class
    public static final class Rectangle extends Shape {
        private final double width, height;
        
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        public double width() {
            return width;
        }
        
        public double height() {
            return height;
        }
        
        @Override
        public double area() {
            return width * height;
        }
    }
    
    // Final permitted class
    public static final class Triangle extends Shape {
        private final double base, height;
        
        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }
        
        public double base() {
            return base;
        }
        
        public double height() {
            return height;
        }
        
        @Override
        public double area() {
            return 0.5 * base * height;
        }
    }
    
    // Sealed interface
    public sealed interface Expression permits Constant, Variable, Add, Multiply {
        double evaluate();
    }
    
    // Record implementing sealed interface
    public record Constant(double value) implements Expression {
        @Override
        public double evaluate() {
            return value;
        }
    }
    
    public record Variable(String name) implements Expression {
        @Override
        public double evaluate() {
            // Simplified - would normally look up in context
            return 1.0;
        }
    }
    
    public record Add(Expression left, Expression right) implements Expression {
        @Override
        public double evaluate() {
            return left.evaluate() + right.evaluate();
        }
    }
    
    public record Multiply(Expression left, Expression right) implements Expression {
        @Override
        public double evaluate() {
            return left.evaluate() * right.evaluate();
        }
    }
    
    // Sealed class with non-sealed subclass
    public sealed static abstract class Animal permits Dog, Cat, Bird {
        private final String name;
        
        protected Animal(String name) {
            this.name = name;
        }
        
        public String name() {
            return name;
        }
    }
    
    public static final class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
    }
    
    public static final class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
    }
    
    // Non-sealed - can be extended by anyone
    public static non-sealed class Bird extends Animal {
        public Bird(String name) {
            super(name);
        }
    }
    
    // Parrot extends Bird (allowed because Bird is non-sealed)
    public static class Parrot extends Bird {
        public Parrot(String name) {
            super(name);
        }
    }
}

