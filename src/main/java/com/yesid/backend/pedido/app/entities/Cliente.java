package com.yesid.backend.pedido.app.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=7, max=10)
	private String cedula;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String apellidos;
	
	@NotBlank
	private String ciudad;
	
	@NotBlank
	private String direccion;
	
	@NotBlank
	private String telefono;
	
	@Email
	private String email;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonManagedReference(value="pedido_cliente")
	private List<Pedido> pedidos;
	
	@OneToOne(mappedBy="cliente")
	@JsonManagedReference(value="cuenta_cliente")
	private Cuenta cuenta;
	
	public Cliente() {
		this.pedidos = new ArrayList<Pedido>();
	}

	public Long getId() {
		return id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
