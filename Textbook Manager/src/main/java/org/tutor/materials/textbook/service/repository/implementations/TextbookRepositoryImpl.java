package org.tutor.materials.textbook.service.repository.implementations;

import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.service.repository.interfaces.TextbookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TextbookRepositoryImpl implements TextbookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TextbookRepositoryImpl() {
    }

    @Override
    public List<Textbook> findByIds(Collection<ID<Textbook>> ids) {
        var stringIds = ids.stream().map(ID::id).toList();
        var sql = "SELECT new io.vavr.Tuple3(e.id, e.title, c.id) " +
                  "FROM TextbookEntity e LEFT JOIN e.content c WHERE e.id IN :ids";

        return entityManager.createQuery(sql, Tuple3.class)
                .setParameter("ids", stringIds)
                .getResultList().stream()
                .collect(Collectors.groupingBy(
                        t -> new Tuple2(t._1, t._2),
                        Collectors.mapping(t -> (String) t._3, Collectors.toList())
                )).entrySet().stream()
                .map(this::fromTupleEntry).toList();
    }

    @Override
    public Optional<Textbook> findById(ID<Textbook> id) {
        var results =  findByIds(List.of(id));
        if(results.size() == 1)
            return Optional.of(results.get(0));
        else if(results.size() == 0)
            return Optional.empty();
        else
            throw new IllegalStateException(id + " accords to more than one record in databse");
    }

    
    @Override
    public boolean isPublic(ID<Textbook> textbook) {
        return true;
    }


    private Textbook fromTupleEntry(Map.Entry<Tuple2, List<String>> entry) {
        return createTextbook(entry.getKey(), entry.getValue());
    }

    private Textbook createTextbook(Tuple2<String, String> idAndTitle, List<String> contentIds) {
        var content =  contentIds.stream()
                .map(i -> (new ID<>(Chapter.class, i)))
                .toList();
        var id = new ID<>(Textbook.class, idAndTitle._1);
        var title = idAndTitle._2;
        return new Textbook(id, title, content);
    }

}
