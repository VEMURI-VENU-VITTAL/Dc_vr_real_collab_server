package com.example.controllers;

import com.example.enums.Status;
import com.example.models.Session;
import com.example.services.SessionService;
import com.example.utils.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/create")
    public ResponseEntity<Session> createSession(@RequestBody String userId){
        ResponseEntity<Session> response;
        try{
            Session session = sessionService.createSession(userId);
            response = new ResponseEntity<>(session, Status.SUCCESS, "");
        } catch (Exception e) {
            response = new ResponseEntity<>(null, Status.FAILURE, e.getMessage());
        }
        return response;
    }

    @GetMapping("/getSession")
    public ResponseEntity<Session> getSession(@RequestParam String userId){
        ResponseEntity<Session> response;
        try{
            Session session = sessionService.getSession(userId);
            response = new ResponseEntity<>(session, Status.SUCCESS, "");
        } catch (Exception e) {
            response = new ResponseEntity<>(null, Status.FAILURE, e.getMessage());
        }

        return response;
    }
}
