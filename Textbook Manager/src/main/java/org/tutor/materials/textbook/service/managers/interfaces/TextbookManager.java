package org.tutor.materials.textbook.service.managers.interfaces;

import org.tutor.materials.textbook.model.domain.input.TextbookInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.content.TextbookAccess;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.model.domain.type.users.Student;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;

import java.util.List;

public interface TextbookManager {

    Textbook createTextbook(ID<? extends AppUser> accessor, TextbookInput input);

    TextbookAccess addAccess(ID<Teacher> accessor, ID<Textbook> textbook, ID<Student> student);

    TextbookAccess removeAccess(ID<Teacher> accessor, ID<Textbook> textbook, ID<Student> student);

    TextbookAccess makePublic(ID<Teacher> accessor, ID<Textbook> textbook);

    TextbookAccess makePrivate(ID<Teacher> accessor, ID<Textbook> textbook, List<ID<Student>> allowedStudents);

    List<ID<Chapter>> addChapter(ID<? extends AppUser> accessor, ID<Textbook> textbookID, ID<Chapter> chapterID, Integer place);

    List<ID<Chapter>> moveChapter(ID<? extends AppUser> accessor, ID<Textbook> textbookID, ID<Chapter> chapterID, Integer place);

    List<ID<Chapter>> removeChapter(ID<? extends AppUser> accessor, ID<Textbook> textbookID, ID<Chapter> chapterID);
}
