package language.service.service.textbook.implementations;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.ChapterContent;
import language.contentandrepository.repository.textbook.ChapterContentRepository;
import language.contentandrepository.repository.textbook.ChapterRepository;
import language.graphql.textbook.input.ChapterUpdateInput;
import language.service.service.eventpublisher.DomainEvent;
import language.service.service.eventpublisher.PublishEvent;
import language.service.service.textbook.inputs.ChapterContentModifyInput;
import language.service.service.textbook.inputs.ChapterCreateInput;
import language.service.service.textbook.services.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("chapterService")
@RequiredArgsConstructor
public class PublisherChapterService implements ChapterService {

    private ChapterRepository chapterRepository;
    private ChapterContentRepository chapterContentRepository;

    @PublishEvent(DomainEvent.CHAPTER_CREATED)
    public Chapter createChapter(ChapterCreateInput input) {
        var id = chapterRepository.generateId(getBaseId(input));
        var chapter = new Chapter(id, input.title(), new ArrayList<>());
        chapterRepository.add(chapter);
        return chapter;
    }

    @PublishEvent(DomainEvent.CHAPTER_UPDATED)
    public Chapter updateChapter(ChapterUpdateInput input) {
        var newChapter = new Chapter(
                input.chapter().id(),
                input.title(),
                input.chapter().content()
        );
        chapterRepository.delete(input.chapter());
        chapterRepository.add(newChapter);
        return newChapter;
    }

    @PublishEvent(DomainEvent.CHAPTER_CONTENT_MODIFIED)
    public List<DomainID<ChapterContent>> modifyContent(ChapterContentModifyInput input) {
        Utils.modifyContent(input.chapter().content(), input.chapterContent(), input.newPlace());
        return input.chapter().content().stream().toList();
    }

    private String getBaseId(ChapterCreateInput input) {
        return input.title().replaceAll("\s", "_");
    }

}
