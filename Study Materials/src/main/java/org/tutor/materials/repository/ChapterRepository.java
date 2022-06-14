package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.materialsource.textbook.Textbook;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.Chapter;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPart;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    @Query("SELECT chapters FROM Textbook t WHERE t.id = ?1")
    List<Chapter> findByTextbook(Long textbookId);

    default List<Chapter> findByTextbook(Textbook textbook) {
        return this.findByTextbook(textbook.getId());
    }

    @Query("SELECT content FROM Chapter c WHERE c = ?1")
    List<ChapterPart> findByChapter(Chapter chapter);

    @Query("SELECT content FROM Chapter c WHERE c = ?1")
    List<ChapterPart> getChapterContent(Chapter chapter);

}
