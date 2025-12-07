package com.azudev.API_supermercado.Mapper;

import com.azudev.API_supermercado.DTO.DetalleVentaDTO;
import com.azudev.API_supermercado.DTO.ProductoDTO;
import com.azudev.API_supermercado.DTO.SucursalDTO;
import com.azudev.API_supermercado.DTO.VentaDTO;
import com.azudev.API_supermercado.Model.Producto;
import com.azudev.API_supermercado.Model.Sucursal;
import com.azudev.API_supermercado.Model.Venta;

import java.util.stream.Collectors;

public class Mapper {

    //mapeo de producto
    public static ProductoDTO toDTO(Producto producto){
        if(producto == null) return null;

        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .categoria(producto.getCategoria())
                .cantidad(producto.getCantidad())
                .build();
    }

    //mapeo de sucursal
    public static SucursalDTO toDTO(Sucursal sucursal){
        if(sucursal == null) return null;

        return SucursalDTO.builder()
                .id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();
    }

    //Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta venta) {
        if (venta == null) return null;

        var detalle = venta.getDetalleVentas().stream().map(det ->
                DetalleVentaDTO.builder()
                        .id(det.getProducto().getId())
                        .nombreProd(det.getProducto().getNombre())
                        .cantProd(det.getCantProducto())
                        .precio(det.getPrecio())
                        .subtotal(det.getPrecio() * det.getCantProducto())
                        .build()
        ).collect(Collectors.toList());

        var total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getId())
                .estado(venta.getEstado())
                .detalle(detalle)
                .total(total)
                .build();
    }
}
