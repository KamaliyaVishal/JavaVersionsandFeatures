package java21.programs;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Practical example using Java 21 Virtual Threads
 */
public class VirtualThreadsExample {
    public static void main(String[] args) {
        
        System.out.println("=== Virtual Threads Example ===");
        
        // Simulate I/O-bound operations with virtual threads
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    // Simulate I/O operation
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Task " + taskId + " completed on " + 
                        Thread.currentThread().getName());
                });
            }
        }
        
        System.out.println("\nAll tasks completed!");
        System.out.println("Virtual threads enable handling thousands of concurrent I/O operations");
    }
}

