package model.repository.criteria;

import lombok.NonNull;
import org.jooq.meta.derby.sys.Sys;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public interface Criteria extends SqlParameterSource {

    @Override
    default boolean hasValue(String paramName) {
        return Arrays.stream(this.getClass().getDeclaredFields()).anyMatch(f -> f.getName().equals(paramName));
    }

    @Override
    default Object getValue(String paramName) throws IllegalArgumentException {
        return Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(m -> m.getName().equals(paramName) && m.getParameterCount() == 0)
                .findFirst()
                .map(this::invokeMethod)
                .orElseThrow(() -> new IllegalArgumentException(paramName));
    }

    private Object invokeMethod(Method f) {
        try {
            return f.invoke(this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(f.getName());
        }
    }
}
