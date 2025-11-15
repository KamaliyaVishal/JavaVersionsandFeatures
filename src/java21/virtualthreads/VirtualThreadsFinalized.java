package java21.virtualthreads;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Java 21 Virtual Threads (Finalized) Demonstrates standardized virtual threads
 */
public class VirtualThreadsFinalized
{
	public static void main(String[] args)
	{

		// Virtual threads are now standard (no longer preview)
		System.out.println("=== Virtual Threads (Finalized) ===");

		// Create virtual thread
		Thread virtualThread = Thread.ofVirtual()
				.name("worker-", 0)
				.start(() -> {
					System.out.println("Virtual thread: " + Thread.currentThread().getName());
				});

		try
		{
			virtualThread.join();
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}

		// Virtual thread executor
		try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor())
		{
			for (int i = 0; i < 10; i++)
			{
				final int taskId = i;
				executor.submit(() -> {
					System.out.println("Task " + taskId + " on " + Thread.currentThread().getName());
					try
					{
						Thread.sleep(50); // Simulate I/O
					}
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
					}
				});
			}
		}

		System.out.println("\nVirtual threads enable high-throughput concurrent applications!");
	}
}

