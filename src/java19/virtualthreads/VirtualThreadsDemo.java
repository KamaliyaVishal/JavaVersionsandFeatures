package java19.virtualthreads;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Java 19 Virtual Threads (Preview) Demonstrates lightweight virtual threads
 */
public class VirtualThreadsDemo
{
	public static void main(String[] args)
	{

		// 1. Create virtual thread directly
		System.out.println("=== Virtual Thread Creation ===");
		Thread virtualThread = Thread.ofVirtual()
				.name("worker-", 0)
				.start(() -> {
					System.out.println("Running on virtual thread: " + Thread.currentThread().getName());
				});

		try
		{
			virtualThread.join();
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}

		// 2. Using virtual thread executor
		System.out.println("\n=== Virtual Thread Executor ===");
		try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor())
		{
			for (int i = 0; i < 5; i++)
			{
				final int taskId = i;
				executor.submit(() -> {
					System.out.println("Task " + taskId + " on virtual thread: " +
							Thread.currentThread().getName());
					try
					{
						Thread.sleep(100); // Simulate I/O operation
					}
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
					}
				});
			}
		}

		// 3. Builder pattern
		System.out.println("\n=== Builder Pattern ===");
		Thread.Builder builder = Thread.ofVirtual().name("task-", 0);
		Thread vt1 = builder.start(() -> System.out.println("Task 1"));
		Thread vt2 = builder.start(() -> System.out.println("Task 2"));

		try
		{
			vt1.join();
			vt2.join();
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}

		System.out.println("\nVirtual threads are lightweight and perfect for I/O-bound operations!");
	}
}

