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
import com.tip.gestionBares.dto.CategoriaDto;
import com.tip.gestionBares.model.Categoria;
import com.tip.gestionBares.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = {"http://localhost:3000", "*"}, maxAge = 3600)
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping
	public ResponseEntity<CategoriaDto> create(@RequestBody CategoriaDto categoria) {
		CategoriaDto categoriaDto = this.categoriaService.create(categoria);

		return new ResponseEntity<CategoriaDto>(categoriaDto, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDto>> findAll() throws NotFoundException{
		List<CategoriaDto> categoriasDto = this.categoriaService.getCategorias();
		if(categoriasDto.size() <= 0) {
			throw new NotFoundException();
		}
		return new ResponseEntity<List<CategoriaDto>>(categoriasDto, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<List<CategoriaDto>> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
		List<CategoriaDto> categoriasDto = this.categoriaService.delete(id);
		return new ResponseEntity<List<CategoriaDto>>(categoriasDto, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<CategoriaDto> updateById(@RequestBody CategoriaDto categoriaDto, @PathVariable("id") Long id) throws NotFoundException {
		CategoriaDto categoriaDtoAux = this.categoriaService.update(categoriaDto, id);
		if(categoriaDtoAux == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<CategoriaDto>(categoriaDtoAux, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CategoriaDto> getById(@PathVariable("id") Long id) throws NotFoundException{
		CategoriaDto categoriaDto = this.categoriaService.getById(id);
		if(categoriaDto == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<CategoriaDto>(categoriaDto, HttpStatus.OK);
	}
	
	@GetMapping("/paginas/{pag}")
	public ResponseEntity<Page<Categoria>> paginas(
			@PathVariable(value = "pag" ) int page,
            @RequestParam(defaultValue = "7") int size,
            @RequestParam(defaultValue = "nombre") String order
			) throws NotFoundException{
		Page<Categoria> categorias = categoriaService.paginas(PageRequest.of(page, size, Sort.by(order)));

		return new ResponseEntity<Page<Categoria>>(categorias, HttpStatus.OK);
	}
}
