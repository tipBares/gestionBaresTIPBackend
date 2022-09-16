package com.tip.gestionBares.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tip.gestionBares.dto.MesaDto;
import com.tip.gestionBares.service.MesaService;

@RestController
@RequestMapping("/mesa")
@CrossOrigin(origins = {"http://localhost:3000", "*"}, maxAge = 3600)
public class MesaController {

	@Autowired
	private MesaService mesaService;
	
	@PostMapping
	public ResponseEntity<MesaDto> create(@RequestBody MesaDto mesaDto){
		mesaDto = this.mesaService.create(mesaDto);
		return new ResponseEntity<MesaDto>(mesaDto, HttpStatus.CREATED);
		
	}
	
	@GetMapping(value = "/get")
	public ResponseEntity<ArrayList<MesaDto>> getMesas() throws NotFoundException{
		ArrayList<MesaDto> mesasDto = this.mesaService.getMesas();
		if(mesasDto.size() == 0) {
			throw new NotFoundException();
		}
		return new ResponseEntity<ArrayList<MesaDto>>(mesasDto, HttpStatus.OK);
		
	}
}
