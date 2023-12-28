package com.yesid.backend.pedido.app.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;
	private String estado; //Nuevo, listo, enviado, entregado, pagado, cancelado
	
	@NotNull
	private Double envio;
	
	@JsonIgnoreProperties("pedidos")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cuenta_id")
	private Cuenta cuenta;
	
	@JsonIgnoreProperties("pedidos")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@JsonIgnoreProperties("pedidos")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mesa_id")
	private Mesa mesa;
	
	@OneToMany(mappedBy="pedido", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ItemPedido> items;
	
	@OneToOne(mappedBy="pedido")
	private Pago pago;
	
	@PrePersist
	public void prePersist() {
		this.fecha = new Date();
	}
	
	public Pedido() {
		this.items = new ArrayList<ItemPedido>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getEnvio() {
		return envio;
	}

	public void setEnvio(Double envio) {
		this.envio = envio;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public List<ItemPedido> getItemsPedido() {
		return items;
	}

	public void setItemsPedido(List<ItemPedido> items) {
		this.items = items;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public void addItemPedido(ItemPedido itemPedido) {
		this.items.add(itemPedido);
	}
	
	public Double getTotal() {
		Double total = 0.0;
		for(ItemPedido item: items) {
			total += item.calcularTotal();
		}
		return total;
	}
	
	public Map<String, String> verificarStock() {
		Map<String, String> resultado = new HashMap<String, String>();
		for(ItemPedido item: items) {
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
		for(ItemPedido item: items) {
			Producto producto = item.getProducto();
			producto.setStock(producto.getStock() - item.getCantidad());
			productos.add(producto);
		}
		return productos;
	}
}
