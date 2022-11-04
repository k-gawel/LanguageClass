package language.contentandrepository.repository;

import language.contentandrepository.model.Domain;
import language.contentandrepository.model.DomainID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DomainCache<E extends Domain> implements IdsRepository {

    private final Map<DomainID<E>, E> map = new HashMap<>();

    public boolean add(E object) {

        if(map.containsKey(object.id()))

            return false;

        map.put(object.id(), object);
        return true;
    }

    
    public boolean delete(E object) {
        if(map.containsKey(object.id())) {
            map.remove(object.id());
            return true;
        } else {
            return false;
        }
    }

    public Optional<E> findById(DomainID<E> id) {
        return findById(id.id());
    }

    public Optional<E> findById(String idString) {
        return map.entrySet().stream()
                .filter(e -> e.getKey().id().equals(idString))
                .map(Map.Entry::getValue)
                .findFirst();
    }


    @Override
    public List<String> findIdsStartingWith(String beginString) {
        return map.keySet().stream().map(DomainID::id).filter(i -> i.startsWith(beginString)).toList();
    }

}
