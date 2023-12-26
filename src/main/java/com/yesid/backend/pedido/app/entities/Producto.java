package com.yesid.backend.pedido.app.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

@Entity
@Table(name="productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String codigo;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String medida; //Kilogramos, gramos, centimetros, unidades
	
	@NotNull
	private Integer stock;
	
	@Column(name="punto_reorden")
	private Integer puntoReorden;
	
	@NotNull
	private Double precio;	
	private String tipo; //Terminado, insumo
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="proveedor_id")
	private Proveedor proveedor;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	@JsonManagedReference
	@OneToMany(mappedBy="producto", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Producto> productos;
	
	@JsonManagedReference
	@OneToMany(mappedBy="producto", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ItemPedido> items;
	
	@JsonManagedReference
	@OneToMany(mappedBy="producto", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Movimiento> movimientos;
	
	public Producto() {
		this.productos = new ArrayList<Producto>();
		this.items = new ArrayList<ItemPedido>();
		this.movimientos = new ArrayList<Movimiento>();
	}

	public Long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getPuntoReorden() {
		return puntoReorden;
	}

	public void setPuntoReorden(Integer puntoReorden) {
		this.puntoReorden = puntoReorden;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<ItemPedido> getItems() {
		return items;
	}

	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
}
