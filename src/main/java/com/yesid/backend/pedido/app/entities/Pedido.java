package com.yesid.backend.pedido.app.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="pedidos")
@Data
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;
	private String estado; //Nuevo, listo, enviado, entregado, pagado, cancelado
	
	@NotNull
	@Column(name="valor_envio")
	private Double valorEnvio;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cuenta_id")
	private Cuenta cuenta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vendedor_id")
	private Vendedor vendedor;
	
	@OneToMany(mappedBy="pedido", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ItemPedido> itemsPedido;
	
	@PrePersist
	public void prePersist() {
		this.fecha = new Date();
	}
	
	public void addItemPedido(ItemPedido itemPedido) {
		this.itemsPedido.add(itemPedido);
	}
	
	public Double getTotal() {
		Double total = 0.0;
		for(ItemPedido item: itemsPedido) {
			total += item.calcularTotal();
		}
		return total;
	}
	
	public Map<String, String> verificarStock() {
		Map<String, String> resultado = new HashMap<String, String>();
		for(ItemPedido item: itemsPedido) {
			int cantidad = item.getCantidad().intValue();
			int stock = item.getProducto().getStock().intValue();
			if(cantidad > stock) {
				resultado.put(item.getProducto().getCodigo(), "No hay stock suficiente de " + item.getProducto().getNombre() + ".");
			}
		}
		return resultado;
	}
	
	public List<Producto> actualizarStock() {
		List<Producto> productos = new ArrayList<Producto>();
		for(ItemPedido item: itemsPedido) {
			Producto producto = item.getProducto();
			producto.setStock(producto.getStock() - item.getCantidad());
			productos.add(producto);
		}
		return productos;
	}
}
