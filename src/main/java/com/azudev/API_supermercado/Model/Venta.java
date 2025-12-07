package com.azudev.API_supermercado.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "venta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String estado;

    @ManyToOne
    private Sucursal sucursal;

    @OneToMany(mappedBy = "venta", cascade =CascadeType.ALL , fetch = FetchType.EAGER)
    private List<DetalleVenta> detalleVentas;

    private Double total;


}
