package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Producto;
import com.yesid.backend.pedido.app.repositories.IProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private IProductoRepository productoRepositorio;
	
	@Transactional(readOnly=true)
	public List<Producto> findAll() throws Exception {
		List<Producto> productos = productoRepositorio.findAll();
		if(productos == null || productos.isEmpty()) {
			throw new Exception("No existen Productos registrados en el sistema.");
		}
		return productos;
	}
	
	@Transactional(readOnly=true)
	public Producto findById(Long id) throws Exception {
		Producto producto = productoRepositorio.findById(id).orElse(null);
		if(producto == null) {
			throw new Exception("Este Producto no se encuentra registrado en el sistema.");
		}
		return producto;
	}
	
	@Transactional(readOnly=true)
	public List<Producto> findAllByNombreLike(String nombre) throws Exception {
		List<Producto> productos = productoRepositorio.findAllByNombreLike(nombre);
		if(productos == null || productos.isEmpty()) {
			throw new Exception("No se encontraron Productos que concidan con: " + nombre + ".");
		}
		return productos;
	}
	
	@Transactional
	public Producto save(Producto producto) throws Exception {
		if(productoRepositorio.existsById(producto.getId())) {
			throw new Exception("Este Producto ya se encuentra registrado en el sistema.");
		}
		return productoRepositorio.save(producto);
	}
	
	@Transactional
	public Producto update(Long id, Producto producto) throws Exception {
		Producto productoEncontrado = productoRepositorio.findById(id).orElse(null);
		if(productoEncontrado == null) {
			throw new Exception("Este Producto no se encuentra registrado en el sistema.");
		}
		productoEncontrado.setCodigo(producto.getCodigo());
		productoEncontrado.setNombre(producto.getNombre());
		productoEncontrado.setPrecio(producto.getPrecio());
		return productoRepositorio.save(productoEncontrado);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!productoRepositorio.existsById(id)) {
			throw new Exception("Este Producto no se encuentra registrado en el sistema.");
		}
		productoRepositorio.deleteById(id);
	}
}
