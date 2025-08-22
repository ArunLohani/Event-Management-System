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

    EventDTO updateEvent(Long id, @Valid PostEventRequestDTO eventDto);

    PutResponse addEvent(@Valid PostEventRequestDTO event);
}
