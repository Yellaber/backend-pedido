package com.yesid.backend.pedido.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.ItemPedido;
import com.yesid.backend.pedido.app.repositories.IItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private IItemPedidoRepository itemCarritoRepositorio;
	
	@Transactional(readOnly=true)
	public ItemPedido findById(Long id) throws Exception {
		ItemPedido itemCarrito = itemCarritoRepositorio.findById(id).orElse(null);
		if(itemCarrito == null) {
			throw new Exception("Este Item no se encuentra registrado en el sistema.");
		}
		return itemCarrito;
	}
	
	@Transactional
	public ItemPedido save(ItemPedido itemCarrito) throws Exception {
		if(itemCarritoRepositorio.existsById(itemCarrito.getId())) {
			throw new Exception("Este Item ya se encuentra registrado en el sistema.");
		}
		return itemCarritoRepositorio.save(itemCarrito);
	}
	
	@Transactional
	public ItemPedido update(Long id, ItemPedido itemCarrito) throws Exception {
		ItemPedido itemCarritoEncontrado = itemCarritoRepositorio.findById(id).orElse(null);
		if(itemCarritoEncontrado == null) {
			throw new Exception("Este Item no se encuentra registrado en el sistema.");
		}
		itemCarritoEncontrado.setProducto(itemCarrito.getProducto());
		itemCarritoEncontrado.setCantidad(itemCarrito.getCantidad());
		return itemCarritoRepositorio.save(itemCarritoEncontrado);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!itemCarritoRepositorio.existsById(id)) {
			throw new Exception("Este Item no se encuentra registrado en el sistema.");
		}
		itemCarritoRepositorio.deleteById(id);
	}
}
