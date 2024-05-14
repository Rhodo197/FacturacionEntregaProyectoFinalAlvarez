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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	
	@Operation(summary = "obtencion de lista de productos", description = "permite traer la lista de productos")
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operacion exitosa"),
			@ApiResponse(responseCode = "500", description = "error de servidor",
			content = { @Content(mediaType = "application/json")})
	})
	//traer una lista de productos
		@GetMapping(value = "/lista-productos", produces = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<List<Producto>> listarProductos(){
			//tiene que devuolver una repuesta del servidor
			try {
			List<Producto> productos = productoService.getListaDeProductos();
			return new ResponseEntity<>(productos, HttpStatus.OK); //codigo 200
			} catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //error 500
			}
		}
		
		
	@Operation(summary = "obtencion de producto", description = "permite la obtencion de un cliente")
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operacion exitosa"),
			@ApiResponse(responseCode = "400", description = "error de contenido",
			content = { @Content(mediaType = "application/json")})
	})
		@GetMapping(value = "/{codigo}/get-producto", produces = {MediaType.APPLICATION_JSON_VALUE})
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
		
		@Operation(summary = "creacion de producto", description = "permite la creacion de un nuevo producto")
	
		@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operacion exitosa"),
			@ApiResponse(responseCode = "400", description = "error de solicitud",
			content = { @Content(mediaType = "application/json")})
		})
		@PostMapping(value = "/nuevo-producto", consumes = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto){
			
			try {
				Producto nuevoProducto = productoService.agregarProducto(producto);
				return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);  //codigo 201, y devuelve al nuevo producto
			} catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		}
		
		@Operation(summary = "actualizacion de producto", description = "permite la actualizacion de un producto")
		
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "operacion exitosa"),
				@ApiResponse(responseCode = "500", description = "error de servidor",
				content = { @Content(mediaType = "application/json")})
		})
		@PutMapping(value = "/{codigo}/actualizar", consumes = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<Producto> actualizarProducto(@PathVariable ("codigo") Integer codigo, @RequestBody Producto producto){
			
				
				Producto productoActualizado =  productoService.actualizarProducto(codigo, producto);
				
				if(productoActualizado != null) {
					return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}	
		}
		
		@Operation(summary = "eliminacion de producto", description = "permite la eliminacion de un producto")
		
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "operacion exitosa"),
				@ApiResponse(responseCode = "500", description = "error de servidor",
				content = { @Content(mediaType = "application/json")})
		})
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
