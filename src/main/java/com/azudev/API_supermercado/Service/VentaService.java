package com.azudev.API_supermercado.Service;

import com.azudev.API_supermercado.DTO.DetalleVentaDTO;
import com.azudev.API_supermercado.DTO.VentaDTO;
import com.azudev.API_supermercado.Exception.NotFoundException;
import com.azudev.API_supermercado.Mapper.Mapper;
import com.azudev.API_supermercado.Model.DetalleVenta;
import com.azudev.API_supermercado.Model.Producto;
import com.azudev.API_supermercado.Model.Sucursal;
import com.azudev.API_supermercado.Model.Venta;
import com.azudev.API_supermercado.Repository.ProductoRepository;
import com.azudev.API_supermercado.Repository.SucursalRepository;
import com.azudev.API_supermercado.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private SucursalRepository sucursalRepo;

    @Override
    public List<VentaDTO> findAll() {
        List<Venta> ventas = (List<Venta>) ventaRepo.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();

        VentaDTO dto;
        for (Venta v : ventas) {
            dto = Mapper.toDTO(v);
            ventasDto.add (dto);
        }

        return ventasDto;
    }

    @Override
    public VentaDTO save(VentaDTO ventaDTO) {
        //Validaciones
        if (ventaDTO == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDTO.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if (ventaDTO.getDetalle() == null || ventaDTO.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        //Buscar la sucursal
        Sucursal suc = sucursalRepo.findById(ventaDTO.getIdSucursal()).orElse(null);
        if (suc == null) {
            throw new NotFoundException("Sucursal no encontrada");
        }

        //Crear la venta
        Venta vent = new Venta();
        vent.setFecha(ventaDTO.getFecha());
        vent.setEstado(ventaDTO.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventaDTO.getTotal());

        // La lista de detalles
        // --> Acá están los productos
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetalleVentaDTO detDTO : ventaDTO.getDetalle()) {
            // Buscar producto por id (tu detDTO usa id como id de producto)
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);
            if (p == null)
            {throw new RuntimeException("Producto no encontrado: " + detDTO.getNombreProd());}

            //Crear detalle
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProducto(p);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantProducto(detDTO.getCantProd());
            detalleVent.setVenta(vent);

            detalles.add(detalleVent);
            totalCalculado = totalCalculado+(detDTO.getPrecio()*detDTO.getCantProd());

        }
        //Seteamos la lista de detalle Venta
        vent.setDetalleVentas(detalles);

        //guardamos en la BD
        vent = ventaRepo.save(vent);

        //Mapeo de salida
        VentaDTO ventaSalida = Mapper.toDTO(vent);

        return ventaSalida;
    }

    @Override
    public VentaDTO update(Long id, VentaDTO ventaDTO) {
        //buscar si la venta existe para actualizarla
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");

        if (ventaDTO.getFecha()!=null) {
            v.setFecha(ventaDTO.getFecha());
        }
        if(ventaDTO.getEstado()!=null) {
            v.setEstado(ventaDTO.getEstado());
        }

        if (ventaDTO.getTotal()!=null) {
            v.setTotal(ventaDTO.getTotal());
        }

        if (ventaDTO.getIdSucursal()!=null) {
            Sucursal suc = sucursalRepo.findById(ventaDTO.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }
        ventaRepo.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void delete(Long id) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");
        ventaRepo.delete(v);
    }
}