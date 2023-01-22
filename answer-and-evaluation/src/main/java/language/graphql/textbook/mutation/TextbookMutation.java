package language.graphql.textbook.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.graphql.textbook.binder.TextbookCreateInputBinder;
import language.graphql.textbook.binder.TextbookModifyAccessInputBinder;
import language.graphql.textbook.binder.TextbookModifyContentInputBinder;
import language.graphql.textbook.input.ModifyContentInput;
import language.graphql.textbook.input.TextbookAccessInput;
import language.graphql.textbook.input.TextbookInput;
import language.service.service.textbook.services.TextbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TextbookMutation implements GraphQLMutationResolver {

    private final TextbookService textbookService;

    private final TextbookCreateInputBinder textbookCreateInputBinder;
    private final TextbookModifyAccessInputBinder textbookModifyAccessInputBinder;
    private final TextbookModifyContentInputBinder textbookModifyContentInputBinder;

    @PreAuthorize("hasAuthority('TEACHER') and principal.username.equals(#input.author())")
    public Textbook createTextbook(TextbookInput rawInput) {
        var input = textbookCreateInputBinder.bind(rawInput);
        return textbookService.createTextbook(input);
    }

    @PreAuthorize("hasAuthority('TEACHER') and principal.username.equals(#input.teacher())")
    public boolean changeAccess(TextbookAccessInput rawInput) {
        var input = textbookModifyAccessInputBinder.bind(rawInput);
        return textbookService.changeAccess(input);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    public List<String> addTextbookContent(String textbookId, String chapterId) {
        var rawInput = new ModifyContentInput(textbookId, chapterId, Integer.MAX_VALUE);
        var input = textbookModifyContentInputBinder.bind(rawInput);
        return textbookService.modifyContent(input).stream().map(DomainID::id).toList();
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    public List<String> removeTextbookContent(String textbookId, String chapterId) {
        var rawInput = new ModifyContentInput(textbookId, chapterId, -1);
        var input = textbookModifyContentInputBinder.bind(rawInput);
        return textbookService.modifyContent(input).stream().map(DomainID::id).toList();
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    public List<String> moveTextbookContent(String textbookId, String chapterId, Integer place) {
        var rawInput = new ModifyContentInput(textbookId, chapterId, place);
        var input = textbookModifyContentInputBinder.bind(rawInput);
        return textbookService.modifyContent(input).stream().map(DomainID::id).toList();
    }


}
