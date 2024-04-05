package com.rungroup.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface EvenRepository extends JpaRepository<Event, Long> {
}
