package java24.virtualthreads;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CountDownLatch;

/**
 * Java 24 Improved Virtual Threads (JEP 491) Demonstrates virtual threads with improved synchronization without pinning
 * NOTE: This feature improves virtual thread performance
 */
public class VirtualThreadsDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("=== Java 24 Improved Virtual Threads (JEP 491) ===\n");

		System.out.println("Overview:");
		System.out.println("--------");
		System.out.println("Java 24 improves virtual threads by allowing synchronization without pinning,");
		System.out.println("enabling better scalability and performance.\n");

		System.out.println("Example 1: Basic Virtual Thread");
		System.out.println("-------------------------------");
		Thread.startVirtualThread(() -> {
			System.out.println("Virtual thread running: " + Thread.currentThread().getName());
		});

		Thread.sleep(100); // Wait for thread to complete

		System.out.println("\nExample 2: Synchronization Without Pinning");
		System.out.println("------------------------------------------");
		Object lock = new Object();
		CountDownLatch latch = new CountDownLatch(3);

		for (int i = 0; i < 3; i++)
		{
			final int id = i;
			Thread.startVirtualThread(() -> {
				synchronized (lock)
				{
					System.out.println("Virtual thread " + id + " synchronized without pinning");
					try
					{
						Thread.sleep(50);
					}
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
					}
				}
				latch.countDown();
			});
		}

		latch.await();

		System.out.println("\nExample 3: Virtual Thread Executor");
		System.out.println("-----------------------------------");
		try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor())
		{
			for (int i = 0; i < 5; i++)
			{
				final int taskId = i;
				executor.submit(() -> {
					System.out.println("Task " + taskId + " running in virtual thread: " + Thread.currentThread().getName());
				});
			}
		}

		System.out.println("\nExample 4: High Concurrency");
		System.out.println("----------------------------");
		CountDownLatch highConcurrencyLatch = new CountDownLatch(100);
		for (int i = 0; i < 100; i++)
		{
			final int id = i;
			Thread.startVirtualThread(() -> {
				synchronized (VirtualThreadsDemo.class)
				{
					// Synchronization without pinning
					System.out.println("High concurrency task " + id);
				}
				highConcurrencyLatch.countDown();
			});
		}

		highConcurrencyLatch.await();

		System.out.println("\nKey Features:");
		System.out.println("- Synchronization without pinning");
		System.out.println("- Better scalability");
		System.out.println("- Improved performance");
		System.out.println("- Efficient resource usage");
		System.out.println("- Millions of virtual threads possible");

		System.out.println("\nBenefits:");
		System.out.println("- Better performance for concurrent operations");
		System.out.println("- No platform thread pinning during synchronization");
		System.out.println("- More efficient resource utilization");
		System.out.println("- Improved scalability");
		System.out.println("- Better for I/O-bound operations");

		System.out.println("\nUse Cases:");
		System.out.println("- High-concurrency applications");
		System.out.println("- I/O-bound operations");
		System.out.println("- Microservices");
		System.out.println("- Web servers");
		System.out.println("- Asynchronous processing");

		System.out.println("\nNote: Virtual threads were finalized in Java 21.");
		System.out.println("Java 24 improves them with better synchronization (JEP 491).");
	}
}

