package org.tutor.materials.repository;

import org.checkerframework.framework.qual.EnsuresQualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.domain.Chapter;
import org.tutor.materials.model.domain.interfaces.ChapterPart;
import org.tutor.materials.model.entity.materialsource.textbook.TextbookEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.ChapterEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.ChapterPartEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChapterRepository extends BasicRepository<ChapterEntity, Chapter> {

    default List<org.tutor.materials.model.domain.interfaces.UUID<ChapterPart>> getContentIds(org.tutor.materials.model.domain.interfaces.UUID<Chapter> chapterUUID) {
        return getContentIds(chapterUUID.uuid()).stream()
                .map(u -> new org.tutor.materials.model.domain.interfaces.UUID<ChapterPart>(u, ChapterPart.class))
                .toList();
    }

    @Query(value = "SELECT cast(T.content_uuid as varchar) FROM chapter_content as T WHERE T.chapter_uuid = ?1", nativeQuery = true)
    List<String> getContentIds(UUID chapterUUID);

    @Query("SELECT t.content FROM TextbookEntity  t WHERE t = ?1")
    List<ChapterEntity> findByTextbook(TextbookEntity textbookEntity);

    @Query(value = "SELECT cast(t.chapter_id as varchar) FROM textbook_chapter as t WHERE cast(t.textbook_id as varchar) = ?1", nativeQuery = true)
    List<String> findIdsByTextbook(String textbookUUID);

    @Query("SELECT c.content FROM ChapterEntity c WHERE c.UUID = ?1")
    List<ChapterPartEntity> findContentList(UUID uuid);

    @Query("SELECT p FROM ChapterPartEntity p WHERE p.UUID = ?1")
    Optional<ChapterPartEntity> findContentEntity(UUID uuid);

    default List<UUID> findIdsByTextbook(UUID textbookUUID) {
        System.out.println("LOOKING FOR " + textbookUUID.toString());
        return findIdsByTextbook(textbookUUID.toString())
                .stream()
                .map(UUID::fromString)
                .toList();
    }

    @Query("SELECT c.content FROM ChapterEntity c WHERE c = ?1")
    List<ChapterPartEntity> findByChapter(ChapterEntity chapter);

    @Query("SELECT c.content FROM ChapterEntity c WHERE c = ?1")
    List<ChapterPartEntity> getChapterContent(ChapterEntity chapter);

    @Query("SELECT p FROM ChapterPartEntity p WHERE p.UUID = ?1")
    Optional<ChapterPartEntity> findChapterPartByUUID(java.util.UUID uuid);

}
