package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Mesa;
import com.yesid.backend.pedido.app.repositories.IMesaRepository;

@Service
public class MesaService {
	
	@Autowired
	private IMesaRepository mesaRepositorio;
	
	@Transactional(readOnly=true)
	public List<Mesa> findAll() throws Exception {
		List<Mesa> mesas = mesaRepositorio.findAll();
		if(mesas == null || mesas.isEmpty()) {
			throw new Exception("No existen Mesas registradas en el sistema.");
		}
		return mesas;
	}
	
	@Transactional(readOnly=true)
	public Mesa findById(Long id) throws Exception {
		Mesa mesa = mesaRepositorio.findById(id).orElse(null);
		if(mesa == null) {
			throw new Exception("Esta Mesa no se encuentra registrada en el sistema.");
		}
		return mesa;
	}
	
	@Transactional
	public Mesa save(Mesa mesa) throws Exception {
		if(mesaRepositorio.existsById(mesa.getId())) {
			throw new Exception("Esta Mesa ya se encuentra registrada en el sistema.");
		}
		return mesaRepositorio.save(mesa);
	}
	
	@Transactional
	public Mesa update(Long id, Mesa mesa) throws Exception {
		Mesa mesaEncontrado = mesaRepositorio.findById(id).orElse(null);
		if(mesaEncontrado == null) {
			throw new Exception("Esta Mesa no se encuentra registrada en el sistema.");
		}
		mesaEncontrado.setNombre(mesa.getNombre());
		return mesaRepositorio.save(mesaEncontrado);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!mesaRepositorio.existsById(id)) {
			throw new Exception("Esta Mesa no se encuentra registrada en el sistema.");
		}
		mesaRepositorio.deleteById(id);
	}
}
