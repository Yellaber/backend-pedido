package com.yesid.backend.pedido.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yesid.backend.pedido.app.entities.Pago;
import com.yesid.backend.pedido.app.repositories.IPagoRepository;

@Service
public class PagoService {
	
	@Autowired
	private IPagoRepository pagoRepositorio;
	
	@Transactional(readOnly=true)
	public List<Pago> findAll() throws Exception {
		List<Pago> pagos = pagoRepositorio.findAll();
		if(pagos == null || pagos.isEmpty()) {
			throw new Exception("No existen Pagos registrados en el sistema.");
		}
		return pagos;
	}
	
	@Transactional(readOnly=true)
	public Pago findById(Long id) throws Exception {
		Pago pago = pagoRepositorio.findById(id).orElse(null);
		if(pago == null) {
			throw new Exception("Este Pago no se encuentra registrado en el sistema.");
		}
		return pago;
	}
	
	@Transactional
	public Pago save(Pago pago) throws Exception {
		if(pago.getId() != null) {
			if(pagoRepositorio.existsById(pago.getId())) {
				throw new Exception("Este Pago ya se encuentra registrado en el sistema.");
			}
		}
		return pagoRepositorio.save(pago);
	}
	
	@Transactional
	public Pago update(Long id, Pago pago) throws Exception {
		Pago pagoEncontrado = pagoRepositorio.findById(id).orElse(null);
		if(pagoEncontrado == null) {
			throw new Exception("Este Pago no se encuentra registrado en el sistema.");
		}
		pagoEncontrado.setFecha(pago.getFecha());
		pagoEncontrado.setPedido(pago.getPedido());
		return pagoRepositorio.save(pagoEncontrado);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		if(!pagoRepositorio.existsById(id)) {
			throw new Exception("Este Pago no se encuentra registrado en el sistema.");
		}
		pagoRepositorio.deleteById(id);
	}
}
