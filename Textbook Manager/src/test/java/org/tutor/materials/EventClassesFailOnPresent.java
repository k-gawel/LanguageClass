package org.tutor.materials;

import org.junit.jupiter.api.Test;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.service.events.events.Event;
import org.tutor.materials.textbook.shared.Errors;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EventClassesFailOnPresent {

    @Test
    public void validateEventClassesForConstructor() {
        var packageName = "org.tutor.materials.service.events.events";
        var classes = findEventClasses(packageName);

        System.out.println("Found " + classes.size() + " event classes in " + packageName);

        for (Class eventClass : classes) {
            System.out.print("Validating " + eventClass.getSimpleName() + "...");
            validate(eventClass);
            System.out.print(" SUCCESS\n");
        }
    }

    private void validate(Class<? extends Event> eventClass) {
        var message = String.format("%1$s must have public constructor with parameters: [%2$s, ?inputType?, ?resultType?, %3$s]",
                eventClass, ID.class, Errors.class);

        Arrays.stream(eventClass.getDeclaredConstructors())
                .filter(c -> c.getParameterCount() == 4)
                .filter(c -> c.getParameterTypes()[0].equals(ID.class) && c.getParameterTypes()[3].equals(Errors.class))
                .filter(c -> Modifier.isPublic(c.getModifiers()))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(message));

    }

    private Class getFieldType(Class<? extends Event> eventClass, String fieldName) {
        try {
            return eventClass.getDeclaredField(fieldName).getType();
        } catch (NoSuchFieldException e) {
            if(eventClass.equals(Event.class))
                throw new IllegalStateException("Cannot find field of name " + fieldName + ". Available fields " +
                        Arrays.stream(eventClass.getDeclaredFields()).map(Field::getName).collect(Collectors.toSet()));
            else
                return getFieldType((Class<? extends Event>) eventClass.getSuperclass(), fieldName);
        }
    }

    private Set<Class> findEventClasses(String packageName) {
        return findAllClassesUsingClassLoader(packageName).stream()
                .filter(Event.class::isAssignableFrom)
                .filter(c -> Modifier.isPublic(c.getModifiers()))
                .filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .collect(Collectors.toSet());
    }

    private Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

}
