package com.tip.gestionBares.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.gestionBares.dto.ProductoDto;
import com.tip.gestionBares.model.Producto;
import com.tip.gestionBares.repositories.ProductoRepository;

@Service
public class ProductoServiceImplem implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	public ProductoServiceImplem() {

	}

	@Override
	public ProductoDto create(ProductoDto productoDto) {
		Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio(), productoDto.getCategoria(),
				productoDto.getDescripcion());

		this.productoRepository.save(producto);

		return new ProductoDto(producto);
	}

	@Override
	public List<ProductoDto> findByCategory(String categoria){
		List<Producto> productos = this.productoRepository.findByCategoria(categoria);
		List<ProductoDto> productosDto = new ArrayList<ProductoDto>();
		if(productos != null) {
			productos.forEach(p -> productosDto.add(new ProductoDto(p)));
		}
		
		return productosDto;
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

}
