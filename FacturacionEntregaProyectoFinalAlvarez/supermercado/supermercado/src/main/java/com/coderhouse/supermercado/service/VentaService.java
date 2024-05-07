package com.coderhouse.supermercado.service;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.supermercado.modelos.Cliente;
import com.coderhouse.supermercado.modelos.Producto;
import com.coderhouse.supermercado.modelos.Venta;
import com.coderhouse.supermercado.repository.ClienteRepository;
import com.coderhouse.supermercado.repository.ProductoRepository;
import com.coderhouse.supermercado.repository.VentaRepository;

@Service
public class VentaService {

	@Autowired
	VentaRepository ventaRepository;
	ClienteRepository clienteRepository;
	ProductoRepository productoRepository;
	
	
	private final String HORA_URL = "http://worldtimeapi.org/api/timezone/America/Argentina[Buenos Aires]";
	private RestTemplate restTemplate;
	
	public Venta verVenta(Integer numeroOperacion) {
			
			Venta venta = ventaRepository.findById(numeroOperacion).orElse(null);
			
			return venta;
		
	}

	
	public List<Venta> listaDeVentas(){
		return ventaRepository.findAll();
	}
	
	
	//no me funcionó este metodo
	public Venta nuevaVenta(Venta venta,Integer dni, Integer codigo, Integer cantidad) {
		
		Cliente cliente = clienteRepository.findById(dni).orElse(null);
		
		if(cliente == null) {
			throw new RuntimeException("No se ha encontrado al cliente");
		} 
			
		Producto producto = productoRepository.findById(codigo).orElse(null);
		
		if(producto == null) {
			throw new RuntimeException("No se ha encontrado el producto");
		}
		
		if (cantidad <= 0) {
			throw new RuntimeException("No se ingreso ningun producto");
		}
		
		if(producto.getStock() < cantidad) {
			throw new RuntimeException("No hay suficiente stock almacenado");
		}
		//para que reste el stock por la cantidad que estamos solicitando del producto
		producto.setStock(producto.getStock()-cantidad);
		productoRepository.save(producto); //se actualiza el stock del producto
		
		List<Producto> productos = productoRepository.findAll(); 
		
		Long precioTotal = producto.getCantidad() * producto.getPrecio();
		
		LocalDateTime fechaVenta = LocalDateTime.now();
		
		Venta ventaFinal = new Venta();
		
		venta.setCliente(cliente);;
		venta.setCantidad(cantidad);
		venta.setProductos(productos);
		venta.setPrecioTotal(precioTotal);;
		venta.setFecha(fechaVenta);
		return ventaRepository.save(ventaFinal);
		
	}
	

	
}
