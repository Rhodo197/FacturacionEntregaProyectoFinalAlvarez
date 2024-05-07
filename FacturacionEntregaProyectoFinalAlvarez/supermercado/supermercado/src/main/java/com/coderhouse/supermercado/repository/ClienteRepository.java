package com.coderhouse.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.supermercado.modelos.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
