package com.tip.gestionBares.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tip.gestionBares.dto.ProductoDto;
import com.tip.gestionBares.model.Producto;

public interface ProductoService {
	public ProductoDto create(ProductoDto productoDto, Long idCategoria);
	public Page<Producto> findByName(String nombreProducto, Pageable pageable);
	public List<ProductoDto> findByNameAll(String nombreProducto);
	public ProductoDto update(ProductoDto productoDto, Long id, Long idCategoria);
	public Page<Producto> findByCategory(Long idCategoria, Pageable pageable);
	public List<ProductoDto> findByCategoryAll(Long idCategoria);
	public List<ProductoDto> findByAll();
	public List<ProductoDto> delete(Long id);
	public Page<Producto> paginas(Pageable pageable);
	public ProductoDto findById(Long id);
}
