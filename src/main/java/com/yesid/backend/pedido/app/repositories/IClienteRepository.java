package com.yesid.backend.pedido.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yesid.backend.pedido.app.entities.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
	
	Cliente findByCedula(String cedula);
}
