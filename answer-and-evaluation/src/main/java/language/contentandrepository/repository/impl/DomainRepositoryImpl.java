package language.contentandrepository.repository.impl;

import language.contentandrepository.criteria.DomainCriteria;
import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.repository.DomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DomainRepositoryImpl implements DomainRepository {

    private final ByIdRepository byIdRepository;
    private final CriteriaRepository criteriaRepository;

    @Override
    public <E extends Domain> Optional<E> find(DomainID<E> id) {
        return byIdRepository.find(id);
    }

    @Override
    public <E extends Domain> List<E> find(DomainCriteria<E> criteria) {
        return criteriaRepository.find(criteria);
    }

}
