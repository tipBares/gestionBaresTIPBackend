package com.tip.gestionBares.service;

import java.util.ArrayList;

import com.tip.gestionBares.dto.MesaDto;
import com.tip.gestionBares.dto.TicketDto;

public interface MesaService {
	
	public MesaDto create(MesaDto mesaDto);
	public ArrayList<MesaDto> getMesas();
	public MesaDto getMesaById (Long id);
}
