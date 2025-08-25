package com.project.eventmanagement.services;

import com.project.eventmanagement.dto.DeleteResponse;
import com.project.eventmanagement.dto.EventDTO;
import com.project.eventmanagement.dto.PostEventRequestDTO;
import com.project.eventmanagement.dto.PutResponse;
import jakarta.validation.Valid;

import java.util.List;


public interface EventService {



    List<EventDTO> getAllEvents();

    EventDTO getEventById(Long id);

    DeleteResponse deleteEventById(Long id);

    List<EventDTO> getAllEventsForUser();
    EventDTO getEventByIdForUser(Long id);

    DeleteResponse deleteEventByIdForUser(Long id);

    EventDTO updateEventForUser(Long id, @Valid PostEventRequestDTO eventDto);

    PutResponse addEventForUser(@Valid PostEventRequestDTO event);
}
