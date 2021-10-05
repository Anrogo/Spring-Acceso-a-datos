package com.cev.adtema1.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cev.adtema1.domain.Estreno;
import com.cev.adtema1.repository.EstrenoRepository;

@RestController
public class EstrenoController {

	EstrenoRepository estrenoRepository;
	
	public EstrenoController(EstrenoRepository estrenoRepository) {
		this.estrenoRepository = estrenoRepository;
	}

	@GetMapping(path = "/estrenos")
	List<Estreno> getAll(){
		return estrenoRepository.findAll(); 
	}
	
	@PostMapping(path = "/estrenos")
	Estreno altaEstreno(@RequestBody Estreno estreno) {
		return estrenoRepository.save(estreno);
	}
}
