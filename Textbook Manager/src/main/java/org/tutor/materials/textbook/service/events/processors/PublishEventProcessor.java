package org.tutor.materials.textbook.service.events.processors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.textbook.exceptions.AppException;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.service.events.events.Event;
import org.tutor.materials.textbook.shared.Errors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;


@Aspect
@Component
public class PublishEventProcessor {

    private final EventPublisher publisher;

    @Autowired
    public PublishEventProcessor(EventPublisher publisher) {
        this.publisher = publisher;
    }


    @Around("@annotation(org.tutor.materials.textbook.service.events.processors.PublishEvent)")
    public Object publishEvent(ProceedingJoinPoint joinPoint) throws Throwable {
        var constructor = getConstructor(joinPoint);

        var accessor = (ID<? extends AppUser>) joinPoint.getArgs()[0];
        var input = joinPoint.getArgs()[1];
        var isOptional = ((MethodSignature)joinPoint.getSignature()).getReturnType().equals(Optional.class);

        return getResult(joinPoint, constructor, accessor, input, isOptional);
    }


    private Object getResult(ProceedingJoinPoint joinPoint, Constructor<? extends Event> constructor, ID<? extends AppUser> accessor, Object input, boolean isOptional) throws Throwable {
        try {
            var result = joinPoint.proceed();
            publishEvent(constructor, accessor, input, result, null);
            return result;
        } catch (Exception e) {
            var errors = getErrorMessages(e);
            publishEvent(constructor, accessor, input, null, errors);
            if(isOptional)
                return Optional.empty();
            else
                throw e;
        }
    }

    private void publishEvent(Constructor<? extends Event> constructor, ID<? extends AppUser> accessor, Object input, Object result, Errors errors) {
        try {
            var event = constructor.newInstance(accessor, input, result, errors);
            publisher.publish(event);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }

    @NotNull
    private Constructor<? extends Event> getConstructor(ProceedingJoinPoint joinPoint) {
        var method = getMethod(joinPoint);
        var eventClass = getEventClass(joinPoint);
        var constructor = getConstructor(method, eventClass);
        return constructor;
    }

    @NotNull
    private Constructor<? extends Event> getConstructor(Method method, Class<? extends Event> eventClass) {
        var resultType = method.getReturnType();
        var inputType = method.getParameterTypes()[1];

        try {
            return eventClass.getDeclaredConstructor(ID.class, inputType, resultType, Errors.class);
        } catch (NoSuchMethodException e) {
            var availableConstructors = Arrays.stream(eventClass.getDeclaredConstructors())
                    .map(Constructor::toString).collect(Collectors.joining(", \n"));
            var message = String.format("No constructor found in %1$s for parameters: [ %2$s, %3$s, %4$s, %5$s]\n" +
                            "Available constructors %6$s",
                    eventClass, ID.class, inputType, resultType, Errors.class, availableConstructors);
            throw new IllegalStateException(message);
        }
    }


    @NotNull
    private Errors getErrorMessages(Exception e) {
        var errorList = new ArrayList<String>();
        if(e instanceof AppException) {
            errorList.add(e.getClass().getSimpleName() + ": " + e.getMessage());
        } else {
            errorList.add("InternalServerError: " + e.getMessage());
        }
        return new Errors(errorList);
    }


    private Method getMethod(ProceedingJoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod();
    }

    private Class<? extends Event> getEventClass(ProceedingJoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(PublishEvent.class).value();
    }

}
