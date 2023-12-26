package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Cuenta;
import com.yesid.backend.pedido.app.repositories.ICuentaRepository;

@Service
public class CuentaService {
	
	@Autowired
	private ICuentaRepository cuentaRepositorio;
	
	@Transactional(readOnly=true)
	public List<Cuenta> findAll() throws Exception {
		List<Cuenta> cuentas = cuentaRepositorio.findAll();
		if(cuentas == null || cuentas.isEmpty()) {
			throw new Exception("No existen Cuentas registradas en el sistema.");
		}
		return cuentas;
	}
	
	@Transactional(readOnly=true)
	public Cuenta findById(Long id) throws Exception {
		Cuenta cuenta = cuentaRepositorio.findById(id).orElse(null);
		if(cuenta == null) {
			throw new Exception("Esta Cuenta no se encuentra registrada en el sistema.");
		}
		return cuenta;
	}
	
	@Transactional
	public Cuenta save(Cuenta cuenta) throws Exception {
		if(cuenta.getId() != null) {
			if(cuentaRepositorio.existsById(cuenta.getId())) {
				throw new Exception("Esta Cuenta ya se encuentra registrada en el sistema.");
			}
		}
		return cuentaRepositorio.save(cuenta);
	}
	
	@Transactional
	public Cuenta update(Long id, Cuenta cuenta) throws Exception {
		Cuenta cuentaEncontrada = cuentaRepositorio.findById(id).orElse(null);
		if(cuentaEncontrada == null) {
			throw new Exception("Esta Cuenta no se encuentra registrada en el sistema.");
		}
		cuentaEncontrada.setNombre(cuenta.getNombre());
		cuentaEncontrada.setPassword(cuenta.getPassword());
		return cuentaRepositorio.save(cuentaEncontrada);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!cuentaRepositorio.existsById(id)) {
			throw new Exception("Esta Cuenta no se encuentra registrada en el sistema.");
		}
		cuentaRepositorio.deleteById(id);
	}
}
