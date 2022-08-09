package org.tutor.materials.textbook.service.repository.implementations;

import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.ChapterContent;
import org.tutor.materials.textbook.service.repository.interfaces.ChapterRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChapterRepositoryImpl implements ChapterRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Chapter> findByIds(Collection<ID<Chapter>> ids) {
        var stringIds = ids.stream().map(ID::id).toList();
        var sql = "SELECT new io.vavr.Tuple3(e.id, e.title, c.id) " +
                "FROM ChapterEntity e LEFT JOIN e.content c WHERE e.id IN :ids";

        return entityManager.createQuery(sql, Tuple3.class)
                .setParameter("ids", stringIds)
                .getResultList().stream()
                .peek(System.out::println)
                .collect(Collectors.groupingBy(
                        t -> new Tuple2((String) t._1(), (String) t._2()),
                        Collectors.mapping(t -> (String) t._3, Collectors.toList())
                )).entrySet().stream()
                .map(this::fromTupleEntry).toList();
    }

    @Override
    public Optional<Chapter> findById(ID<Chapter> id) {
        return findByIds(List.of(id)).stream().findFirst();
    }

    Chapter fromTupleEntry(Map.Entry<Tuple2, List<String>> entry) {
        return createChapter(entry.getKey(), entry.getValue());
    }

    Chapter createChapter(Tuple2<String, String> idAndTitle, List<String> contentIds) {
        var content =  contentIds.stream()
            .map(i -> (new ID<>(ChapterContent.class, i)))
            .collect(Collectors.toCollection(() -> new ArrayList<ID<? extends ChapterContent>>()));
        var id = new ID<>(Chapter.class, idAndTitle._1);
        var title = idAndTitle._2;
        return new Chapter(id, title, content);
    }

}
