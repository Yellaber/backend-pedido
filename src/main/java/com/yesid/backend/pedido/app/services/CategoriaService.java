package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Categoria;
import com.yesid.backend.pedido.app.repositories.ICategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private ICategoriaRepository categoriaRepositorio;
	
	@Transactional(readOnly=true)
	public List<Categoria> findAll() throws Exception {
		List<Categoria> etiquetas = categoriaRepositorio.findAll();
		if(etiquetas == null || etiquetas.isEmpty()) {
			throw new Exception("No existen Categorias registradas en el sistema.");		
		}
		return etiquetas;
	}
	
	@Transactional(readOnly=true)
	public Categoria findById(Long id) throws Exception {
		Categoria etiqueta = categoriaRepositorio.findById(id).orElse(null);
		if(etiqueta == null) {
			throw new Exception("Esta Categoria no se encuentra registrada en el sistema.");
		}
		return etiqueta;
	}
	
	@Transactional
	public Categoria save(Categoria etiqueta) throws Exception {
		if(categoriaRepositorio.existsById(etiqueta.getId())) {
			throw new Exception("Esta Categoria ya se encuentra registrada en el sistema.");
		}
		return categoriaRepositorio.save(etiqueta);
	}
	
	@Transactional
	public Categoria update(Long id, Categoria etiqueta) throws Exception {
		Categoria etiquetaEncontrada = categoriaRepositorio.findById(id).orElse(null);
		if(etiquetaEncontrada == null) {
			throw new Exception("Esta Categoria no se encuentra registrada en el sistema.");
		}
		etiquetaEncontrada.setNombre(etiqueta.getNombre());
		etiquetaEncontrada.setDescripcion(etiqueta.getDescripcion());
		return categoriaRepositorio.save(etiquetaEncontrada);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!categoriaRepositorio.existsById(id)) {
			throw new Exception("Esta Categoria no se encuentra registrada en el sistema.");
		}
		categoriaRepositorio.deleteById(id);
	}
}
