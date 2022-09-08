package com.tip.gestionBares.service;

import java.util.List;
import com.tip.gestionBares.dto.MozoDto;

public interface MozoService {
	public List<MozoDto> findAll();
	public MozoDto update(MozoDto mozoDto, Long id);
	public MozoDto create(MozoDto mozoDto);
	public List<MozoDto> delete(Long id);
}
