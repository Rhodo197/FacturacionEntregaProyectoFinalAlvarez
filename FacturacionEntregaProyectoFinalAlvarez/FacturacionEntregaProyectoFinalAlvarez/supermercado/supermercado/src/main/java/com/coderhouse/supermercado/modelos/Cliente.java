package com.coderhouse.supermercado.modelos;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	@Schema(description= "id ingresado por el usuario", requiredMode = Schema.RequiredMode.REQUIRED , example ="43934737")
	private Integer dni;
	
	@Column(name = "nombre")
	@Schema(description= "nombre ingresado por el usuario", requiredMode = Schema.RequiredMode.REQUIRED , example ="Ramiro")
	private String nombre;
	
	@Column(name = "apellido")
	@Schema(description= "apellido ingresado por el usuario", requiredMode = Schema.RequiredMode.REQUIRED , example ="Perez")
	private String apellido;
	
	@Column(name = "telefono")
	@Schema(description= "nuevo de telefono ingresado por el usuario", requiredMode = Schema.RequiredMode.REQUIRED , example ="29746780")
	private Integer telefono;
	
	@OneToMany(mappedBy= "cliente", cascade = CascadeType.ALL)
	private List<Venta> ventas = new ArrayList<>();
	
	
	public Cliente() {
		
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(dni, other.dni)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(telefono, other.telefono);
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	
	

}
