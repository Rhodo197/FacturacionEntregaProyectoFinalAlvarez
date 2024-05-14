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

import com.coderhouse.supermercado.modelos.Cliente;
import com.coderhouse.supermercado.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@Operation(summary = "listar clientes", description = "permite traer una lista de clientes")
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operacion exitosa"),
			@ApiResponse(responseCode = "500", description = "error de servidor",
			content = { @Content(mediaType = "application/json")})
	})
	@GetMapping(value = "/lista-de-clientes", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Cliente>> listarClientes(){
		//tiene que devuolver una repuesta del servidor
		try {
		List<Cliente> clientes = clienteService.getListaDeClientes();
		return new ResponseEntity<>(clientes, HttpStatus.OK); //codigo 200
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //error 500
		}
	}

	@Operation(summary = "obtencion de cliente", description = "permite buscar un cliente por dni")
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operacion exitosa"),
			@ApiResponse(responseCode = "400", description = "No existe el cliente",
			content = { @Content(mediaType = "application/json")})
	})
	@GetMapping(value = "/{dni}/get-cliente", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> getClientePorDni(@PathVariable("dni") Integer dni){
		try {
			
			Cliente cliente = clienteService.getClientePorDni(dni).orElse(null);
			
			if(cliente != null) {
				return new ResponseEntity<>(cliente, HttpStatus.OK);	
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "creacion de cliente", description = "permite la creacion de un nuevo cliente")
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "operacion exitosa"),
			@ApiResponse(responseCode = "400", description = "error en el ingreso de datos",
			content = { @Content(mediaType = "application/json")})
	})
	@PostMapping(value = "/nuevo-cliente", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente){
		
		try {
			Cliente clienteNuevo = clienteService.agregarCliente(cliente);
			return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);  //codigo 201, y devuelve al nuevo cliente
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@Operation(summary = "actualizacion del cliente", description = "permite actualizar los datos del cliente")
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operacion exitosa"),
			@ApiResponse(responseCode = "500", description = "error de servidor",
			content = { @Content(mediaType = "application/json")})
	})
	@PutMapping(value = "/{dni}/actualizar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable ("dni") Integer dni, @RequestBody Cliente cliente){
		
			
			Cliente clienteActualizado =  clienteService.actualizarCliente(dni, cliente);
			
			if(clienteActualizado != null) {
				return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}	
	}
	
	@Operation(summary = "delete cliente", description = "permite que se elimine un cliente existente")
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operacion exitosa"),
			@ApiResponse(responseCode = "404", description = "cliente no encontrado",
			content = { @Content(mediaType = "application/json")})
	})
	@DeleteMapping(value = "/{dni}/eliminar")
	public ResponseEntity<Void> eliminarCliente(@PathVariable ("dni") Integer dni){
		
		boolean clienteEliminado = clienteService.eliminarClientePorDni(dni);
		if (clienteEliminado == true) { //ya que tiene que ser un boolean
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); //delete succesfull, codigo 204
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
