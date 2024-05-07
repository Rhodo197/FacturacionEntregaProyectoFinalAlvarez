package com.coderhouse.supermercado.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.supermercado.modelos.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

	List<Producto> findByNombre(String producto);
	
}
