package java20.virtualthreads;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

/**
 * Java 20 Virtual Threads (Second Preview)
 * Demonstrates lightweight virtual threads
 * 
 * NOTE: Requires --enable-preview flag for compilation and execution
 * Compile: javac --enable-preview --release 20 VirtualThreadsDemo.java
 * Run: java --enable-preview VirtualThreadsDemo
 */
public class VirtualThreadsDemo {
    public static void main(String[] args) {
        
        System.out.println("=== Java 20 Virtual Threads (Second Preview) ===\n");
        
        // 1. Create virtual thread directly
        System.out.println("1. Virtual Thread Creation");
        System.out.println("--------------------------");
        Thread virtualThread = Thread.ofVirtual()
                .name("worker-", 0)
                .start(() -> {
                    System.out.println("Running on virtual thread: " + Thread.currentThread().getName());
                    System.out.println("Is virtual: " + Thread.currentThread().isVirtual());
                });
        
        try {
            virtualThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 2. Using virtual thread executor
        System.out.println("\n2. Virtual Thread Executor");
        System.out.println("--------------------------");
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId + " on virtual thread: " +
                            Thread.currentThread().getName());
                    try {
                        Thread.sleep(100); // Simulate I/O operation
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
        
        // 3. Builder pattern
        System.out.println("\n3. Builder Pattern");
        System.out.println("------------------");
        Thread.Builder builder = Thread.ofVirtual().name("task-", 0);
        Thread vt1 = builder.start(() -> System.out.println("Task 1"));
        Thread vt2 = builder.start(() -> System.out.println("Task 2"));
        
        try {
            vt1.join();
            vt2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 4. High-throughput example
        System.out.println("\n4. High-Throughput Example");
        System.out.println("-------------------------");
        System.out.println("Creating 1000 virtual threads...");
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 1000).forEach(i -> {
                executor.submit(() -> {
                    try {
                        Thread.sleep(10); // Simulate I/O
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            });
        }
        System.out.println("Completed 1000 tasks with virtual threads!");
        
        // 5. Comparison with platform threads
        System.out.println("\n5. Virtual Threads vs Platform Threads");
        System.out.println("--------------------------------------");
        System.out.println("Virtual Threads:");
        System.out.println("  - Managed by JVM");
        System.out.println("  - Millions can be created");
        System.out.println("  - Perfect for I/O-bound operations");
        System.out.println("  - Blocking doesn't block OS thread");
        System.out.println("\nPlatform Threads:");
        System.out.println("  - Managed by OS");
        System.out.println("  - Limited (thousands)");
        System.out.println("  - Better for CPU-bound operations");
        
        System.out.println("\nVirtual threads are lightweight and perfect for I/O-bound operations!");
    }
}

