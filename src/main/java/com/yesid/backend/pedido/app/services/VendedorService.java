package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Vendedor;
import com.yesid.backend.pedido.app.repositories.IVendedorRepository;

@Service
public class VendedorService {
	
	@Autowired
	private IVendedorRepository vendedorRepositorio;
	
	@Transactional(readOnly=true)
	public List<Vendedor> findAll() throws Exception {
		List<Vendedor> vendedores = vendedorRepositorio.findAll();
		if(vendedores == null || vendedores.isEmpty()) {
			throw new Exception("No existen Vendedores registrados en el sistema.");
		}
		return vendedores;
	}
	
	@Transactional(readOnly=true)
	public Vendedor findById(Long id) throws Exception {
		Vendedor vendedor = vendedorRepositorio.findById(id).orElse(null);
		if(vendedor == null) {
			throw new Exception("Este Vendedor no se encuentra registrado en el sistema.");
		}
		return vendedor;
	}
	
	@Transactional
	public Vendedor save(Vendedor vendedor) throws Exception {
		if(vendedorRepositorio.existsById(vendedor.getId())) {
			throw new Exception("Este Vendedor ya se encuentra registrado en el sistema.");
		}
		return vendedorRepositorio.save(vendedor);
	}
	
	@Transactional
	public Vendedor update(Long id, Vendedor vendedor) throws Exception {
		Vendedor vendedorEncontrado = vendedorRepositorio.findById(id).orElse(null);
		if(vendedorEncontrado == null) {
			throw new Exception("Este Vendedor no se encuentra registrado en el sistema.");
		}
		vendedorEncontrado.setNombre(vendedor.getNombre());
		vendedorEncontrado.setApellidos(vendedor.getApellidos());
		return vendedorRepositorio.save(vendedorEncontrado);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!vendedorRepositorio.existsById(id)) {
			throw new Exception("Este Vendedor no se encuentra registrado en el sistema.");
		}
		vendedorRepositorio.deleteById(id);
	}
}
