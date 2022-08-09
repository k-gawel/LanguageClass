package org.tutor.materials.textbook.service.events.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.tutor.materials.textbook.service.events.events.Event;

@Component
public class EventPublisher {

    private final ApplicationEventPublisher publisher;

    @Autowired
    public EventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(final Event event) {
        publisher.publishEvent(event);
    }

}
