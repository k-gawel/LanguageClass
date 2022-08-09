package org.tutor.materials.textbook.service.managers.interfaces;

import org.tutor.materials.textbook.model.domain.input.ChapterInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.ChapterContent;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;

public interface ChapterManager extends ContentManager<Chapter, ChapterContent> {

    Chapter create(ID<Teacher> accessor, ChapterInput input);

//    List<ID<ChapterContent>> addContent(ID<AppUser> accessor, ID<Chapter> chapter, ID<ChapterContent> item, Integer place);
//
//    List<ID<ChapterContent>> moveContent(ID<AppUser> accessor, ID<Chapter> chapter, ID<ChapterContent> item, Integer place);
//
//    List<ID<ChapterContent>> removeContent(ID<AppUser> accessor, ID<Chapter> chapter, ID<ChapterContent> content);

}
