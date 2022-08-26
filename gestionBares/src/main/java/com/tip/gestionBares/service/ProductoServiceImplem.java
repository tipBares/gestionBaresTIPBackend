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
	public List<ProductoDto> finByName(String nombreProducto) {
		List<ProductoDto> resultado = new ArrayList<ProductoDto>();
		List<Producto> productos = this.productoRepository.findByNombre(nombreProducto);
		for (Producto producto : productos) {
			resultado.add(new ProductoDto(producto));
		}
		return resultado;
	}

	@Override
	public ProductoDto update(ProductoDto productoDto, Long id) {
		Producto producto = this.productoRepository.findById(id).orElse(null);
		producto.setCategoria(productoDto.getCategoria());
		producto.setDescripcion(productoDto.getDescripcion());
		producto.setNombre(productoDto.getNombre());
		producto.setPrecio(productoDto.getPrecio());
		this.productoRepository.save(producto);
		return new ProductoDto(producto);
	}
	
	
}
