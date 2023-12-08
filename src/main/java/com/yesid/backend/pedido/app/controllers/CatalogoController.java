package com.yesid.backend.pedido.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yesid.backend.pedido.app.entities.Catalogo;
import com.yesid.backend.pedido.app.services.CatalogoService;
import com.yesid.backend.pedido.app.validations.Validacion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catalogos")
public class CatalogoController {
	
	@Autowired
	private CatalogoService catalogoService;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		try {
			return ResponseEntity.ok(catalogoService.findAll());
		} catch(Exception ex) {
			return Validacion.registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(catalogoService.findById(id));
		} catch(Exception ex) {
			return Validacion.registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody Catalogo catalogo, BindingResult result) {
		try {
			if(result.hasErrors()) {
				return Validacion.registrarErrorCampo(result);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(catalogoService.save(catalogo));
		} catch(Exception ex) {
			return Validacion.registrarError(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Catalogo catologo, BindingResult result) {
		try {
			if(result.hasErrors()) {
				return Validacion.registrarErrorCampo(result);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(catalogoService.update(id, catologo));
		} catch(Exception ex) {
			return Validacion.registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		try {
			catalogoService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch(Exception ex) {
			return Validacion.registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
