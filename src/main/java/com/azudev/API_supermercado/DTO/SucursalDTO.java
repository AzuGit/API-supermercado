package com.azudev.API_supermercado.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SucursalDTO {

    private Long id;

    private String nombre;

    private String direccion;

}
