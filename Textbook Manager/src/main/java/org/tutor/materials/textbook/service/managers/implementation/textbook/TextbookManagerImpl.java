package org.tutor.materials.textbook.service.managers.implementation.textbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.model.domain.input.ContentModificationAction;
import org.tutor.materials.textbook.model.domain.input.ContentModificationInput;
import org.tutor.materials.textbook.model.domain.input.TextbookAccessInput;
import org.tutor.materials.textbook.model.domain.input.TextbookInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.content.TextbookAccess;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.model.domain.type.users.Student;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;
import org.tutor.materials.textbook.service.events.events.TextbookAccessEvent;
import org.tutor.materials.textbook.service.events.events.TextbookCreationEvent;
import org.tutor.materials.textbook.service.events.processors.PublishEvent;
import org.tutor.materials.textbook.service.managers.interfaces.TextbookManager;

import java.util.List;

@Service
public class TextbookManagerImpl implements TextbookManager {

    private final TextbookCreator creator;
    private final TextbookAccessManager accessManager;
    private final TextbookContentManager contentManger;

    @Autowired
    public TextbookManagerImpl(TextbookCreator creator, TextbookAccessManager accessManager, TextbookAccessManager accessManager1, TextbookContentManager contentManger) {
        this.creator = creator;
        this.accessManager = accessManager1;
        this.contentManger = contentManger;
    }

    @Override
    @PublishEvent(TextbookCreationEvent.class)
    public Textbook createTextbook(ID<? extends AppUser> accessor, TextbookInput input) {
        return creator.create(input);
    }

    @Override
    public TextbookAccess addAccess(ID<Teacher> accessor, ID<Textbook> textbook, ID<Student> student) {
        var input = new TextbookAccessInput(textbook, false, student, TextbookAccessInput.TextbookAccessAction.GRANT);
        return modifyAccess(accessor, input);
    }

    @Override
    public TextbookAccess removeAccess(ID<Teacher> accessor, ID<Textbook> textbook, ID<Student> student) {
        var input = new TextbookAccessInput(textbook, false, student, TextbookAccessInput.TextbookAccessAction.DENY);
        return modifyAccess(accessor, input);
    }

    @Override
    public TextbookAccess makePublic(ID<Teacher> accessor, ID<Textbook> textbook) {
        var input = new TextbookAccessInput(textbook, true, null, TextbookAccessInput.TextbookAccessAction.GRANT);
        return modifyAccess(accessor, input);
    }

    @Override
    public TextbookAccess makePrivate(ID<Teacher> accessor, ID<Textbook> textbook, List<ID<Student>> allowedStudents) {
        var input = new TextbookAccessInput(textbook, true, null, TextbookAccessInput.TextbookAccessAction.DENY);
        return modifyAccess(accessor, input);
    }

    @Override
    public List<ID<Chapter>> addChapter(ID<? extends AppUser> accessor, ID<Textbook> textbookID, ID<Chapter> chapterID, Integer place) {
        var input = new ContentModificationInput<>(textbookID, chapterID, place, ContentModificationAction.ADD);
        return contentManger.modifyContent(accessor, input);
    }

    @Override
    public List<ID<Chapter>> moveChapter(ID<? extends AppUser> accessor, ID<Textbook> textbookID, ID<Chapter> chapterID, Integer place) {
        var input = new ContentModificationInput<>(textbookID, chapterID, place, ContentModificationAction.MOVE);
        return contentManger.modifyContent(accessor, input);
    }

    @Override
    public List<ID<Chapter>> removeChapter(ID<? extends AppUser> accessor, ID<Textbook> textbookID, ID<Chapter> chapterID) {
        var input = new ContentModificationInput<>(textbookID, chapterID, null, ContentModificationAction.REMOVE);
        return contentManger.modifyContent(accessor, input);
    }



    @PublishEvent(TextbookAccessEvent.class)
    public TextbookAccess modifyAccess(ID<? extends AppUser> accessor, TextbookAccessInput input) {
        switch (input.action()) {
            case GRANT -> {
                if (input.student() == null)
                    accessManager.makePublic(input.textbook());
                else
                    accessManager.addAccess(input.textbook(), input.student());
            }
            case DENY -> {
                if (input.student() == null)
                    accessManager.makePrivate(input.textbook());
                else
                    accessManager.removeAccess(input.textbook(), input.student());
            }
        }
        return new TextbookAccess(input.action().equals(TextbookAccessInput.TextbookAccessAction.GRANT), input.student());
    }


}
