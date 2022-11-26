package com.tip.gestionBares.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public List<MozoDto> findAll() {
		List<Mozo> mozos = (List<Mozo>) this.mozoRepository.findAll();
		List<MozoDto> mozosDto = new ArrayList<MozoDto>();
		if(mozos != null) {
			mozos.forEach(mozo -> mozosDto.add(new MozoDto(mozo)));
		}
		return mozosDto;
	}

	@Override
	public MozoDto findById(Long id) {
		Mozo mozo = this.mozoRepository.findById(id).orElse(null);
		MozoDto mozoDto = new MozoDto(mozo);
		return mozoDto;
	}
	
	@Override
	public MozoDto update(MozoDto mozoDto, Long id) {
		Mozo mozo = this.mozoRepository.findById(id).orElse(null);
		mozo.setNombre(mozoDto.getNombre());
		mozo.setApellido(mozoDto.getApellido());
		mozo.setNick(mozoDto.getNick());
		this.mozoRepository.save(mozo);
		return new MozoDto(mozo);
	}

	@Override
	public MozoDto create(MozoDto mozoDto) {
		Mozo mozo = new Mozo(mozoDto.getNombre(),
				mozoDto.getApellido(),
				mozoDto.getNick()
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

	@Override
	public Page<Mozo> paginas(Pageable pageable) {
		return this.mozoRepository.findAll(pageable);
	}

}
