package com.yesid.backend.pedido.app.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name="cuentas")
@Data
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String password;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;
	
	@OneToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente; 
	
	@OneToMany(mappedBy="cuenta", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Pedido> pedidos;
	
	public Cuenta() {
		this.pedidos = new ArrayList<Pedido>();
	}
	
	@PrePersist
	public void prePersist() {
		this.fecha = new Date();
	}
}
