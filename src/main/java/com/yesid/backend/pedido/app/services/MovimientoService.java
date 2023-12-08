package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Movimiento;
import com.yesid.backend.pedido.app.repositories.IMovimientoRepository;

@Service
public class MovimientoService {
	
	@Autowired
	private IMovimientoRepository movimientoRepositorio;
	
	@Transactional(readOnly=true)
	public List<Movimiento> findAll() throws Exception {
		List<Movimiento> movimientos = movimientoRepositorio.findAll();
		if(movimientos == null || movimientos.isEmpty()) {
			throw new Exception("No existen Movimientos registrados en el sistema.");
		}
		return movimientos;
	}
	
	@Transactional(readOnly=true)
	public Movimiento findById(Long id) throws Exception {
		Movimiento movimiento = movimientoRepositorio.findById(id).orElse(null);
		if(movimiento == null) {
			throw new Exception("Este Movimiento no se encuentra registrado en el sistema.");
		}
		return movimiento;
	}
	
	@Transactional
	public Movimiento save(Movimiento movimiento) throws Exception {
		if(movimientoRepositorio.existsById(movimiento.getId())) {
			throw new Exception("Este Movimiento ya se encuentra registrado en el sistema.");
		}
		return movimientoRepositorio.save(movimiento);
	}
	
	@Transactional
	public Movimiento update(Long id, Movimiento movimiento) throws Exception {
		Movimiento movimientoEncontrado = movimientoRepositorio.findById(id).orElse(null);
		if(movimientoEncontrado == null) {
			throw new Exception("Este Movimiento no se encuentra registrado en el sistema.");
		}
		movimientoEncontrado.setTipo(movimiento.getTipo());
		movimientoEncontrado.setCantidad(movimiento.getCantidad());
		movimientoEncontrado.setFecha(movimiento.getFecha());
		return movimientoRepositorio.save(movimientoEncontrado);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!movimientoRepositorio.existsById(id)) {
			throw new Exception("Este Movimiento no se encuentra registrado en el sistema.");
		}
		movimientoRepositorio.deleteById(id);
	}
}
