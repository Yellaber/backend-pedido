package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Cliente;
import com.yesid.backend.pedido.app.repositories.IClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private IClienteRepository clienteRepositorio;
	
	@Transactional(readOnly=true)
	public List<Cliente> findAll() throws Exception {
		List<Cliente> clientes = clienteRepositorio.findAll();
		if(clientes == null || clientes.isEmpty()) {
			throw new Exception("No existen Clientes registrados en el sistema.");
		}
		return clienteRepositorio.findAll();
	}
	
	@Transactional(readOnly=true)
	public Cliente findById(Long id) throws Exception {
		Cliente cliente = clienteRepositorio.findById(id).orElse(null);
		if(cliente == null) {
			throw new Exception("Este Cliente no se encuentra registrado en el sistema.");
		}
		return cliente;
	}
	
	@Transactional(readOnly=true)
	public Cliente findByCedula(String cedula) throws Exception {
		Cliente cliente = clienteRepositorio.findByCedula(cedula);
		if(cliente == null) {
			throw new Exception("Este Cliente no se encuentra registrado en el sistema.");
		}
		return cliente;
	}
	
	@Transactional
	public Cliente save(Cliente cliente) throws Exception {
		if(cliente.getId() != null) {
			if(clienteRepositorio.existsById(cliente.getId())) {
				throw new Exception("Este Cliente ya se encuentra registrado en el sistema.");
			}
		}
		return clienteRepositorio.save(cliente);
	}
	
	@Transactional
	public Cliente update(Long id, Cliente cliente) throws Exception {
		Cliente clienteEncontrado = clienteRepositorio.findById(id).orElse(null);
		if(clienteEncontrado == null) {
			throw new Exception("Este Cliente no se encuentra registrado en el sistema.");
		}
		clienteEncontrado.setCedula(cliente.getCedula());
		clienteEncontrado.setNombre(cliente.getNombre());
		clienteEncontrado.setApellidos(cliente.getApellidos());
		clienteEncontrado.setCiudad(cliente.getCiudad());
		clienteEncontrado.setDireccion(cliente.getDireccion());
		clienteEncontrado.setTelefono(cliente.getTelefono());
		clienteEncontrado.setEmail(cliente.getEmail());
		return clienteRepositorio.save(clienteEncontrado);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!clienteRepositorio.existsById(id)) {
			throw new Exception("Este Cliente no se encuentra registrado en el sistema.");
		}
		clienteRepositorio.deleteById(id);
	}
}
