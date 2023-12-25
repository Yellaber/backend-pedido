package com.yesid.backend.pedido.app.validations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class Validacion {
	
	public static ResponseEntity<?> registrarErrorCampo(BindingResult result) {
		Map<String, String> errores = new HashMap<String, String>();
		result.getFieldErrors().forEach(error -> {
			errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
	
	public static ResponseEntity<?> registrarError(String mensaje, HttpStatus estado) {
		Map<String, String> error = new HashMap<String, String>();
		error.put("Error", mensaje);
		return ResponseEntity.status(estado).body(error);
	}
}
