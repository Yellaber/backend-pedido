package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Catalogo;
import com.yesid.backend.pedido.app.repositories.ICatalogoRepository;

@Service
public class CatalogoService {
	
	@Autowired
	private ICatalogoRepository catalogoRepositorio;
	
	@Transactional(readOnly=true)
	public List<Catalogo> findAll() throws Exception {
		List<Catalogo> catalogos = catalogoRepositorio.findAll();
		if(catalogos == null || catalogos.isEmpty()) {
			throw new Exception("No existen Catalogos registrados en el sistema.");
		}
		return catalogos;
	}
	
	@Transactional(readOnly=true)
	public Catalogo findById(Long id) throws Exception {
		Catalogo catalogo = catalogoRepositorio.findById(id).orElse(null);
		if(catalogo == null) {
			throw new Exception("Este Catalogo no se encuentra registrado en el sistema.");
		}
		return catalogo;
	}
	
	@Transactional
	public Catalogo save(Catalogo catalogo) throws Exception {
		if(catalogo.getId() != null) {
			if(catalogoRepositorio.existsById(catalogo.getId())) {
				throw new Exception("Este Catalogo ya se encuentra registrado en el sistema.");
			}
		}
		return catalogoRepositorio.save(catalogo);
	}
	
	@Transactional
	public Catalogo update(Long id, Catalogo catalogo) throws Exception {
		Catalogo catalogoEncontrado = catalogoRepositorio.findById(id).orElse(null);
		if(catalogoEncontrado == null) {
			throw new Exception("Este Catalogo no se encuentra registrado en el sistema.");
		}
		catalogoEncontrado.setNombre(catalogo.getNombre());
		catalogoEncontrado.setDescripcion(catalogo.getDescripcion());
		return catalogoRepositorio.save(catalogoEncontrado);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!catalogoRepositorio.existsById(id)) {
			throw new Exception("Este Catalogo no se encuentra registrado en el sistema.");
		}
		catalogoRepositorio.deleteById(id);
	}
}
