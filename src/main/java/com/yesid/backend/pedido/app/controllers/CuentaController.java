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

import com.yesid.backend.pedido.app.entities.Cuenta;
import com.yesid.backend.pedido.app.services.CuentaService;
import com.yesid.backend.pedido.app.validations.Validacion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
	
	@Autowired
	private CuentaService cuentaService;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		try {
			return ResponseEntity.ok(cuentaService.findAll());
		} catch(Exception ex) {
			return Validacion.registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(cuentaService.findById(id));
		} catch(Exception ex) {
			return Validacion.registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody Cuenta cuenta, BindingResult result) {
		try {
			if(result.hasErrors()) {
				return Validacion.registrarErrorCampo(result);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.save(cuenta));
		} catch(Exception ex) {
			return Validacion.registrarError(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Cuenta cuenta, BindingResult result) {
		try {
			if(result.hasErrors()) {
				return Validacion.registrarErrorCampo(result);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.update(id, cuenta));
		} catch(Exception ex) {
			return Validacion.registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		try {
			cuentaService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch(Exception ex) {
			return Validacion.registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
