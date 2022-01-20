package com.devsuperior.dsmove.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmove.dto.ScoreDTO;
import com.devsuperior.dsmove.services.ScoreService;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
	@Autowired
	private ScoreService service;
	
	@PutMapping()
	public void saveScore(@RequestBody ScoreDTO dto) {
		service.saveScore(dto);
	}
}
