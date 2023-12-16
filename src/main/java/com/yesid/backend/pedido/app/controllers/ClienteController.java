package com.yesid.backend.pedido.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.yesid.backend.pedido.app.entities.Cliente;
import com.yesid.backend.pedido.app.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("appi/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		try {
			List<Cliente> clientes = clienteService.findAll();
			return ResponseEntity.ok(clientes);
		} catch(Exception ex) {
			return registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		try {
			Cliente cliente = clienteService.findById(id);
			return ResponseEntity.ok(cliente);
		} catch(Exception ex) {
			return registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{cedula}")
	public ResponseEntity<?> buscarPorCedula(@PathVariable String cedula) {
		try {
			Cliente cliente = clienteService.findByCedula(cedula);
			return ResponseEntity.ok(cliente);
		} catch(Exception ex) {
			return registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody Cliente cliente, BindingResult result) {
		try {
			if(result.hasErrors()) {
				return registrarErrorCampo(result);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
		} catch(Exception ex) {
			return registrarError(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente, BindingResult result) {
		try {
			if(result.hasErrors()) {
				return registrarErrorCampo(result);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.update(id, cliente));
		} catch(Exception ex) {
			return registrarError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		try {
			clienteService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	private ResponseEntity<?> registrarErrorCampo(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(error -> {
			errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
	
	private ResponseEntity<?> registrarError(String mensaje, HttpStatus estado) {
		Map<String, String> error = new HashMap<>();
		error.put("Error", mensaje);
		return ResponseEntity.status(estado).build();
	}
}
