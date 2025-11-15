package java16.unixsocket;

import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Java 16 Unix-Domain Socket Channels
 * Demonstrates inter-process communication using Unix-domain sockets
 * 
 * NOTE: Unix-domain sockets only work on Unix-like systems (Linux, macOS)
 * This example demonstrates the API structure
 */
public class UnixDomainSocketDemo {
    private static final String SOCKET_PATH = "/tmp/java16_unix_socket";
    
    public static void main(String[] args) {
        System.out.println("=== Java 16 Unix-Domain Socket Channels ===\n");
        
        // Check if running on Unix-like system
        String os = System.getProperty("os.name").toLowerCase();
        if (!os.contains("nix") && !os.contains("nux") && !os.contains("mac")) {
            System.out.println("Unix-domain sockets are only available on Unix-like systems.");
            System.out.println("Current OS: " + System.getProperty("os.name"));
            System.out.println("\nExample usage:");
            demonstrateAPI();
            return;
        }
        
        // Run server in a separate thread
        Thread serverThread = new Thread(() -> {
            try {
                runServer();
            } catch (IOException e) {
                System.err.println("Server error: " + e.getMessage());
            }
        });
        serverThread.start();
        
        // Wait for server to start
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Run client
        try {
            runClient();
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
        
        // Cleanup
        try {
            serverThread.join();
            cleanup();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void runServer() throws IOException {
        System.out.println("Starting server...");
        
        // Create server socket channel
        ServerSocketChannel server = ServerSocketChannel.open(StandardProtocolFamily.UNIX);
        
        // Create socket address
        UnixDomainSocketAddress address = UnixDomainSocketAddress.of(SOCKET_PATH);
        
        // Bind to address
        server.bind(address);
        System.out.println("Server bound to: " + SOCKET_PATH);
        
        // Accept connection
        SocketChannel client = server.accept();
        System.out.println("Client connected");
        
        // Read message from client
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = client.read(buffer);
        
        if (bytesRead > 0) {
            buffer.flip();
            String message = StandardCharsets.UTF_8.decode(buffer).toString();
            System.out.println("Received from client: " + message);
            
            // Send response
            String response = "Hello from server!";
            ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8));
            client.write(responseBuffer);
            System.out.println("Sent response to client");
        }
        
        // Close connections
        client.close();
        server.close();
        System.out.println("Server closed");
    }
    
    private static void runClient() throws IOException {
        System.out.println("\nStarting client...");
        
        // Create client socket channel
        SocketChannel client = SocketChannel.open(StandardProtocolFamily.UNIX);
        
        // Connect to server
        UnixDomainSocketAddress address = UnixDomainSocketAddress.of(SOCKET_PATH);
        client.connect(address);
        System.out.println("Connected to server");
        
        // Send message
        String message = "Hello from client!";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
        client.write(buffer);
        System.out.println("Sent message to server");
        
        // Read response
        ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
        int bytesRead = client.read(responseBuffer);
        
        if (bytesRead > 0) {
            responseBuffer.flip();
            String response = StandardCharsets.UTF_8.decode(responseBuffer).toString();
            System.out.println("Received from server: " + response);
        }
        
        // Close connection
        client.close();
        System.out.println("Client closed");
    }
    
    private static void cleanup() {
        try {
            Path socketPath = Path.of(SOCKET_PATH);
            if (Files.exists(socketPath)) {
                Files.delete(socketPath);
                System.out.println("\nCleaned up socket file");
            }
        } catch (IOException e) {
            System.err.println("Cleanup error: " + e.getMessage());
        }
    }
    
    private static void demonstrateAPI() {
        System.out.println("""
            // Server side
            ServerSocketChannel server = ServerSocketChannel.open(StandardProtocolFamily.UNIX);
            UnixDomainSocketAddress address = UnixDomainSocketAddress.of("/tmp/mysocket");
            server.bind(address);
            SocketChannel client = server.accept();
            
            // Client side
            SocketChannel client = SocketChannel.open(StandardProtocolFamily.UNIX);
            UnixDomainSocketAddress address = UnixDomainSocketAddress.of("/tmp/mysocket");
            client.connect(address);
            
            // Send data
            ByteBuffer buffer = ByteBuffer.wrap("Hello".getBytes());
            client.write(buffer);
            
            // Receive data
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            client.read(readBuffer);
            """);
        
        System.out.println("\nKey Features:");
        System.out.println("- File-based addressing (uses file system paths)");
        System.out.println("- Local communication only (same machine)");
        System.out.println("- Lower overhead than TCP/IP");
        System.out.println("- File system permissions control access");
        
        System.out.println("\nUse Cases:");
        System.out.println("- Inter-process communication on Unix systems");
        System.out.println("- Docker container communication");
        System.out.println("- Microservices on same host");
        System.out.println("- Application-to-daemon communication");
    }
}

