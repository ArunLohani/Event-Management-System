package com.project.eventmanagement.services.impl;

import com.project.eventmanagement.dto.DeleteResponse;
import com.project.eventmanagement.dto.EventDTO;
import com.project.eventmanagement.dto.PostEventRequestDTO;
import com.project.eventmanagement.dto.PutResponse;
import com.project.eventmanagement.exception.EventNotFoundException;
import com.project.eventmanagement.model.Event;
import com.project.eventmanagement.model.User;
import com.project.eventmanagement.repository.EventRepo;
import com.project.eventmanagement.repository.UserRepo;
import com.project.eventmanagement.services.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.eventmanagement.utility.Utility.getCurrentUser;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo repo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    EventServiceImpl(EventRepo repo, ModelMapper modelMapper , UserRepo userRepo) {
        this.repo = repo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
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
    public List<EventDTO> getAllEventsForUser() {
        String email = getCurrentUser();
        System.out.println("GET ALL EVENTS FOR USER" + email);
        User user = userRepo.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<EventDTO> events = user.getOrganizedEvents().stream().map(event->modelMapper.map(event,EventDTO.class)).toList();
        return events;
    }


    @Override
    public EventDTO getEventByIdForUser(Long id) {
        String email = getCurrentUser();




        Event event = repo
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));

        if(!event.getOrganizer().getEmail().equals(email)){
            throw new AccessDeniedException("You are not authorized to update this event.");
        }

        return modelMapper.map(event, EventDTO.class);
    }

    @Override
    public DeleteResponse deleteEventByIdForUser(Long id) {
        String email = getCurrentUser();




        Event event = repo
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));

        if(!event.getOrganizer().getEmail().equals(email)){
            throw new AccessDeniedException("You are not authorized to update this event.");
        }
        repo.deleteById(id);

        return new DeleteResponse("Event Delete Successfully", modelMapper.map(event, EventDTO.class));

    }

    @Override
    public EventDTO updateEventForUser(Long id, PostEventRequestDTO eventDto) {

        String email = getCurrentUser();




        Event event = repo
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));

        if(!event.getOrganizer().getEmail().equals(email)){
            throw new AccessDeniedException("You are not authorized to update this event.");
        }


        modelMapper.map(eventDto, event);

        event = repo.save(event);

        return modelMapper.map(event, EventDTO.class);
    }

    @Override
    public PutResponse addEventForUser(PostEventRequestDTO eventDto) {

        String email = getCurrentUser();
        System.out.println("EMAIL" + email);
        User user = userRepo.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Event newEvent = modelMapper.map(eventDto, Event.class);

        newEvent.setOrganizer(user);
        Event event = repo.save(newEvent);

        return new PutResponse("Event Added successfully",modelMapper.map(event, EventDTO.class));
    }
}
