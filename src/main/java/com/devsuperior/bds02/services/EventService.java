package com.devsuperior.bds02.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
			Optional<Event> obj = repository.findById(id);
			Event entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found" + id));
	        copyDtoToEntity(dto, entity);
	        entity = repository.save(entity);
	        return new EventDTO(entity);
		}
	
	private void copyDtoToEntity(EventDTO dto, Event entity) {
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		entity.setCity(new City(dto.getCityId(), null));
	}
}
