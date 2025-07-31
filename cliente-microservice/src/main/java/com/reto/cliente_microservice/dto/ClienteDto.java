package com.reto.cliente_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {

    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;

}
