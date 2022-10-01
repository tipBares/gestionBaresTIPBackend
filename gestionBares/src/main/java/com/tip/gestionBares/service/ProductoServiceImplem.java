package com.tip.gestionBares.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tip.gestionBares.dto.ProductoDto;
import com.tip.gestionBares.model.Categoria;
import com.tip.gestionBares.model.Producto;
import com.tip.gestionBares.repositories.CategoriaRepository;
import com.tip.gestionBares.repositories.ProductoRepository;

@Service
public class ProductoServiceImplem implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public ProductoServiceImplem() {

	}

	@Override
	public ProductoDto create(ProductoDto productoDto, Long idCategoria) {
		Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio(),
				productoDto.getDescripcion());
		
		Categoria categoria = this.categoriaRepository.findById(idCategoria).orElse(null);
		
		producto.setCategoria(categoria);
		
		this.productoRepository.save(producto);

		return new ProductoDto(producto);
	}

	@Override
	public Page<Producto> finByName(String nombreProducto, Pageable pageable) {
		Page<Producto> productos = this.productoRepository.findByNombre(nombreProducto, pageable);
		
		return productos;
	}

	@Override
	public ProductoDto update(ProductoDto productoDto, Long id, Long idCategoria) {
		Producto producto = this.productoRepository.findById(id).orElse(null);
		Categoria categoria = this.categoriaRepository.findById(idCategoria).orElse(null);
		producto.setCategoria(categoria);
		producto.setDescripcion(productoDto.getDescripcion());
		producto.setNombre(productoDto.getNombre());
		producto.setPrecio(productoDto.getPrecio());
		this.productoRepository.save(producto);
		return new ProductoDto(producto);
	}
	
	@Override
	public Page<Producto> findByCategory(Long idCategoria, Pageable pageable){
		Categoria categoria = this.categoriaRepository.findById(idCategoria).orElse(null);
		Page<Producto> productos = this.productoRepository.findByCategoria(categoria, pageable);
		
		return productos;
	}

	@Override
	public List<ProductoDto> findByAll() {
		List<Producto> productos = (List<Producto>) this.productoRepository.findAll();
		List<ProductoDto> productosDto = new ArrayList<ProductoDto>();
		
		if(productos != null) {
			productos.forEach(p -> productosDto.add(new ProductoDto(p)));
		}
		
		return productosDto;
	}

	@Override
	public List<ProductoDto> delete(Long id) {
		List<Producto> productos = (List<Producto>) this.productoRepository.findAll();
		Producto producto = productos.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
		productos.removeIf(c -> c.getId().equals(id));
		List<ProductoDto> productosDto = new ArrayList<ProductoDto>();
		if(productos != null) {
			productos.forEach(p -> productosDto.add(new ProductoDto(p)));
		}
		this.productoRepository.delete(producto);
		
		return productosDto;
	}
	
	@Override
	public Page<Producto> paginas(Pageable pageable) {
		return this.productoRepository.findAll(pageable);
	}

	@Override
	public ProductoDto findById(Long id) {
		Producto producto = this.productoRepository.findById(id).orElse(null);
		ProductoDto productoDto = new ProductoDto(producto);
		return productoDto;
	}

}
