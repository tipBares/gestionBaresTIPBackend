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
	private MozoRepository mozoRepository;
	
	
	
	public MozoServiceImplem() {
	
	}

	@Override
	public MozoDto create(MozoDto mozoDto) {
		Mozo mozo = new Mozo(mozoDto.getNombre(),
				mozoDto.getApellido()
				);
		this.mozoRepository.save(mozo);
		return new MozoDto(mozo);
	}

	@Override
	public List<MozoDto> delete(Long id) {
		List<Mozo> mozos = (List<Mozo>) this.mozoRepository.findAll();
		Mozo mozo = mozos.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
		mozos.removeIf(m -> m.getId().equals(id));
		List<MozoDto> mozosDto = new ArrayList<MozoDto>();
		if(mozo != null) {
			mozos.forEach(m -> mozosDto.add(new MozoDto(mozo)));
		}
		this.mozoRepository.delete(mozo);
		return mozosDto;
	}

}
