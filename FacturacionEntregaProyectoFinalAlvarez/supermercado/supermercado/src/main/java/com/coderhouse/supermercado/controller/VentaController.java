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
import com.coderhouse.supermercado.modelos.Comprobante;
import com.coderhouse.supermercado.modelos.Producto;
import com.coderhouse.supermercado.modelos.Venta;
import com.coderhouse.supermercado.service.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {

	@Autowired
	VentaService ventaService;
	
	//hacer una venta
	@PostMapping(value = "/realizarVenta", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Venta> realizarVenta(@RequestBody Venta venta,@RequestBody Cliente cliente,@RequestBody Producto producto, Integer cantidad){
		try {
			Venta ventas = ventaService.nuevaVenta(venta, cantidad, cantidad, cantidad);
			return new ResponseEntity<>(ventas,HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//ver una venta ya hecha
	@GetMapping(value = "{numeroOperacion}/verVenta", produces = {MediaType.APPLICATION_JSON_VALUE})
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
	
	//ver la lista de ventas que se han hecho
	@GetMapping(value = "/listaVentas", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Venta>> listaDeVentas(){
		try {
			
			List<Venta> ventas = ventaService.listaDeVentas();
			return new ResponseEntity<>(ventas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
