package com.yesid.backend.pedido.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yesid.backend.pedido.app.entities.Proveedor;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor, Long> {
	
	@Query("select p from Proveedor p where lower(p.nombre) like lower(concat(:nombre, '%'))")
	List<Proveedor> findByNombreLike(String nombre);
}
