package com.reto.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoFinancieroDto {

    private String tipoProducto;
    private String nombreProducto;
    private Double saldo;

}
