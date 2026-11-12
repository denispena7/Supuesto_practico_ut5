package edu.altair.ut5reto.services;

import java.util.List;

import edu.altair.ut5reto.dtos.ProductoRequestDto;
import edu.altair.ut5reto.dtos.ProductoResponseDto;

public interface ProductoService 
{
	List<ProductoResponseDto> obtenerTodo();
	ProductoResponseDto crear(ProductoRequestDto requestDto);
	ProductoResponseDto actualizar(String codSku, ProductoRequestDto dto);
	void eliminar(String codSku);
}
