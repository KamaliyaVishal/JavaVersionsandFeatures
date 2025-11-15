package java11.jfr;

import jdk.jfr.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Java 11 Java Flight Recorder (JFR) Demo
 * 
 * Demonstrates how to use JFR programmatically to record application events.
 * 
 * To run with JFR:
 * java -XX:+FlightRecorder -XX:StartFlightRecording=duration=60s,filename=recording.jfr JavaFlightRecorderDemo
 * 
 * Or use programmatic recording as shown below.
 */
public class JavaFlightRecorderDemo {
    
    @Label("Custom Event")
    @Description("A custom application event")
    static class CustomEvent extends Event {
        @Label("Event ID")
        int eventId;
        
        @Label("Event Message")
        String message;
        
        @Label("Processing Time")
        @Timespan(Timespan.MILLISECONDS)
        long processingTime;
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("=== Java Flight Recorder Demo ===");
        
        // Check if JFR is available
        if (!FlightRecorder.isAvailable()) {
            System.out.println("JFR is not available. Run with -XX:+FlightRecorder");
            System.out.println("Example: java -XX:+FlightRecorder JavaFlightRecorderDemo");
            return;
        }
        
        System.out.println("JFR is available!");
        
        // 1. Programmatic Recording
        System.out.println("\n--- Starting Programmatic Recording ---");
        Recording recording = new Recording();
        
        // Configure recording
        recording.setName("MyApplicationRecording");
        recording.setMaxAge(java.time.Duration.ofMinutes(5));
        recording.setMaxSize(100 * 1024 * 1024); // 100 MB
        
        // Start recording
        recording.start();
        System.out.println("Recording started");
        
        // 2. Simulate application work
        System.out.println("\n--- Simulating Application Work ---");
        simulateWork();
        
        // 3. Record custom events
        System.out.println("\n--- Recording Custom Events ---");
        recordCustomEvents();
        
        // 4. Stop and save recording
        System.out.println("\n--- Stopping Recording ---");
        recording.stop();
        
        // Save to file
        Path recordingPath = Paths.get("demo-recording.jfr");
        recording.dump(recordingPath);
        System.out.println("Recording saved to: " + recordingPath.toAbsolutePath());
        
        recording.close();
        
        // 5. List available recordings
        System.out.println("\n--- Available Recordings ---");
        List<Recording> recordings = FlightRecorder.getFlightRecorder().getRecordings();
        System.out.println("Active recordings: " + recordings.size());
        
        System.out.println("\n=== JFR Usage Tips ===");
        System.out.println("1. Use JMC (JDK Mission Control) to analyze recordings");
        System.out.println("2. Use jfr tool: jfr print demo-recording.jfr");
        System.out.println("3. Use jfr summary: jfr summary demo-recording.jfr");
        System.out.println("4. Filter events: jfr print --events CPULoad demo-recording.jfr");
        
        System.out.println("\n=== Command Line Usage ===");
        System.out.println("# Start with JFR:");
        System.out.println("java -XX:+FlightRecorder \\");
        System.out.println("     -XX:StartFlightRecording=duration=60s,filename=recording.jfr \\");
        System.out.println("     JavaFlightRecorderDemo");
        
        System.out.println("\n# Start recording on running JVM:");
        System.out.println("jcmd <pid> JFR.start duration=60s filename=recording.jfr");
        
        System.out.println("\n# Stop recording:");
        System.out.println("jcmd <pid> JFR.stop");
        
        System.out.println("\n# Dump recording:");
        System.out.println("jcmd <pid> JFR.dump filename=recording.jfr");
    }
    
    private static void simulateWork() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        
        // Simulate CPU-intensive work
        for (int i = 0; i < 100000; i++) {
            numbers.add(random.nextInt(1000));
        }
        
        // Simulate some processing
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .mapToInt(n -> n * n)
            .sum();
        
        System.out.println("Processed " + numbers.size() + " numbers");
    }
    
    private static void recordCustomEvents() {
        for (int i = 0; i < 5; i++) {
            CustomEvent event = new CustomEvent();
            event.eventId = i;
            event.message = "Processing item " + i;
            
            long startTime = System.currentTimeMillis();
            
            // Simulate work
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            event.processingTime = System.currentTimeMillis() - startTime;
            event.commit(); // Record the event
            
            System.out.println("Recorded event: " + event.message + " (took " + event.processingTime + "ms)");
        }
    }
}

