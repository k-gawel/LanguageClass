package org.tutor.materials.service.materialsource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.dto.input.TextbookInput;
import org.tutor.materials.model.entity.materialsource.textbook.Textbook;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.users.Teacher;
import org.tutor.materials.repository.TextbookRepository;

@Service
public class TextbookService {

    private final TextbookRepository textbookRepository;

    @Autowired
    public TextbookService(TextbookRepository textbookRepository) {
        this.textbookRepository = textbookRepository;
    }

    public Textbook createNewTextbook(Teacher teacher, TextbookInput input) {
        var newTextBook = new Textbook();
        newTextBook.setCreatedBy(teacher);
        newTextBook.setName(input.name());

        return textbookRepository.save(newTextBook);
    }

    public void addChapter(Textbook textbook, Chapter chapter, int place) {
        var chapters = textbook.getChapters();
        try {
            chapters.add(place - 1, chapter);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            chapters.add(chapter);
        }
        textbookRepository.save(textbook);
    }

}
