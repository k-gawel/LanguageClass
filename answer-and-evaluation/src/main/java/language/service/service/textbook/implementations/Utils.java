package language.service.service.textbook.implementations;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;

import java.util.List;

public final class Utils {

    public static <E extends Domain> void modifyContent(List<DomainID<E>> content, E object, int newPlace) {
        content.remove(object);

        if(newPlace < 0) {
        } else if(newPlace > content.size()) {
            content.add(object.id());
        } else {
            content.add(newPlace, object.id());
        }
    }

}
