package com.azudev.API_supermercado.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle|_venta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="venta_id")
    private Venta venta;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="producto_id")
    private Producto producto;


    @Column(name = "cantidad_producto")
    private Integer cantProducto;

    private Double precio;

}
