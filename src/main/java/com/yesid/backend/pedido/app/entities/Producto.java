package com.yesid.backend.pedido.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="productos")
@Data
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String codigo;
	
	@NotBlank
	private String nombre;
	private String descripcion;
	
	@NotBlank
	private String medida; //Kilogramos, gramos, centimetros, unidades
	
	@NotNull
	private Integer stock;
	
	@Column(name="punto_reorden")
	private Integer puntoReorden;
	
	@NotNull
	private Double precio;	
	private String tipo; //Terminado, insumo
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="etiqueta_id")
	private Categoria etiqueta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="proveedor_id")
	private Proveedor proveedor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="catalogo_id")
	private Catalogo catalogo;
	
	@OneToMany(mappedBy="producto", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Producto> productos;
	
	@OneToMany(mappedBy="producto", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ItemPedido> items;
	
	@OneToMany(mappedBy="producto", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Movimiento> movimientos;
	
	public Producto() {
		this.productos = new ArrayList<Producto>();
		this.items = new ArrayList<ItemPedido>();
		this.movimientos = new ArrayList<Movimiento>();
	}
}
