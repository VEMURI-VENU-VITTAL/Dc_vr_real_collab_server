package com.example.services;

import com.example.models.Event;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventService {
    private final MongoTemplate mongoTemplate;

    public EventService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Event saveEvent(Event event){
        Event resultedEvent = mongoTemplate.save(event);
        return resultedEvent;
    }
}
