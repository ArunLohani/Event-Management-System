package com.project.eventmanagement.controller;

import com.project.eventmanagement.dto.DeleteResponse;
import com.project.eventmanagement.dto.EventDTO;
import com.project.eventmanagement.dto.PostEventRequestDTO;
import com.project.eventmanagement.dto.PutResponse;
import com.project.eventmanagement.services.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {


    private final EventService service;

    UserController(EventService service){
        this.service = service;
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id){

        return new ResponseEntity<>(service.getEventByIdForUser(id),HttpStatus.OK);


    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<DeleteResponse> deleteEventById(@PathVariable Long id){

        return new ResponseEntity<>(service.deleteEventByIdForUser(id),HttpStatus.OK);

    }
    
    
    @PutMapping("/events/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id , @RequestBody @Valid PostEventRequestDTO eventDto){

        return new ResponseEntity<>(service.updateEventForUser(id,eventDto), HttpStatus.OK);

    }

    @PostMapping("/events")
    public ResponseEntity<PutResponse> addEvent(@RequestBody @Valid PostEventRequestDTO event){

        return new ResponseEntity<>(service.addEventForUser(event),HttpStatus.CREATED);

    }

    @GetMapping("/events")
    public ResponseEntity<List<EventDTO>> getAllEventsForUser(){

        return  ResponseEntity.status(HttpStatus.OK).body(service.getAllEventsForUser());


    }

}
