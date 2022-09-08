package com.tip.gestionBares.service;

import java.util.List;

import com.tip.gestionBares.dto.MozoDto;

public interface MozoService {
	public MozoDto create(MozoDto mozoDto);
	public List<MozoDto> delete(Long id);
}
