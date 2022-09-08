package com.tip.gestionBares.service;

import java.util.List;

import com.tip.gestionBares.dto.CategoriaDto;

public interface CategoriaService {

	public CategoriaDto create(CategoriaDto categoriaDto);
	
	public List<CategoriaDto> getCategorias();
	
	public List<CategoriaDto> delete(Long id);
	
	public CategoriaDto update(CategoriaDto categoriaDto, Long id);
	
	public CategoriaDto getById(Long id);
}
