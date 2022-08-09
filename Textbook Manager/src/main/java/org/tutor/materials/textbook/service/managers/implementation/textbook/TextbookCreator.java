package org.tutor.materials.textbook.service.managers.implementation.textbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.model.domain.input.TextbookInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.entity.content.TextbookEntity;
import org.tutor.materials.textbook.model.entity.users.StudentEntity;
import org.tutor.materials.textbook.model.entity.users.TeacherEntity;
import org.tutor.materials.textbook.service.DAO;
import org.tutor.materials.textbook.service.managers.implementation.Creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
class TextbookCreator extends Creator {


    @Autowired
    public TextbookCreator(DAO dao) {
        super(dao);
    }

    public Textbook create(TextbookInput input) {
        var entity = fromInput(input);

        return new Textbook(
                new ID<>(Textbook.class, entity.getId()),
                entity.getTitle(),
                new ArrayList<>()
        );
    }

    TextbookEntity fromInput(TextbookInput input) {
        var creator = dao.getReference(input.creator(), TeacherEntity.class);
        var allowedStudents = dao.findByIds(input.allowedStudents(), StudentEntity.class);

        var entity = createTextbookEntity(creator, input.title(), input.isPublic(), allowedStudents);
        saveNewEntity(entity);
        return entity;
    }


    TextbookEntity createTextbookEntity(TeacherEntity creator, String title, boolean isPublic, List<StudentEntity> allowedStudents) {
        var entity = new TextbookEntity();
        entity.setCreatedBy(creator);
        entity.setTitle(title);
        entity.setPublic(isPublic);
        entity.setUsers(allowedStudents == null ? Collections.emptyList() : allowedStudents);

        return entity;
    }

}
