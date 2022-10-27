package com.tip.gestionBares.service;

import java.util.List;

import com.tip.gestionBares.dto.MesaDto;

public interface MesaService {
	
	public MesaDto create(MesaDto mesaDto);
	
	public List<MesaDto> getMesas();
	
	public MesaDto getById(Long id);
}
