package com.example.controllers;


import com.example.enums.Status;
import com.example.models.Event;
import com.example.services.EventService;
import com.example.utils.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/save")
    public ResponseEntity<Event> saveEvent(Event event){
        ResponseEntity<Event> response;
        try{
            event = eventService.saveEvent(event);
            response = new ResponseEntity<>(event, Status.SUCCESS, "");
        } catch (Exception e) {
            response = new ResponseEntity<>(null, Status.FAILURE, e.getMessage());
        }
        return response;
    }
}
