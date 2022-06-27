package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.Chapter;
import org.tutor.materials.model.domain.Textbook;
import org.tutor.materials.model.entity.materialsource.textbook.TextbookEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.ChapterEntity;
import org.tutor.materials.repository.generator.QueryGenerator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TextbookRepository extends BasicRepository<TextbookEntity, Textbook> {

    @Query("SELECT t.content FROM TextbookEntity t WHERE t.UUID = ?1")
    List<ChapterEntity> findContentList(UUID uuid);

    @Query("SELECT c FROM ChapterEntity c WHERE c.UUID = ?1")
    Optional<ChapterEntity> findContentEntity(UUID uuid);
}
