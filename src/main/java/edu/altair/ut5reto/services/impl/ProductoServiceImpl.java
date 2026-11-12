package edu.altair.ut5reto.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.altair.ut5reto.dtos.ProductoRequestDto;
import edu.altair.ut5reto.dtos.ProductoResponseDto;
import edu.altair.ut5reto.entities.Producto;
import edu.altair.ut5reto.exceptions.CodigoAlreadyExistsException;
import edu.altair.ut5reto.exceptions.ProductoNotFoundException;
import edu.altair.ut5reto.repositories.ProductoRepository;
import edu.altair.ut5reto.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService
{
	private final ProductoRepository repo;

	public ProductoServiceImpl(ProductoRepository repo) {this.repo = repo;}

	@Override
	@Transactional(readOnly = true)
	public List<ProductoResponseDto> obtenerTodo()
	{
		return repo.findAll()
				.stream()
				.map(this::mapToResponseDto)
				.toList();
	}

	@Override
	@Transactional
	public ProductoResponseDto crear(ProductoRequestDto requestDto) 
	{
		if(repo.existsByCodigoSku(requestDto.codigoSku()))
		{
			throw new CodigoAlreadyExistsException("El código ya existe, imposible crear");
		}
		
		Producto p = new Producto(
				null,
				requestDto.codigoSku(),
				requestDto.nombre(),
				requestDto.descripcion(),
				requestDto.precio(),
				requestDto.stock(),
				requestDto.fechaAlta()
		);
		
		return mapToResponseDto(repo.save(p));
	}

	@Override
	@Transactional
	public ProductoResponseDto actualizar(String codSku, ProductoRequestDto dto) 
	{
		Producto p = repo.findByCodigoSku(codSku)
				.orElseThrow(() -> new ProductoNotFoundException("El producto no existe"));
		
		p.setCodigoSku(dto.codigoSku());
		p.setNombre(dto.nombre());
		p.setDescripcion(dto.descripcion());
		p.setPrecio(dto.precio());
		p.setStock(dto.stock());
		p.setFechaAlta(dto.fechaAlta());
		
		return mapToResponseDto(repo.save(p));
	}

	@Override
	@Transactional
	public void eliminar(String codSku) 
	{
		Producto p = repo.findByCodigoSku(codSku)
				.orElseThrow(() -> new ProductoNotFoundException("El producto no existe"));

		repo.deleteByCodigoSku(p.getCodigoSku());
	}
	

	public ProductoResponseDto mapToResponseDto(Producto prod)
	{
		return new ProductoResponseDto(
					prod.getId(),
					prod.getCodigoSku(),
					prod.getNombre(),
					prod.getDescripcion(),
					prod.getPrecio(),
					prod.getStock(),
					prod.getFechaAlta()
				);
	}
}
