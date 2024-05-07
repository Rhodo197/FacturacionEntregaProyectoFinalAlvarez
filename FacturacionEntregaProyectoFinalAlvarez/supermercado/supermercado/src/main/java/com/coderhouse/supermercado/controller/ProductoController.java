package com.coderhouse.supermercado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coderhouse.supermercado.modelos.Producto;
import com.coderhouse.supermercado.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	//traer una lista de productos
		@GetMapping(value = "/ListaProductos", produces = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<List<Producto>> listarProductos(){
			//tiene que devuolver una repuesta del servidor
			try {
			List<Producto> productos = productoService.getListaDeProductos();
			return new ResponseEntity<>(productos, HttpStatus.OK); //codigo 200
			} catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //error 500
			}
		}
		
		
		//traer un producto por codigo
		@GetMapping(value = "/{codigo}/getProducto", produces = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<Producto> getProductoPorCodigo(@PathVariable("codigo") Integer codigo){
			try {
				
				Producto producto = productoService.getProductoPorCodigo(codigo);
				
				if(producto != null) {
					return new ResponseEntity<>(producto, HttpStatus.OK);	
				} else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
				
			} catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		//agregar un nuevo producto
		@PostMapping(value = "/nuevoProducto", consumes = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto){
			
			try {
				Producto nuevoProducto = productoService.agregarProducto(producto);
				return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);  //codigo 201, y devuelve al nuevo producto
			} catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		}
		
		//actualizar un cliente
		@PutMapping(value = "/{codigo}/actualizar", consumes = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<Producto> actualizarProducto(@PathVariable ("codigo") Integer codigo, @RequestBody Producto producto){
			
				
				Producto productoActualizado =  productoService.actualizarProducto(codigo, producto);
				
				if(productoActualizado != null) {
					return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}	
		}
		
		@DeleteMapping(value = "/{codigo}/eliminar")
		public ResponseEntity<Void> eliminarProducto(@PathVariable ("codigo") Integer codigo){
			
			boolean productoEliminado = productoService.eliminarProductoPorCodigo(codigo);
			if (productoEliminado == true) { //ya que tiene que ser un boolean
				return new ResponseEntity<>(HttpStatus.NO_CONTENT); //delete succesfull, codigo 204
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}
	
}
