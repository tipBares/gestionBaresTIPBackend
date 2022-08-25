package com.tip.gestionBares.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
