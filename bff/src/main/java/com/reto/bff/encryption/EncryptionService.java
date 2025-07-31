package com.reto.bff.encryption;

public interface EncryptionService {
    String encrypt(String plaintext);
    String decrypt(String encryptedText);
}
