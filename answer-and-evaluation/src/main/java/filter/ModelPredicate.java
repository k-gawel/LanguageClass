package filter;

import model.domain.user.AppUser;
import model.domain.Domain;
import model.domain.ID;

public interface ModelPredicate<E extends Domain> {

    boolean hasAccess(ID<? extends AppUser> user, E object);

}
