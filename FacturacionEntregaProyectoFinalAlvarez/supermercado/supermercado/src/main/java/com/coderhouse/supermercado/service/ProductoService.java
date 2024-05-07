package com.coderhouse.supermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.supermercado.modelos.Producto;
import com.coderhouse.supermercado.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	ProductoRepository productoRepository;
	
	public List<Producto> getListaDeProductos(){
		return productoRepository.findAll();
	}
	
	public Producto getProductoPorCodigo(Integer codigo) {
		return productoRepository.findById(codigo).orElse(null); 
	}
	
	public Producto agregarProducto(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public Producto actualizarProducto(Integer codigo, Producto producto) {
		try {
			if(productoRepository.existsById(codigo)) {
				producto.setCodigo(codigo);
				productoRepository.save(producto);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return null;
	}
	
	public Boolean eliminarProductoPorCodigo(Integer codigo) {
		try {
			productoRepository.deleteById(codigo);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

}
