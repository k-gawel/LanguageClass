package language.graphql.shared;

import language.contentandrepository.model.Domain;

public abstract class DomainResolver<E extends Domain>  {

    public String id(E domain) {
        return domain.id().id();
    }

}
