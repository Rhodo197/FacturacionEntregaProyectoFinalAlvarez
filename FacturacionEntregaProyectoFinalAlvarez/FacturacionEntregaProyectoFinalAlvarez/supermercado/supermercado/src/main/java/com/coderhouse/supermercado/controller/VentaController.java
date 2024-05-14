package com.coderhouse.supermercado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.supermercado.modelos.Cliente;
import com.coderhouse.supermercado.modelos.Producto;
import com.coderhouse.supermercado.modelos.Venta;
import com.coderhouse.supermercado.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/venta")
public class VentaController {

	@Autowired
	VentaService ventaService;
	
	@Operation(summary = "creacion de una venta", description = "permite la creacion de una venta")
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operacion exitosa"),
			@ApiResponse(responseCode = "400", description = "error de solicitud",
			content = { @Content(mediaType = "application/json")})
	})
	@PostMapping(value = "/realizar-venta", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Venta> realizarVenta(@RequestBody Venta venta,@RequestBody Cliente cliente,@RequestBody Producto producto, Integer cantidad){
		try {
			Venta ventas = ventaService.nuevaVenta(venta, cantidad, cantidad, cantidad);
			return new ResponseEntity<>(ventas,HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Operation(summary = "obtencion de una venta", description = "permite ver una venta hecha")
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operacion exitosa"),
			@ApiResponse(responseCode = "404", description = "not found",
			content = { @Content(mediaType = "application/json")})
	})
	@GetMapping(value = "{numeroOperacion}/ver-venta", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Venta> getVenta(@PathVariable("numeroOperacion") Integer numeroOperacion){
		try {
			
			Venta venta = ventaService.verVenta(numeroOperacion);
			if (venta != null) {
				return new ResponseEntity<>(venta, HttpStatus.OK); //codigo 200
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);  //codigo 404
			}
			
		} catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //codigo 500
		}
	}
	
	@Operation(summary = "obtencion de lista de ventas", description = "permite obtener una lista de las ventas hechas")
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operacion exitosa"),
			@ApiResponse(responseCode = "500", description = "error de servidor",
			content = { @Content(mediaType = "application/json")})
	})
	@GetMapping(value = "/lista-ventas", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Venta>> listaDeVentas(){
		try {
			
			List<Venta> ventas = ventaService.listaDeVentas();
			return new ResponseEntity<>(ventas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
