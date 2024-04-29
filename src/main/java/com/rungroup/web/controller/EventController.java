package com.rungroup.web.controller;

import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Event;
import com.rungroup.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@CrossOrigin
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/{clubId}/new")
    public ResponseEntity<?> createEvent(@RequestBody EventDto newEvent, @PathVariable("clubId") Long clubId) {
        Event event = eventService.createEvent(clubId, newEvent);
        return new ResponseEntity<>(event, CREATED);
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> getEvents() {
        List<EventDto> events = eventService.findAllEvents();
        return new ResponseEntity<>(events, OK);
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<EventDto> getEvent(@PathVariable("eventId") Long eventId) {
        EventDto event = eventService.findEvent(eventId);
        return new ResponseEntity<>(event, OK);
    }

    @DeleteMapping("/events/{eventId}/delete")
    public ResponseEntity<String> deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.status(OK).body("Event has been removed successfully");
    }

}
