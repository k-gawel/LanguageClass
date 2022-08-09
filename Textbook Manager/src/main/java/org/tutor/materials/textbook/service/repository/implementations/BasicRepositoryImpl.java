package org.tutor.materials.textbook.service.repository.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.users.Student;
import org.tutor.materials.textbook.service.repository.interfaces.BasicRepository;
import org.tutor.materials.textbook.service.repository.interfaces.ChapterRepository;
import org.tutor.materials.textbook.service.repository.interfaces.StudentRepository;
import org.tutor.materials.textbook.service.repository.interfaces.TextbookRepository;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class BasicRepositoryImpl implements BasicRepository {

    public static final String TUPLES_PACKAGE = "org.tutor.materials.service.repository.implementations.tuples.";

    @PersistenceContext
    EntityManager entityManager;

    private final TextbookRepository textbookRepository;
    private final StudentRepository studentRepository;
    private final ChapterRepository chapterRepository;

    @Autowired
    public BasicRepositoryImpl(TextbookRepository textbookRepository, StudentRepository studentRepository, ChapterRepository chapterRepository) {
        this.textbookRepository = textbookRepository;
        this.studentRepository = studentRepository;
        this.chapterRepository = chapterRepository;
    }


    private Textbook textbookFromTuple(Tuple tuple) {
        return new Textbook(
                new ID(Textbook.class, tuple.get("id", String.class)),
                tuple.get("title", String.class),
                tuple.get("contentIds", List.class)
        );
    }

    @Nonnull
    private <Q> Q fromTuple(Tuple tuple, Class<Q> clazz) {
        if(clazz.equals(Textbook.class))
            return (Q) textbookFromTuple(tuple);
        return null;
    }

    @Override
    public <Q extends Domain> Optional<Q> findById(ID<Q> id) {
        if(id.type().equals(Textbook.class))
            return (Optional<Q>) textbookRepository.findById((ID<Textbook>) id);
        else if(id.type().equals(Student.class))
            return (Optional<Q>) studentRepository.findById((ID<Student>) id);
        else if(id.type().equals(Chapter.class))
            return (Optional<Q>) chapterRepository.findById((ID<Chapter>) id);
        else
            throw new IllegalStateException(id.type().getTypeName());
    }


}
