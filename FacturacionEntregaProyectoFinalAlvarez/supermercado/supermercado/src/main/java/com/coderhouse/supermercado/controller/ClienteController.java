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

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	//traer una lista de clientes
	@GetMapping(value = "/listaDeClientes", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Cliente>> listarClientes(){
		//tiene que devuolver una repuesta del servidor
		try {
		List<Cliente> clientes = clienteService.getListaDeClientes();
		return new ResponseEntity<>(clientes, HttpStatus.OK); //codigo 200
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //error 500
		}
	}
	
	
	//traer un cliente por dni
	@GetMapping(value = "/{dni}/getCliente", produces = {MediaType.APPLICATION_JSON_VALUE})
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
	
	//agregar un nuevo cliente
	@PostMapping(value = "/nuevoCliente", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente){
		
		try {
			Cliente clienteNuevo = clienteService.agregarCliente(cliente);
			return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);  //codigo 201, y devuelve al nuevo cliente
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//actualizar un cliente
	@PutMapping(value = "/{dni}/actualizar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable ("dni") Integer dni, @RequestBody Cliente cliente){
		
			
			Cliente clienteActualizado =  clienteService.actualizarCliente(dni, cliente);
			
			if(clienteActualizado != null) {
				return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}	
	}
	
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
