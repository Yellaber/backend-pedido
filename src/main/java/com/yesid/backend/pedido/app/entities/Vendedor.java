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
@Table(name="vendedores")
@Data
public class Vendedor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String apellidos;
	
	@OneToMany(mappedBy="vendedor", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Pedido> pedidos;
	
	public Vendedor() {
		this.pedidos = new ArrayList<Pedido>();
	}
}
