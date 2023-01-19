package language.service.service.textbook.services;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.ChapterContent;
import language.graphql.textbook.input.ChapterUpdateInput;
import language.service.service.textbook.inputs.ChapterContentModifyInput;
import language.service.service.textbook.inputs.ChapterCreateInput;

import java.util.List;

public interface ChapterService {

    Chapter createChapter(ChapterCreateInput input);
    Chapter updateChapter(ChapterUpdateInput input);
    List<DomainID<ChapterContent>> modifyContent(ChapterContentModifyInput input);

}
