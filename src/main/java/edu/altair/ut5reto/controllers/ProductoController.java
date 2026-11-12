package edu.altair.ut5reto.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.altair.ut5reto.dtos.ProductoRequestDto;
import edu.altair.ut5reto.dtos.ProductoResponseDto;
import edu.altair.ut5reto.services.ProductoService;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController 
{
	private final ProductoService service;

	public ProductoController(ProductoService service) {this.service = service;}
	
	@GetMapping
	public ResponseEntity<List<ProductoResponseDto>> obtener()
	{
		return ResponseEntity.ok(service.obtenerTodo());
	}
	
	@PostMapping
	public ResponseEntity<ProductoResponseDto> crear(@RequestBody ProductoRequestDto dto)
	{
		ProductoResponseDto p = service.crear(dto); 
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(p);
	}
	
	@PutMapping("/act/{codSku}")
	public ResponseEntity<ProductoResponseDto> actualizar(
			@PathVariable String codSku, @RequestBody ProductoRequestDto dto)
	{
		ProductoResponseDto p = service.actualizar(codSku, dto); 
		
		return ResponseEntity.ok(p);
	}
	
	@DeleteMapping("/del/{codSku}")
	public ResponseEntity<Void> eliminar(@PathVariable String codSku)
	{
		service.eliminar(codSku);
		return ResponseEntity.noContent().build();
	}
}