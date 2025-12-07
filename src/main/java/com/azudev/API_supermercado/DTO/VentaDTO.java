package com.azudev.API_supermercado.DTO;

import com.azudev.API_supermercado.Model.Sucursal;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VentaDTO {

    //datos venta
    private Long id;
    private LocalDate fecha;
    private String estado;

    //datos de la sucursal
    private Long idSucursal;

    //lista de detalles
    private List<DetalleVentaDTO> detalle = new ArrayList<>();

    //total de la venta
    private Double total;

}
