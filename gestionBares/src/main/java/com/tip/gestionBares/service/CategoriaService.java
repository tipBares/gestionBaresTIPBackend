package com.tip.gestionBares.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tip.gestionBares.dto.CategoriaDto;
import com.tip.gestionBares.model.Categoria;

public interface CategoriaService {
	public CategoriaDto create(CategoriaDto categoriaDto);
	public List<CategoriaDto> getCategorias();
	public List<CategoriaDto> delete(Long id);
	public CategoriaDto update(CategoriaDto categoriaDto, Long id);
	public CategoriaDto getById(Long id);
	public Page<Categoria> paginas(Pageable pageable);
}
