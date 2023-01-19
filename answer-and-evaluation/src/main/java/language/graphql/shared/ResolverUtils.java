package language.graphql.shared;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class ResolverUtils {

    public static <E extends Domain> List<E> getProxy(List<DomainID<E>> ids) {
        return ids.stream().map(ResolverUtils::getProxy).toList();
    }

    public static <E extends Domain> E getProxy(DomainID<E> id) {
        var constructor = id.type().getConstructors()[0];
        var parameterCount = constructor.getParameterCount();
        var parameters = new Object[parameterCount];
        parameters[0] = id;
        try {
            return (E) constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(id.toString());
        }
    }

}
