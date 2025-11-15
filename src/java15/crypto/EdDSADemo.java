package java15.crypto;

import java.security.*;
import java.security.spec.*;
import java.util.Base64;

/**
 * Java 15 Edwards-Curve Digital Signature Algorithm (EdDSA) Demo
 * 
 * Demonstrates the new EdDSA cryptographic algorithm support.
 * Supports Ed25519 and Ed448 curves.
 */
public class EdDSADemo {
    public static void main(String[] args) {
        
        System.out.println("=== Edwards-Curve Digital Signature Algorithm (EdDSA) ===\n");
        
        try {
            // 1. Generate EdDSA key pair (Ed25519)
            System.out.println("--- Generating Ed25519 Key Pair ---");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("Ed25519");
            KeyPair keyPair = keyGen.generateKeyPair();
            
            System.out.println("Key pair generated successfully");
            System.out.println("Public key algorithm: " + keyPair.getPublic().getAlgorithm());
            System.out.println("Private key algorithm: " + keyPair.getPrivate().getAlgorithm());
            
            // 2. Sign data
            System.out.println("\n--- Signing Data ---");
            Signature signature = Signature.getInstance("Ed25519");
            signature.initSign(keyPair.getPrivate());
            
            String message = "Hello, Java 15!";
            byte[] data = message.getBytes();
            signature.update(data);
            byte[] signatureBytes = signature.sign();
            
            System.out.println("Message: " + message);
            System.out.println("Signature (Base64): " + Base64.getEncoder().encodeToString(signatureBytes));
            System.out.println("Signature length: " + signatureBytes.length + " bytes");
            
            // 3. Verify signature
            System.out.println("\n--- Verifying Signature ---");
            signature.initVerify(keyPair.getPublic());
            signature.update(data);
            boolean verified = signature.verify(signatureBytes);
            
            System.out.println("Signature verified: " + verified);
            
            // 4. Verify with wrong data (should fail)
            System.out.println("\n--- Verifying with Wrong Data ---");
            signature.initVerify(keyPair.getPublic());
            byte[] wrongData = "Wrong message".getBytes();
            signature.update(wrongData);
            boolean wrongVerified = signature.verify(signatureBytes);
            
            System.out.println("Signature verified (wrong data): " + wrongVerified);
            System.out.println("Expected: false (signature should not match)");
            
            // 5. Ed448 example (if supported)
            System.out.println("\n--- Ed448 Algorithm ---");
            try {
                KeyPairGenerator keyGen448 = KeyPairGenerator.getInstance("Ed448");
                KeyPair keyPair448 = keyGen448.generateKeyPair();
                
                Signature sig448 = Signature.getInstance("Ed448");
                sig448.initSign(keyPair448.getPrivate());
                sig448.update(data);
                byte[] sig448Bytes = sig448.sign();
                
                System.out.println("Ed448 signature generated");
                System.out.println("Signature length: " + sig448Bytes.length + " bytes");
                
                // Verify
                sig448.initVerify(keyPair448.getPublic());
                sig448.update(data);
                boolean verified448 = sig448.verify(sig448Bytes);
                System.out.println("Ed448 signature verified: " + verified448);
                
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Ed448 not available: " + e.getMessage());
            }
            
            // 6. Comparison with other algorithms
            System.out.println("\n--- Comparison with Other Algorithms ---");
            System.out.println("EdDSA advantages:");
            System.out.println("  - Smaller signature sizes");
            System.out.println("  - Better performance");
            System.out.println("  - Modern cryptographic algorithm");
            System.out.println("  - Better security properties");
            
            System.out.println("\nSignature sizes:");
            System.out.println("  - Ed25519: 64 bytes");
            System.out.println("  - Ed448: 114 bytes");
            System.out.println("  - RSA-2048: 256 bytes");
            System.out.println("  - ECDSA P-256: 64-72 bytes");
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("EdDSA algorithm not available: " + e.getMessage());
            System.out.println("\nNote: EdDSA requires Java 15+");
        } catch (InvalidKeyException | SignatureException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Benefits ===");
        System.out.println("1. Better security");
        System.out.println("2. Improved performance");
        System.out.println("3. Smaller signature sizes");
        System.out.println("4. Modern cryptographic algorithm");
        System.out.println("5. Standard algorithm support");
        
        System.out.println("\n=== Use Cases ===");
        System.out.println("1. Digital signatures");
        System.out.println("2. Authentication");
        System.out.println("3. Secure communications");
        System.out.println("4. Certificate generation");
        System.out.println("5. Cryptographic protocols");
    }
}

