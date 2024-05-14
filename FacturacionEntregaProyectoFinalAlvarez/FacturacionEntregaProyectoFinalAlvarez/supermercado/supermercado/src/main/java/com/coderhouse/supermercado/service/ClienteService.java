package com.coderhouse.supermercado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.supermercado.modelos.Cliente;
import com.coderhouse.supermercado.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> getListaDeClientes(){
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> getClientePorDni(Integer dni) {
		return clienteRepository.findById(dni); //sino en vez del optional puedo agregar el orElse(null)
	}
	
	public Cliente agregarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente actualizarCliente(Integer dni, Cliente cliente) {
		try {
			if(clienteRepository.existsById(dni)) {
				cliente.setDni(dni);
				clienteRepository.save(cliente);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return null;
	}
	
	public Boolean eliminarClientePorDni(Integer dni) {
		try {
			clienteRepository.deleteById(dni);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
}
