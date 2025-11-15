package java11.httpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Java 11 HTTP Client API (Standardized)
 * Demonstrates the finalized HTTP Client API
 */
public class HttpClientStandard {
    public static void main(String[] args) {
        
        // Create HTTP Client
        HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();
        
        // GET Request
        System.out.println("=== GET Request ===");
        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://httpbin.org/get"))
            .timeout(Duration.ofSeconds(5))
            .header("User-Agent", "Java 11 HttpClient")
            .GET()
            .build();
        
        try {
            HttpResponse<String> response = client.send(getRequest,
                HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Headers: " + response.headers().firstValue("content-type"));
            System.out.println("Body length: " + response.body().length());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        // Async Request
        System.out.println("\n=== Async Request ===");
        client.sendAsync(getRequest, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(body -> {
                System.out.println("Async response received");
                System.out.println("Body preview: " + body.substring(0, Math.min(100, body.length())));
            })
            .exceptionally(ex -> {
                System.err.println("Async error: " + ex.getMessage());
                return null;
            })
            .join();
        
        // POST Request
        System.out.println("\n=== POST Request ===");
        String jsonBody = "{\"name\":\"John\",\"age\":30}";
        
        HttpRequest postRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://httpbin.org/post"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
        
        try {
            HttpResponse<String> postResponse = client.send(postRequest,
                HttpResponse.BodyHandlers.ofString());
            
            System.out.println("POST Status: " + postResponse.statusCode());
            System.out.println("POST Response: " + postResponse.body().substring(0, Math.min(200, postResponse.body().length())));
        } catch (Exception e) {
            System.err.println("POST Error: " + e.getMessage());
        }
    }
}

