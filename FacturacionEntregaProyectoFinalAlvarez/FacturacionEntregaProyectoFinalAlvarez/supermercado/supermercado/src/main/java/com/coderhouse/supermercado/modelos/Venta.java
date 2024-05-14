package com.coderhouse.supermercado.modelos;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description= "id autogenerado por el la bbdd", requiredMode = Schema.RequiredMode.REQUIRED , example ="1")
	private Integer numeroOperacion;
	
	@Column(name = "cantidad")
	@Schema(description= "cantidad del producto ingresado por el usuario", requiredMode = Schema.RequiredMode.REQUIRED , example ="3")
	private int cantidad;
	
	@Column (name = "precioTotal")
	@Schema(description= "precio total que lo calcula la aplicacion", requiredMode = Schema.RequiredMode.REQUIRED , example ="18000")
	private long precioTotal;
	
	@Schema(description= "hora y fecha actual brindada por java", requiredMode = Schema.RequiredMode.REQUIRED , example ="Mon-18:45:23")
	private LocalDateTime fecha;
	
	//para obtener una lista por clave-valor
	static Hashtable<String, Producto> lista = new Hashtable<>();
	
	

	public static Hashtable<String, Producto> getLista() {
		return lista;
	}

	public static void setLista(Hashtable<String, Producto> lista) {
		Venta.lista = lista;
	}

	public LocalDateTime getFecha() {
		return fecha;
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
