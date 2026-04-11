package com.example.controllers;


import com.example.enums.Status;
import com.example.models.Event;
import com.example.services.EventService;
import com.example.utils.EventUtils;
import com.example.utils.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @GetMapping("/fetch")
    public ResponseEntity<List<Event>> getEventList(@RequestParam String roomId){
        ResponseEntity<List<Event>> response;
        try{
            List<Event> events = Objects.requireNonNullElse(EventUtils.eventMap.get(roomId), new ArrayList<>());
            response = new ResponseEntity<>(events, Status.SUCCESS, "");
        } catch (Exception e) {
            response = new ResponseEntity<>(null, Status.FAILURE, e.getMessage());
        }

        return response;
    }
}
