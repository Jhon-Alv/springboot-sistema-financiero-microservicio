package com.reto.cliente_microservice.mapper;

import com.reto.cliente_microservice.dto.ClienteDto;
import com.reto.cliente_microservice.model.Cliente;




public class ClienteMapper {

    public static ClienteDto toDto(Cliente cliente) {
        return ClienteDto.builder()
                .nombres(cliente.getNombres())
                .apellidos(cliente.getApellidos())
                .tipoDocumento(cliente.getTipoDocumento())
                .numeroDocumento(cliente.getNumeroDocumento())
                .build();
    }

}
