package language.graphql.shared;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface Input {
    default List<String> validate() {
        var possibleMethodNames = Arrays
                .stream(this.getClass().getDeclaredFields())
                .map(Field::getName)
                .map(n -> n.substring(0, 1).toUpperCase() + n.substring(1))
                .map(n -> "validate" + n)
                .toList();

        return Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(m -> possibleMethodNames.contains(m.getName()))
                .filter(m -> m.getReturnType().equals(Optional.class))
                .filter(m -> m.getParameterCount() == 0)
                .peek(m -> m.setAccessible(true))
                .map(m -> {
                    try {
                        return (Optional<String>) m.invoke(this);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(m.getName());
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    };

}
