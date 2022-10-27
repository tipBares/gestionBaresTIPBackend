package com.tip.gestionBares.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.gestionBares.dto.MesaDto;
import com.tip.gestionBares.model.Mesa;
import com.tip.gestionBares.repositories.MesaRepository;

@Service
public class MesaServiceImplem implements MesaService{

	@Autowired
	private MesaRepository mesaRepository;

	public MesaServiceImplem() {
		
	}

	@Override
	public MesaDto create(MesaDto mesaDto) {
		Mesa mesa = new Mesa(
				 mesaDto.getAbierta(),
				 mesaDto.getNroMesa()
				 );
		this.mesaRepository.save(mesa);
		return new MesaDto(mesa);
	}

	@Override
	public List<MesaDto> getMesas() {
		List<Mesa> mesas = (List<Mesa>) this.mesaRepository.findAll();
		List<MesaDto> mesasDto = new ArrayList<MesaDto>();
		if(mesas != null) {
			mesas.forEach(m -> mesasDto.add(new MesaDto(m)));
		}

		return mesasDto;
	}

	@Override
	public MesaDto getById(Long id) {
		
		Mesa mesa = this.mesaRepository.findById(id).orElse(null);
		MesaDto mesaDto = new MesaDto(mesa);
		return mesaDto;
	}

}
