package com.tip.gestionBares.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.gestionBares.dto.MozoDto;
import com.tip.gestionBares.model.Mozo;
import com.tip.gestionBares.repositories.MozoRepository;

@Service
public class MozoServiceImplem implements MozoService{

	@Autowired
	MozoRepository mozoRepository;
	
	@Override
	public List<MozoDto> findAll() {
		List<Mozo> mozos = (List<Mozo>) this.mozoRepository.findAll();
		List<MozoDto> mozosDto = new ArrayList<MozoDto>();
		
		if(mozos != null) {
			mozos.forEach(mozo -> mozosDto.add(new MozoDto(mozo)));
		}
		return mozosDto;
	}

	@Override
	public MozoDto update(MozoDto mozoDto, Long id) {
		Mozo mozo = this.mozoRepository.findById(id).orElse(null);
		mozo.setNombre(mozoDto.getNombre());
		mozo.setApellido(mozo.getApellido());
		this.mozoRepository.save(mozo);
		return new MozoDto(mozo);
	}

}
