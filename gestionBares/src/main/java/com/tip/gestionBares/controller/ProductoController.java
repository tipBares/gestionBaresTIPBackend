package com.tip.gestionBares.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tip.gestionBares.dto.ProductoDto;
import com.tip.gestionBares.model.Producto;
import com.tip.gestionBares.service.ProductoService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = {"http://localhost:3000", "*"}, maxAge = 3600)
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@PostMapping("{id}")
	public ResponseEntity<ProductoDto> create(@RequestBody ProductoDto productoDto, @PathVariable("id") Long idCategoria){
		productoDto = this.productoService.create(productoDto, idCategoria);
		
		return new ResponseEntity<ProductoDto>(productoDto, HttpStatus.CREATED);
		
	}

	@GetMapping("/nombre/{pag}")
	public ResponseEntity<Page<Producto>> findByName(
			@RequestParam String nombre, 
			@PathVariable(value = "pag" ) int page,
            @RequestParam(defaultValue = "7") int size,
            @RequestParam(defaultValue = "nombre") String order) throws NotFoundException{
		Page<Producto> productos = this.productoService.findByName(nombre, PageRequest.of(page, size, Sort.by(order)));
		
		return new ResponseEntity<Page<Producto>>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/nombre")
	public ResponseEntity<List<ProductoDto>> findByName(@RequestParam String nombre) throws NotFoundException{
		List<ProductoDto> productos = this.productoService.findByNameAll(nombre);
		return new ResponseEntity<List<ProductoDto>>(productos, HttpStatus.OK);
	}
	
	@PutMapping("{id}/{idCategoria}")
	public ResponseEntity<ProductoDto> updateById(@RequestBody ProductoDto producto, @PathVariable("id") Long id, @PathVariable("idCategoria") Long idCategoria) throws NotFoundException {
		ProductoDto productoDto = this.productoService.update(producto, id, idCategoria);
		if(productoDto == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<ProductoDto>(productoDto, HttpStatus.OK);
	}

	
	@GetMapping("/categoria/{id}/{pag}")
	public ResponseEntity<Page<Producto>> findBycategory(
			@PathVariable("id") Long idCategoria,
			@PathVariable(value = "pag" ) int page,
            @RequestParam(defaultValue = "7") int size,
            @RequestParam(defaultValue = "nombre") String order) throws NotFoundException{
		Page<Producto> productos = this.productoService.findByCategory(idCategoria, PageRequest.of(page, size, Sort.by(order)));
		
		return new ResponseEntity<Page<Producto>>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<List<ProductoDto>> findBycategoryAll(
			@PathVariable("id") Long idCategoria,
            @RequestParam(defaultValue = "nombre") String order) throws NotFoundException{
		List<ProductoDto> productos = this.productoService.findByCategoryAll(idCategoria);
		
		return new ResponseEntity<List<ProductoDto>>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/productoAll")
	public ResponseEntity<List<ProductoDto>> findAll() throws NotFoundException{
		List<ProductoDto> productosDto = this.productoService.findByAll();
		
		
		return new ResponseEntity<List<ProductoDto>>(productosDto, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<List<ProductoDto>> delete(@PathVariable(value ="id") Long id) throws NotFoundException{
		List<ProductoDto> productosDto = this.productoService.delete(id);
		return new ResponseEntity<List<ProductoDto>>(productosDto, HttpStatus.OK);
	}
	
	@GetMapping("/{pag}")
	public ResponseEntity<Page<Producto>> paginas(
			@PathVariable(value = "pag" ) int page,
            @RequestParam(defaultValue = "7") int size,
            @RequestParam(defaultValue = "nombre") String order
			) throws NotFoundException{
		Page<Producto> productos = productoService.paginas(PageRequest.of(page, size, Sort.by(order)));

		return new ResponseEntity<Page<Producto>>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ProductoDto> findById(@PathVariable("id") Long id) throws NotFoundException {
		ProductoDto productoDto = this.productoService.findById(id);
		if (productoDto == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<ProductoDto>(productoDto, HttpStatus.OK);
	}
}
