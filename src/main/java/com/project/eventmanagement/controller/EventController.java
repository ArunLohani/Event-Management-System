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
@RequestMapping("/api/v1/admin/events")
public class EventController {


    private final EventService service;

    EventController(EventService service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<EventDTO>> getAllEvents(){

            return  ResponseEntity.status(HttpStatus.OK).body(service.getAllEvents());


    }


    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id){

        return new ResponseEntity<>(service.getEventById(id),HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteEventById(@PathVariable Long id){

        return new ResponseEntity<>(service.deleteEventById(id),HttpStatus.OK);

    }



}
