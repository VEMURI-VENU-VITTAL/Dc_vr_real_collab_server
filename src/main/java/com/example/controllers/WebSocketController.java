package com.example.controllers;

import com.example.models.Event;
import com.example.models.SignalMessage;
import com.example.utils.EventUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@Controller
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
    private final Map<String, List<Event>> eventMap= EventUtils.eventMap;

    @MessageMapping("/room/{roomId}/event")
    public void testWebSocket(Event event, @DestinationVariable String roomId){
        String userId = event.getUserId();
        List<Event> eventList = eventMap.getOrDefault(roomId, new ArrayList<>());
        eventList.removeIf(e-> Objects.equals(e.getUserId(), userId));
        eventList.add(event);
        eventMap.put(roomId, eventList);
        simpMessagingTemplate.convertAndSend("/topic/"+roomId+"/event", event);
        log.info("Event broad casted for roomid: {}, Event: {}", roomId, event);
    }

    @MessageMapping("/room/{roomId}/signal")
    public void signal(@DestinationVariable String roomId, SignalMessage msg) {

        msg.roomId = roomId;

        simpMessagingTemplate.convertAndSend("/topic/room/" + roomId + "/signal", msg);
    }
}
