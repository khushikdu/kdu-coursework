package com.example.assesment2.service;

import com.example.assesment2.entity.Event;
import com.example.assesment2.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService{
    private final EventRepository eventRepository;
    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }

    public List<Event> displayAll(){
        return eventRepository.findAll();
    }
}
