package java17.random;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.IntStream;

/**
 * Java 17 Enhanced Pseudo-Random Number Generators
 * Demonstrates the new RandomGenerator API with multiple algorithms
 */
public class RandomGeneratorDemo {
    public static void main(String[] args) {
        
        System.out.println("=== Java 17 Enhanced Pseudo-Random Number Generators ===\n");
        
        // 1. Default random generator
        System.out.println("1. Default Random Generator");
        System.out.println("---------------------------");
        RandomGenerator rng = RandomGeneratorFactory.getDefault().create();
        System.out.println("Algorithm: " + RandomGeneratorFactory.getDefault().name());
        
        for (int i = 0; i < 5; i++) {
            System.out.println("Random int (0-100): " + rng.nextInt(100));
        }
        
        // 2. Specific algorithms
        System.out.println("\n2. Specific Algorithms");
        System.out.println("---------------------");
        
        // L32X64MixRandom - Fast, good quality
        RandomGenerator lcg = RandomGeneratorFactory.of("L32X64MixRandom").create();
        System.out.println("L32X64MixRandom:");
        for (int i = 0; i < 3; i++) {
            System.out.println("  Random: " + lcg.nextLong());
        }
        
        // L64X128MixRandom - Very fast, excellent quality
        RandomGenerator lcg128 = RandomGeneratorFactory.of("L64X128MixRandom").create();
        System.out.println("\nL64X128MixRandom:");
        for (int i = 0; i < 3; i++) {
            System.out.println("  Random: " + lcg128.nextLong());
        }
        
        // Xoshiro256PlusPlus - Fast, good quality
        RandomGenerator xoshiro = RandomGeneratorFactory.of("Xoshiro256PlusPlus").create();
        System.out.println("\nXoshiro256PlusPlus:");
        for (int i = 0; i < 3; i++) {
            System.out.println("  Random: " + xoshiro.nextLong());
        }
        
        // 3. List all available algorithms
        System.out.println("\n3. Available Algorithms");
        System.out.println("---------------------");
        RandomGeneratorFactory.all()
            .map(f -> f.name() + " (" + f.group() + ")")
            .sorted()
            .forEach(System.out::println);
        
        // 4. Splittable for parallel streams
        System.out.println("\n4. Splittable Random for Parallel Streams");
        System.out.println("------------------------------------------");
        RandomGenerator splittable = RandomGeneratorFactory.of("SplittableRandom").create();
        
        // Generate random numbers in parallel
        IntStream parallelStream = IntStream.range(0, 10)
            .parallel()
            .map(i -> splittable.nextInt(100));
        
        System.out.println("Parallel random numbers:");
        parallelStream.forEach(n -> System.out.println("  " + n));
        
        // 5. Performance comparison
        System.out.println("\n5. Performance Comparison");
        System.out.println("------------------------");
        comparePerformance();
        
        // 6. Different data types
        System.out.println("\n6. Different Data Types");
        System.out.println("----------------------");
        RandomGenerator gen = RandomGeneratorFactory.getDefault().create();
        
        System.out.println("int: " + gen.nextInt());
        System.out.println("long: " + gen.nextLong());
        System.out.println("double: " + gen.nextDouble());
        System.out.println("float: " + gen.nextFloat());
        System.out.println("boolean: " + gen.nextBoolean());
        byte[] bytes = new byte[5];
        gen.nextBytes(bytes);
        System.out.println("bytes: " + java.util.Arrays.toString(bytes));
    }
    
    private static void comparePerformance() {
        int iterations = 10_000_000;
        
        // Legacy Random
        long start = System.currentTimeMillis();
        java.util.Random legacyRandom = new java.util.Random();
        for (int i = 0; i < iterations; i++) {
            legacyRandom.nextInt();
        }
        long legacyTime = System.currentTimeMillis() - start;
        
        // New RandomGenerator
        start = System.currentTimeMillis();
        RandomGenerator newRandom = RandomGeneratorFactory.getDefault().create();
        for (int i = 0; i < iterations; i++) {
            newRandom.nextInt();
        }
        long newTime = System.currentTimeMillis() - start;
        
        System.out.println("Legacy Random: " + legacyTime + " ms");
        System.out.println("New RandomGenerator: " + newTime + " ms");
        System.out.println("Improvement: " + String.format("%.2f%%", 
            (double)(legacyTime - newTime) / legacyTime * 100));
    }
}

