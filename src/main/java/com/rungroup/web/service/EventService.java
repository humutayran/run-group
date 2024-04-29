package com.rungroup.web.service;

import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findEvent(Long eventId);

    void deleteEvent(Long eventId);
}
