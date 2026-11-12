package edu.altair.ut5reto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.altair.ut5reto.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>
{
	boolean existsByCodigoSku(String codSku);
	Optional<Producto> findByCodigoSku(String codSku);
	void deleteByCodigoSku(String codSku);
}
