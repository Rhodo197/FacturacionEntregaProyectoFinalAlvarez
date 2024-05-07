package com.coderhouse.supermercado.modelos;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "venta")
public class Venta {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer numeroOperacion;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column (name = "precioTotal")
	private long precioTotal;
	
	private LocalDate fecha;
	
	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	
	@OneToMany(mappedBy= "venta", cascade = CascadeType.ALL)
	private List<Producto> productos = new ArrayList<>();
	
	public Venta() {
		
	}

	public Integer getNumeroOperacion() {
		return numeroOperacion;
	}

	public void setNumeroOperacion(Integer numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public long getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(long precioTotal) {
		this.precioTotal = precioTotal;
	}

	public void setFecha(LocalDateTime fechaVenta) {
		// TODO Auto-generated method stub
		
	}

	
	
}
