package language.graphql.textbook.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.graphql.textbook.binder.ChapterCreateInputBinder;
import language.graphql.textbook.binder.ChapterModifyContentBinder;
import language.graphql.textbook.binder.ChapterUpdateInputBinder;
import language.graphql.textbook.input.ChapterInput;
import language.graphql.textbook.input.ModifyContentInput;
import language.service.service.textbook.services.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@PreAuthorize("hasAuthority('TEACHER')")
public class ChapterMutation implements GraphQLMutationResolver {

    private final ChapterService chapterService;

    private final ChapterCreateInputBinder chapterCreateInputBinder;
    private final ChapterModifyContentBinder chapterModifyContentBinder;
    private final ChapterUpdateInputBinder chapterUpdateInputBinder;

    public Chapter createChapter(ChapterInput rawInput) {
        var input = chapterCreateInputBinder.bind(rawInput);
        return chapterService.createChapter(input);
    }

    public Chapter updateChapter(ChapterInput rawInput) {
        var input = chapterUpdateInputBinder.bind(rawInput);
        return chapterService.updateChapter(input);
    }

    public List<String> addContent(String chapterId, String contentId) {
        var rawInput = new ModifyContentInput(chapterId, contentId, Integer.MAX_VALUE);
        return modifyContentAndGetIds(rawInput);
    }

    public List<String> removeContent(String chapterId, String contentId) {
        var rawInput = new ModifyContentInput(chapterId, contentId, Integer.MIN_VALUE);
        return modifyContentAndGetIds(rawInput);
    }

    public List<String> moveContent(String chapterId, String contentId, int newPlace) {
        var rawInput = new ModifyContentInput(chapterId, contentId, newPlace);
        return modifyContentAndGetIds(rawInput);
    }

    private List<String> modifyContentAndGetIds(ModifyContentInput rawInput) {
        var input = chapterModifyContentBinder.bind(rawInput);
        return chapterService
                .modifyContent(input)
                .stream()
                .map(DomainID::id)
                .toList();
    }
}