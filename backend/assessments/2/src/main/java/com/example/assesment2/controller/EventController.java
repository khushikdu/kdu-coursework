package com.example.assesment2.controller;

import com.example.assesment2.entity.Event;
import com.example.assesment2.logging.Logging;
import com.example.assesment2.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private EventService eventService;
    Logging.LoggerType loggerTypeInfo=Logging.LoggerType.INFO;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/addEvent")
    public ResponseEntity<String> addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
        return new ResponseEntity<>("Event Added succesfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found");
    }

    @GetMapping("/allEvents")
    public ResponseEntity<List<Event>> allEvents(){
        List<Event> events= eventService.displayAll();
        if(events!=null) {
            Logging.printLogger("List extracted succesfully",loggerTypeInfo);
            return new ResponseEntity<>(events, HttpStatus.OK);
        }
        else {
            Logging.printLogger("List is empty",loggerTypeInfo);
            return null;
        }
    }
}
