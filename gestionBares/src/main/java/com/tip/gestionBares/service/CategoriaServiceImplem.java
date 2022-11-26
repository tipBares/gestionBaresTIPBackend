package com.tip.gestionBares.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tip.gestionBares.dto.CategoriaDto;
import com.tip.gestionBares.model.Categoria;
import com.tip.gestionBares.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImplem implements CategoriaService{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public CategoriaServiceImplem() {
		
	}

	@Override
	public CategoriaDto create(CategoriaDto categoriaDto) {
		Categoria categoria = new Categoria(categoriaDto.getNombre());
		this.categoriaRepository.save(categoria);
		return new CategoriaDto(categoria);
	}

	@Override
	public List<CategoriaDto> getCategorias() {
		List<Categoria> categorias = (List<Categoria>) this.categoriaRepository.findAll();
		List<CategoriaDto> categoriasDto = new ArrayList<CategoriaDto>();
		if(categorias != null) {
			categorias.forEach(t -> categoriasDto.add(new CategoriaDto(t)));
		}
		return categoriasDto;
	}

	@Override
	public List<CategoriaDto> delete(Long id) {
		List<Categoria> categorias = (List<Categoria>) this.categoriaRepository.findAll();
		Categoria categoria = categorias.stream().filter(c -> c.getId().equals(id)).findAny().orElse(null);
		categorias.removeIf(c -> c.getId().equals(id));
		List<CategoriaDto> categoriasDto = new ArrayList<CategoriaDto>();
		if(categorias != null) {
			categorias.forEach(p -> categoriasDto.add(new CategoriaDto(p)));
		}
		this.categoriaRepository.delete(categoria);
		return categoriasDto;
	}

	@Override
	public CategoriaDto update(CategoriaDto categoriaDto, Long id) {
		Categoria categoria = this.categoriaRepository.findById(id).orElse(null);
		categoria.setNombre(categoriaDto.getNombre());
		this.categoriaRepository.save(categoria);
		return new CategoriaDto(categoria);
	}

	@Override
	public CategoriaDto getById(Long id) {
		Categoria categoria = this.categoriaRepository.findById(id).orElse(null);
		CategoriaDto categoriaDto = new CategoriaDto(categoria);
		return categoriaDto;
	}

	@Override
	public Page<Categoria> paginas(Pageable pageable) {
		return this.categoriaRepository.findAll(pageable);
	}

}
