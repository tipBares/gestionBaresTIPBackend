package com.tip.gestionBares.service;

import java.util.List;

import com.tip.gestionBares.dto.ProductoDto;

public interface ProductoService {

	public ProductoDto create(ProductoDto productoDto);
	public List<ProductoDto> findByCategory(String categoria);
	public List<ProductoDto> findByAll();
	public List<ProductoDto> delete(Long id);
}
