package org.tutor.materials.textbook.service.events.processors;

import org.tutor.materials.textbook.service.events.events.BasicEvent;
import org.tutor.materials.textbook.service.events.events.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishEvent {

    Class<? extends Event> value() default BasicEvent.class;

}
