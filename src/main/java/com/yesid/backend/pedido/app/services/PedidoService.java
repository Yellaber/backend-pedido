package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Pedido;
import com.yesid.backend.pedido.app.repositories.IPedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private IPedidoRepository pedidoRepositorio;
	
	@Transactional(readOnly=true)
	public List<Pedido> findAll() throws Exception {
		List<Pedido> pedidos = pedidoRepositorio.findAll();
		if(pedidos == null || pedidos.isEmpty()) {
			throw new Exception("No existen Pedidos registrados en el sistema.");
		}
		return pedidos;
	}
	
	@Transactional(readOnly=true)
	public Pedido findById(Long id) throws Exception {
		Pedido pedido = pedidoRepositorio.findById(id).orElse(null);
		if(pedido == null) {
			throw new Exception("Este Pedido no se encuentra registrado en el sistema.");
		}
		return pedido;
	}
	
	@Transactional
	public Pedido save(Pedido pedido) throws Exception {
		if(pedidoRepositorio.existsById(pedido.getId())) {
			throw new Exception("Este Pedido ya se encuentra registrado en el sistema.");
		}
		return pedidoRepositorio.save(pedido);
	}
	
	@Transactional
	public Pedido update(Long id, Pedido pedido) throws Exception {
		Pedido pedidoEncontrado = pedidoRepositorio.findById(id).orElse(null);
		if(pedidoEncontrado == null) {
			throw new Exception("Este Pedido no se encuentra registrado en el sistema.");
		}
		pedidoEncontrado.setEstado(pedido.getEstado());
		pedidoEncontrado.setValorEnvio(pedido.getValorEnvio());
		return pedidoRepositorio.save(pedidoEncontrado);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!pedidoRepositorio.existsById(id)) {
			throw new Exception("Este Pedido no se encuentra registrado en el sistema.");
		}
		pedidoRepositorio.deleteById(id);
	}
}
