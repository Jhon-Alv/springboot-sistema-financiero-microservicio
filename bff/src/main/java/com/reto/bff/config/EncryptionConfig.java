package com.reto.bff.config;

import com.reto.bff.encryption.AesEncryptionService;
import com.reto.bff.encryption.EncryptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionConfig {

    @Bean
    public EncryptionService encryptionService(){
        return new AesEncryptionService();
    }

}
