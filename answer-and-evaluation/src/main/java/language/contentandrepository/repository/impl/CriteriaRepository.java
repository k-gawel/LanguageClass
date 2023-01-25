package language.contentandrepository.repository.impl;

import language.contentandrepository.criteria.DomainCriteria;
import language.contentandrepository.model.Domain;
import language.contentandrepository.repository.answerandevalution.AnswerAndEvaluationRepository;
import language.contentandrepository.repository.textbook.TextbookElementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Repository
@RequiredArgsConstructor
public class CriteriaRepository {

    private final AnswerAndEvaluationRepository answerAndEvaluationRepository;
    private final TextbookElementsRepository textbookElementsRepository;
    private Map<Class<? extends DomainCriteria>, Function<DomainCriteria, List<? extends Domain>>> criteriaFetcherMap = new HashMap<>();

    @PostConstruct
    private void setCriteriaFetcherMap() {
        Predicate<Method> predicate = m -> m.getName().equals("find") && m.getParameterCount() == 1;

        Consumer<Object> extractMethods = o -> Arrays.stream(o.getClass().getDeclaredMethods())
                .filter(predicate)
                .forEach(m -> {
                    criteriaFetcherMap.put((Class<? extends DomainCriteria>) m.getParameterTypes()[0], fromMethod(m, o));
                });

        extractMethods.accept(answerAndEvaluationRepository);
        extractMethods.accept(textbookElementsRepository);
    }

    public <E extends Domain> List<E> find(DomainCriteria<E> criteria) {
        var fetcher = getCriteriaFetcher(criteria.getClass());
        return (List<E>) fetcher.apply(criteria);
    }

    private Function<DomainCriteria, List<? extends Domain>> getCriteriaFetcher(Class<? extends DomainCriteria> criteriaClass) {
        var criteriaFetcher = criteriaFetcherMap.get(criteriaClass);
        if(criteriaFetcher == null)
            throw new RuntimeException("No criteria fetcher found for " + criteriaClass);
        return criteriaFetcher;
    }

    private Function<DomainCriteria, List<? extends Domain>> fromMethod(Method method, Object object) {
        return domainCriteria -> {
            try {
                return (List<? extends Domain>) method.invoke(object, domainCriteria);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Couldn't run the criteria fetcher function ", e);
            }
        };
    }

}
