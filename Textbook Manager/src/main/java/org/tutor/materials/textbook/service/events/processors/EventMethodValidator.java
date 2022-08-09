package org.tutor.materials.textbook.service.events.processors;

import org.aspectj.lang.reflect.MethodSignature;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.service.events.events.Event;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class EventMethodValidator {

//    for event publisher method to be correct it has to:
//    - have first argument of type ID<? extends AppUser>
//    - have second argument of type corresponding with input type of event class specified in annotation
//    - have return type corresponding to result type specified in annotation

    public Class<? extends Event> getEventClass(MethodSignature annotatedMethod) {
        var annotation = annotatedMethod.getMethod().getAnnotation(PublishEvent.class);
        var eventClass = annotation.value();
        return null;
    }

    //returns error messages list if first argument is missing or is not of desired type, else returns empty list
    public List<String> validateFirstArgument(Method method) {
        var desiredTypeName = ID.class.getName() + "<? extends " + AppUser.class.getName() + ">";
        String actualTypeName;
        try {
            actualTypeName = method.getGenericParameterTypes()[0].getTypeName();
        } catch (IndexOutOfBoundsException e) {
            return List.of("Method should have first argument of type " + desiredTypeName + " but has none.");
        }

        if(!desiredTypeName.equals(actualTypeName))
            return List.of("First method parameter should be of type " + desiredTypeName + " but is " + actualTypeName);

        return Collections.emptyList();
    }
    
    //return error message if second argument is missing or is not applicable for desired type, else returns empty list
    public List<String> validateSecondArgument(Method method) {
        var inputType = getInputFieldType(method);
        var parameters = method.getParameters();
        if(parameters.length < 2) 
            return List.of("Method should have second argument of type " + inputType.getName() + " but has none.");
        
        var parameterType = parameters[0].getType();
        
        if(!inputType.isAssignableFrom(parameterType))
            return List.of("Method should have second argument assignable from " + inputType.getName() + " but has " + parameterType);
        
        return Collections.emptyList();
    }

    //returns errors if method return type is not compatibile with event result type else return emptyList
    public List<String> validateReturnType(Method method) {
        var resultType = getResultFieldType(method);
        var returnType = method.getReturnType();

        if(!returnType.isAssignableFrom(resultType))
            return List.of("Return type should be descendant of  " + resultType + " but is " + returnType);
        return
            Collections.emptyList();
    }

    private Class<?> getInputFieldType(Method method) {
        var event = method.getAnnotation(PublishEvent.class).value();
        return Arrays.stream(event.getDeclaredMethods())
                .filter(m -> m.getName().equals("create"))
                .filter(m -> Modifier.isStatic(m.getModifiers()))
                .filter(m -> m.getParameterCount() == 2)
                .map(m -> m.getParameterTypes()[1])
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Event class should contain only one static method with name 'create' and two arguments."
                ));
    }

    private Class<?> getResultFieldType(Method method) {
        Function<Class<?>, Class<?>> getBuilderClass = c ->
            Arrays.stream(c.getDeclaredClasses())
                    .filter(Event.Builder.class::isAssignableFrom)
                    .filter(d -> Modifier.isStatic(d.getModifiers()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(
                            "Event class " + c + " should have inner static builder class descending from Event.Builder class"
                    ));

        Function<Class<?>, Method> getSuccessMethod = b ->
                Arrays.stream(b.getMethods())
                        .filter(c -> c.getName().equals("success"))
                        .filter(c -> c.getParameterCount() == 1)
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException(
                                "Builder " + b.getName() + " should have success(?, ?) method declared in it. "
                        ));
        var event = method.getAnnotation(PublishEvent.class).value();
        return Optional.of(event)
                .map(getBuilderClass)
                .map(getSuccessMethod)
                .map(c -> c.getParameterTypes()[0])
                .orElseThrow(() -> new IllegalStateException());
    }

    private Class<?> getFieldType(Class<? extends Event> eventClass, String fieldName) {
        try {
            return eventClass.getDeclaredField(fieldName).getType();
        } catch (NoSuchFieldException e) {
            if(eventClass.equals(Event.class))
                throw new IllegalStateException(e);
            else
                return getFieldType((Class<? extends Event>) eventClass.getSuperclass(), fieldName);
        }
    }

}
