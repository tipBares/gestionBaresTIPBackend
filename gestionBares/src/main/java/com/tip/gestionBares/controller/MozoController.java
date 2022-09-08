package com.tip.gestionBares.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tip.gestionBares.dto.MozoDto;
import com.tip.gestionBares.service.MozoService;

@RestController
@RequestMapping("/mozo")
public class MozoController {

	@Autowired
	private MozoService mozoService;
	
	@PostMapping
	public ResponseEntity<MozoDto> create(@RequestBody MozoDto mozoDto) {
		mozoDto = this.mozoService.create(mozoDto);

		return new ResponseEntity<MozoDto>(mozoDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<List<MozoDto>> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
		List<MozoDto> mozosDto = this.mozoService.delete(id);
		if (mozosDto.size() <= 0) {
			throw new NotFoundException();
		}

		return new ResponseEntity<List<MozoDto>>(mozosDto, HttpStatus.OK);

	}
}
