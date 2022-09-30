package com.tip.gestionBares.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tip.gestionBares.dto.MozoDto;
import com.tip.gestionBares.model.Mozo;

public interface MozoService {
	public List<MozoDto> findAll();
	public MozoDto update(MozoDto mozoDto, Long id);
	public MozoDto create(MozoDto mozoDto);
	public List<MozoDto> delete(Long id);
	public MozoDto findById(Long id);
	public Page<Mozo> paginas(Pageable pageable);
}
