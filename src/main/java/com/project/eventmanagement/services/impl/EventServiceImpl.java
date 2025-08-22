package com.project.eventmanagement.services.impl;

import com.project.eventmanagement.dto.DeleteResponse;
import com.project.eventmanagement.dto.EventDTO;
import com.project.eventmanagement.dto.PostEventRequestDTO;
import com.project.eventmanagement.dto.PutResponse;
import com.project.eventmanagement.exception.EventNotFoundException;
import com.project.eventmanagement.model.Event;
import com.project.eventmanagement.repository.EventRepo;
import com.project.eventmanagement.services.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo repo;
    private final ModelMapper modelMapper;

    EventServiceImpl(EventRepo repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = repo.findAll();

        return events
                .stream()
                .map(event -> modelMapper
                        .map(event, EventDTO.class))
                .toList();

    }

    @Override
    public EventDTO getEventById(Long id) {

        Event event = repo
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));


        return modelMapper.map(event, EventDTO.class);

    }

    @Override
    public DeleteResponse deleteEventById(Long id) {

        Event event = repo
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));

        repo.deleteById(id);

        return new DeleteResponse("Event Delete Successfully", modelMapper.map(event, EventDTO.class));


    }

    @Override
    public EventDTO updateEvent(Long id, PostEventRequestDTO eventDto) {

        Event event = repo
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));

        modelMapper.map(eventDto, event);

        event = repo.save(event);

        return modelMapper.map(event, EventDTO.class);

    }

    @Override
    public PutResponse addEvent(PostEventRequestDTO eventDto) {

        Event newEvent = modelMapper.map(eventDto, Event.class);

        Event event = repo.save(newEvent);

        return new PutResponse("Event Added successfully",modelMapper.map(event, EventDTO.class));
    }
}
