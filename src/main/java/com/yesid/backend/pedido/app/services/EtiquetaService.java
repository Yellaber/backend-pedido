package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Categoria;
import com.yesid.backend.pedido.app.repositories.IEtiquetaRepository;

@Service
public class EtiquetaService {
	
	@Autowired
	private IEtiquetaRepository etiquetaRepositorio;
	
	@Transactional(readOnly=true)
	public List<Categoria> findAll() throws Exception {
		List<Categoria> etiquetas = etiquetaRepositorio.findAll();
		if(etiquetas == null || etiquetas.isEmpty()) {
			throw new Exception("No existen Etiquetas registradas en el sistema.");		
		}
		return etiquetas;
	}
	
	@Transactional(readOnly=true)
	public Categoria findById(Long id) throws Exception {
		Categoria etiqueta = etiquetaRepositorio.findById(id).orElse(null);
		if(etiqueta == null) {
			throw new Exception("Esta Etiqueta no se encuentra registrada en el sistema.");
		}
		return etiqueta;
	}
	
	@Transactional
	public Categoria save(Categoria etiqueta) throws Exception {
		if(etiquetaRepositorio.existsById(etiqueta.getId())) {
			throw new Exception("Esta Etiqueta ya se encuentra registrada en el sistema.");
		}
		return etiquetaRepositorio.save(etiqueta);
	}
	
	@Transactional
	public Categoria update(Long id, Categoria etiqueta) throws Exception {
		Categoria etiquetaEncontrada = etiquetaRepositorio.findById(id).orElse(null);
		if(etiquetaEncontrada == null) {
			throw new Exception("Esta Etiqueta no se encuentra registrada en el sistema.");
		}
		etiquetaEncontrada.setNombre(etiqueta.getNombre());
		etiquetaEncontrada.setDescripcion(etiqueta.getDescripcion());
		return etiquetaRepositorio.save(etiquetaEncontrada);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!etiquetaRepositorio.existsById(id)) {
			throw new Exception("Esta Etiqueta no se encuentra registrada en el sistema.");
		}
		etiquetaRepositorio.deleteById(id);
	}
}
