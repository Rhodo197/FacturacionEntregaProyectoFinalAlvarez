package com.coderhouse.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.supermercado.modelos.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer>{

}
