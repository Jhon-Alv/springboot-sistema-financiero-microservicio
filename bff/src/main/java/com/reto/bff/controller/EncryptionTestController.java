package com.reto.bff.controller;

import com.reto.bff.encryption.EncryptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test de Encriptación", description = "Pruebas de encriptado y desencriptado para validación")
public class EncryptionTestController {

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("/encrypt/{text}")
    @Operation(
            summary = "Encriptar texto",
            description = "Devuelve el texto encriptado usando AES"
    )
    public String encrypt(@PathVariable String text) {
        return encryptionService.encrypt(text);
    }

    @GetMapping("/decrypt/{cipher}")
    @Operation(
            summary = "Desencriptar texto",
            description = "Devuelve el texto original desencriptado desde el valor cifrado"
    )
    public String decrypt(@PathVariable String cipher) {
        return encryptionService.decrypt(cipher);
    }

}
