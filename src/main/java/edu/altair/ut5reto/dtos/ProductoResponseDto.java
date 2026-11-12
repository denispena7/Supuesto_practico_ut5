package edu.altair.ut5reto.dtos;

import java.time.LocalDate;

public record ProductoResponseDto(
		Long id,
		String codigoSku,
		String nombre,
		String descripcion,
		double precio,
		int stock,
		LocalDate fechaAlta
		// String fechaAlta
) {}
