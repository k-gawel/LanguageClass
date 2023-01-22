package language.contentandrepository.criteria;

import language.contentandrepository.model.Domain;

public @interface DomainType {

    Class<? extends Domain> value();

}
