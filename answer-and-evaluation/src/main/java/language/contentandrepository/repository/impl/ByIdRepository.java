package language.contentandrepository.repository.impl;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ByIdRepository {

    private final List<ContentRepository> repositories;
    private Map<Class<? extends Domain>, ContentRepository> repositoryMap;

    public <E extends Domain> Optional<E> find(DomainID<E> id) {
        return getRepository(id.type()).findById(id);
    }

    private <E extends Domain> ContentRepository getRepository(Class<E> type) {
        var repository = repositoryMap.get(type);
        if(repository == null)
            throw new RuntimeException("No repository found for " + type);
        return repository;
    }

}
