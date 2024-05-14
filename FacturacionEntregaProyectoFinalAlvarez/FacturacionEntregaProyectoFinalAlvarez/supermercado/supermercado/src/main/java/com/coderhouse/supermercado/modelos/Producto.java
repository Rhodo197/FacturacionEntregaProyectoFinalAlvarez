package com.coderhouse.supermercado.modelos;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Schema(description= "id autogenerado por la BBDD", requiredMode = Schema.RequiredMode.AUTO , example ="1")
	private Integer codigo;
	
	@Column (name = "nombre", nullable = false, unique = true)
	@Schema(description= "nombre de producto ingresado por el usuario", requiredMode = Schema.RequiredMode.REQUIRED , example ="oreo")
	private String nombre;
	
	@Column (name = "precio")
	@Schema(description= "precio unitario ingresado por el usuario", requiredMode = Schema.RequiredMode.REQUIRED , example ="250")
	private Long precio;
	
	@Column (name = "cantidad")
	@Schema(description= "cantidad ingresada por el usuario", requiredMode = Schema.RequiredMode.NOT_REQUIRED , example ="7")
	private Integer cantidad;
	
	@Column(name = "stock")
	@Schema(description= "cantidad de stock ingresada por el usuario", requiredMode = Schema.RequiredMode.REQUIRED , example ="800")
	private Integer stock;
	
	@ManyToOne
	@JoinColumn(name = "venta_id")
	private Venta venta;
	
	public Producto() {
		
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, codigo, nombre, precio);
	}
	
	

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(cantidad, other.cantidad) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio);
	}
	//
	public boolean vendido(int stock) {
		if(cantidad >= stock) {
			cantidad -= stock;
		}else {
			System.out.println("No hay suficiente stock");
			return false;
		}
		return true;
	}
	
	
}
