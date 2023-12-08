package com.yesid.backend.pedido.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yesid.backend.pedido.app.entities.Catalogo;

@Repository
public interface ICatalogoRepository extends JpaRepository<Catalogo, Long> {
	
}
