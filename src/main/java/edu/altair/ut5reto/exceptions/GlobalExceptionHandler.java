package edu.altair.ut5reto.exceptions;

import java.net.URI;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(ProductoNotFoundException.class)
	 public ProblemDetail metodo(ProductoNotFoundException ex) 
	{
	 	ProblemDetail problem = ProblemDetail.forStatusAndDetail(
	 		HttpStatus.NOT_FOUND,
	 		ex.getMessage()
	 	);

	 	problem.setTitle("Entidad no existente");
	 	problem.setType(URI.create("https://api.crm.altair.edu/errors/conflict"));
		problem.setProperty("timestamp", Instant.now());
	 	return problem;
	 }
	
	@ExceptionHandler(CodigoAlreadyExistsException.class)
	 public ProblemDetail metodo(CodigoAlreadyExistsException ex) 
	{
	 	ProblemDetail problem = ProblemDetail.forStatusAndDetail(
	 		HttpStatus.CONFLICT,
	 		ex.getMessage()
	 	);

	 	problem.setTitle("Conflicto de datos. Codigo Sku duplicado");
	 	problem.setType(URI.create("https://api.crm.altair.edu/errors/conflict"));
		problem.setProperty("timestamp", Instant.now());
	 	return problem;
	 }
}
