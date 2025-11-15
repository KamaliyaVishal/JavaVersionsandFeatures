package java14.programs;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Practical examples using Java 14 Records
 */
public class RecordsExamples
{
	public static void main(String[] args)
	{

		// Example 1: Create records
		System.out.println("=== Create Records ===");
		List<Person> people = List.of(
				new Person("Alice", 25),
				new Person("Bob", 30),
				new Person("Charlie", 35)
		);

		people.forEach(person ->
				System.out.println(person.name() + " (" + person.age() + ") - Adult: " + person.isAdult()));

		// Example 2: Filter and process
		System.out.println("\n=== Filter Records ===");
		List<String> adultNames = people.stream()
				.filter(Person::isAdult)
				.map(Person::name)
				.collect(Collectors.toList());

		System.out.println("Adult names: " + adultNames);

		// Example 3: Records as DTOs
		System.out.println("\n=== Records as DTOs ===");
		User user = new User("user123", "john@example.com", "ACTIVE");
		System.out.println("User: " + user);
		System.out.println("Is active: " + user.isActive());
	}

	public record Person(String name, int age)
	{
		public boolean isAdult()
		{
			return age >= 18;
		}
	}

	public record User(String id, String email, String status)
	{
		public boolean isActive()
		{
			return "ACTIVE".equals(status);
		}
	}
}

