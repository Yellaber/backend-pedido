package com.yesid.backend.pedido.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name="catalogos")
@Data
public class Catalogo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nombre;
	private String descripcion;
	
	@OneToMany(mappedBy="catalogo", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Producto> productos;
	
	public Catalogo() {
		this.productos = new ArrayList<Producto>();
	}
}
