package java15.sealedclasses;

public class SealedClassesDemo
{
	public static void main(String[] args)
	{

		// 1. Sealed class hierarchy
		System.out.println("=== Sealed Class Hierarchy ===");
		Shape circle = new Circle(5.0);
		Shape rectangle = new Rectangle(10.0, 20.0);
		Shape triangle = new Triangle(8.0, 6.0);

		System.out.println("Circle area: " + circle.area());
		System.out.println("Rectangle area: " + rectangle.area());
		System.out.println("Triangle area: " + triangle.area());

		// 2. Pattern matching with sealed classes
		System.out.println("\n=== Pattern Matching ===");
		processShape(circle);
		processShape(rectangle);
		processShape(triangle);

		// 3. Sealed interfaces
		System.out.println("\n=== Sealed Interfaces ===");
		Expression constant = new Constant(5.0);
		Expression variable = new Variable("x");
		Expression add = new Add(constant, variable);

		System.out.println("Constant: " + constant.evaluate());
		System.out.println("Variable: " + variable.evaluate());
		System.out.println("Add: " + add.evaluate());
	}

	private static void processShape(Shape shape)
	{
		// Pattern matching with sealed classes (preview feature - use --enable-preview)
		/* For compilation without preview, using traditional instanceof checks
		String result;
		if (shape instanceof Circle) {
			Circle c = (Circle) shape;
			result = "Circle with radius " + c.radius();
		} else if (shape instanceof Rectangle) {
			Rectangle r = (Rectangle) shape;
			result = "Rectangle " + r.width() + "x" + r.height();
		} else if (shape instanceof Triangle) {
			Triangle t = (Triangle) shape;
			result = "Triangle with base " + t.base() + " and height " + t.height();
		} else {
			result = "Unknown shape";
		}*/

		// Note: With --enable-preview, you can use:
		String result = switch (shape)
		{
			case Circle c -> "Circle with radius " + c.radius();
			case Rectangle r -> "Rectangle " + r.width() + "x" + r.height();
			case Triangle t -> "Triangle with base " + t.base() + " and height " + t.height();
		};

		System.out.println(result);
	}

	// Sealed class
	public sealed static abstract class Shape permits Circle, Rectangle, Triangle
	{
		public abstract double area();
	}

	// Final permitted class
	public static final class Circle extends Shape
	{
		private final double radius;

		public Circle(double radius)
		{
			this.radius = radius;
		}

		public double radius()
		{
			return radius;
		}

		@Override
		public double area()
		{
			return Math.PI * radius * radius;
		}
	}

	// Final permitted class
	public static final class Rectangle extends Shape
	{
		private final double width, height;

		public Rectangle(double width, double height)
		{
			this.width = width;
			this.height = height;
		}

		public double width()
		{
			return width;
		}

		public double height()
		{
			return height;
		}

		@Override
		public double area()
		{
			return width * height;
		}
	}

	// Final permitted class
	public static final class Triangle extends Shape
	{
		private final double base, height;

		public Triangle(double base, double height)
		{
			this.base = base;
			this.height = height;
		}

		public double base()
		{
			return base;
		}

		public double height()
		{
			return height;
		}

		@Override
		public double area()
		{
			return 0.5 * base * height;
		}
	}

	// Sealed interface
	public sealed interface Expression permits Constant, Variable, Add, Multiply
	{
		double evaluate();
	}

	// Record implementing sealed interface
	public record Constant(double value) implements Expression
	{
		@Override
		public double evaluate()
		{
			return value;
		}
	}

	public record Variable(String name) implements Expression
	{
		@Override
		public double evaluate()
		{
			// Simplified - would normally look up in context
			return 1.0;
		}
	}

	public record Add(Expression left, Expression right) implements Expression
	{
		@Override
		public double evaluate()
		{
			return left.evaluate() + right.evaluate();
		}
	}

	public record Multiply(Expression left, Expression right) implements Expression
	{
		@Override
		public double evaluate()
		{
			return left.evaluate() * right.evaluate();
		}
	}
}

