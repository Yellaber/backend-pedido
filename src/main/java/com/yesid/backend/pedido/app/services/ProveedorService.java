package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Proveedor;
import com.yesid.backend.pedido.app.repositories.IProveedorRepository;

@Service
public class ProveedorService {
	
	@Autowired
	private IProveedorRepository proveedorRepositorio;
	
	@Transactional(readOnly=true)
	public List<Proveedor> findAll() throws Exception {
		List<Proveedor> proveedores = proveedorRepositorio.findAll();
		if(proveedores == null || proveedores.isEmpty()) {
			throw new Exception("No existen Proveedores registrados en el sistema.");
		}
		return proveedores;
	}
	
	@Transactional(readOnly=true)
	public Proveedor findById(Long id) throws Exception {
		Proveedor proveedor = proveedorRepositorio.findById(id).orElse(null);
		if(proveedor == null) {
			throw new Exception("Este Proveedor no se encuentra registrado en el sistema.");
		}
		return proveedor;
	}
	
	@Transactional(readOnly=true)
	public List<Proveedor> findByNombre(String nombre) throws Exception {
		List<Proveedor> proveedores = proveedorRepositorio.findByNombre(nombre);
		if(proveedores == null || proveedores.isEmpty()) {
			throw new Exception("No se encontraron Proveedores que concidan con: " + nombre + ".");
		}
		return proveedores;
	}
	
	@Transactional
	public Proveedor save(Proveedor proveedor) throws Exception {
		if(proveedorRepositorio.existsById(proveedor.getId())) {
			throw new Exception("Este Proveedor ya se encuentra registrado en el sistema.");
		}
		return proveedorRepositorio.save(proveedor);
	}
	
	@Transactional
	public Proveedor update(Long id, Proveedor proveedor) throws Exception {
		Proveedor proveedorEncontrado = proveedorRepositorio.findById(id).orElse(null);
		if(proveedorEncontrado == null) {
			throw new Exception("Este Proveedor no se encuentra registrado en el sistema.");
		}
		proveedorEncontrado.setNit(proveedor.getNit());
		proveedorEncontrado.setNombre(proveedor.getNombre());
		proveedorEncontrado.setDireccion(proveedor.getDireccion());
		proveedorEncontrado.setTelefono(proveedor.getTelefono());
		proveedorEncontrado.setEmail(proveedor.getEmail());
		return proveedorRepositorio.save(proveedorEncontrado);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!proveedorRepositorio.existsById(id)) {
			throw new Exception("Este Proveedor no se encuentra registrado en el sistema.");
		}
		proveedorRepositorio.deleteById(id);
	}
}
