package com.reto.productos_microservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoFinanciero {

    @Id
    private String id;
    private String codigoUnico;
    private String tipoProducto;
    private String nombreProducto;
    private Double saldo;

}
