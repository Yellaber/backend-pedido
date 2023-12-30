package com.yesid.backend.pedido.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yesid.backend.pedido.app.entities.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
	
	List<Producto> findByNombreLike(String nombre);	
}
