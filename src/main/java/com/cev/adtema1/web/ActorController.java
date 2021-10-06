package com.cev.adtema1.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cev.adtema1.domain.Actor;
import com.cev.adtema1.repository.ActorRepository;

@RestController
public class ActorController {

	ActorRepository actorRepository;
	
	public ActorController(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}
	
	@GetMapping(path = "/actores")
	List<Actor> getActores(){
		return actorRepository.findAll();
	}
	
	@PostMapping(path = "/actores")
	Actor altaActor(@RequestBody Actor actor) {
		return actorRepository.save(actor);
	}
	
	
}
