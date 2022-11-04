package language.contentandrepository.persistence.mapper;

import language.contentandrepository.persistence.entity.IdentifiableEntity;
import language.contentandrepository.model.Domain;

public interface EntityToModelMapper<E extends IdentifiableEntity, M extends Domain> {

    M fromEntity(E entity);

    E toEntity(M model);

}
