package org.tutor.materials.textbook.service.managers.implementation.textbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.exceptions.ElementAbsentException;
import org.tutor.materials.textbook.exceptions.ElementAlreadyInsertedException;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.users.Student;
import org.tutor.materials.textbook.model.entity.content.TextbookEntity;
import org.tutor.materials.textbook.model.entity.users.StudentEntity;
import org.tutor.materials.textbook.service.DAO;
import org.tutor.materials.textbook.service.repository.interfaces.StudentRepository;
import org.tutor.materials.textbook.service.repository.interfaces.TextbookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
class TextbookAccessManager {

    private final DAO dao;
    private final TextbookRepository textbookRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TextbookAccessManager(DAO dao, TextbookRepository textbookRepository, StudentRepository studentRepository) {
        this.dao = dao;
        this.textbookRepository = textbookRepository;
        this.studentRepository = studentRepository;
    }


    public void addAccess(ID<Textbook> textbookID, ID<Student> studentID) {
        var textbook = dao.getById(textbookID, TextbookEntity.class);
        var student = dao.getReference(studentID, StudentEntity.class);

        var accessors = accessorsReferences(textbookID);

        if(accessors.contains(student))
            throw new ElementAlreadyInsertedException(textbookID, studentID);

        accessors.add(student);
        textbook.setUsers(accessors);
        dao.save(textbook);
    }

    public void removeAccess(ID<Textbook> textbookID, ID<Student> studentID) {
        var textbook = dao.getReference(textbookID, TextbookEntity.class);
        var student = dao.getReference(studentID, StudentEntity.class);

        var accessors = accessorsReferences(textbookID);

        if(!accessors.remove(student))
            throw new ElementAbsentException(textbookID, studentID);

        textbook.setUsers(accessors);
        dao.save(textbook);
    }

    public void makePublic(ID<Textbook> textbookID) {
        var textbook = dao.getById(textbookID, TextbookEntity.class);

        textbook.setPublic(true);
        textbook.setUsers(new ArrayList<>());
        dao.save(textbook);
    }

    public void makePrivate(ID<Textbook> textbookID) {
        var textbook = dao.getById(textbookID, TextbookEntity.class);

        textbook.setPublic(false);
        textbook.setUsers(new ArrayList<>());
        dao.save(textbook);
    }

    private List<StudentEntity> accessorsReferences(ID<Textbook> id) {
        return studentRepository.getTextbookAccessors(id).stream()
                .map(Student::id)
                .map(ID::id)
                .map(StudentEntity::new)
                .toList();
    }

}
