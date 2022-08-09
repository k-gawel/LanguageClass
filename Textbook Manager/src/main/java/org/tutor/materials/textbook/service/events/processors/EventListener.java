package org.tutor.materials.textbook.service.events.processors;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.tutor.materials.textbook.service.events.events.Event;

@Component
public class EventListener implements ApplicationListener<Event> {


    @Override
    public void onApplicationEvent(@NotNull Event event) {
        System.out.println(event);
    }

}
