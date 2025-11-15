package java18.webserver;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;

/**
 * Java 18 Simple Web Server
 * Demonstrates the simple HTTP server API
 */
public class SimpleWebServer {
    public static void main(String[] args) throws IOException {
        // Create simple web server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        // Create context for root path
        server.createContext("/", exchange -> {
            String response = "Hello, World from Java 18 Simple Web Server!";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        
        // Create context for /info path
        server.createContext("/info", exchange -> {
            String response = "Server Info: Java 18 Simple Web Server";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        
        // Start server
        server.start();
        System.out.println("Server started on port 8000");
        System.out.println("Visit http://localhost:8000/ or http://localhost:8000/info");
        System.out.println("Press Enter to stop the server...");
        
        // Wait for user input to stop
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Stop server
        server.stop(0);
        System.out.println("Server stopped");
    }
}

