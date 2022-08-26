package com.tip.gestionBares.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tip.gestionBares.dto.ProductoDto;
import com.tip.gestionBares.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@PostMapping
	public ResponseEntity<ProductoDto> create(@RequestBody ProductoDto productoDto){
		productoDto = this.productoService.create(productoDto);
		
		return new ResponseEntity<ProductoDto>(productoDto, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/productoByCategory")
	public ResponseEntity<List<ProductoDto>> findBycategory(@RequestParam String categoria) throws NotFoundException{
		List<ProductoDto> productosDto = this.productoService.findByCategory(categoria);
		if(productosDto.size() <= 0) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<ProductoDto>>(productosDto, HttpStatus.OK);
	}
	
	@GetMapping("/productoAll")
	public ResponseEntity<List<ProductoDto>> findAll() throws NotFoundException{
		List<ProductoDto> productosDto = this.productoService.findByAll();
		if(productosDto.size() <= 0) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<ProductoDto>>(productosDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<List<ProductoDto>> delete(@PathVariable(value ="id") Long id) throws NotFoundException{
		List<ProductoDto> productosDto = this.productoService.delete(id);
		if(productosDto.size() <= 0) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<ProductoDto>>(productosDto, HttpStatus.OK);
	}
	
}
