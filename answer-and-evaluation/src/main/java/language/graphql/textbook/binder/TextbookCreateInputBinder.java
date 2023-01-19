package language.graphql.textbook.binder;

import language.contentandrepository.repository.user.TeacherRepository;
import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.TextbookInput;
import language.service.service.textbook.inputs.TextbookCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextbookCreateInputBinder implements InputBinder<TextbookInput, TextbookCreateInput> {

    private final TeacherRepository teacherRepository;

    @Override
    public TextbookCreateInput bind(TextbookInput source) {
        return new TextbookCreateInput(
                teacherRepository.findById(source.author()).orElse(null),
                source.title()
        );
    }

    @Override
    public Class<TextbookInput> consumes() {
        return TextbookInput.class;
    }

    @Override
    public Class<TextbookCreateInput> generates() {
        return TextbookCreateInput.class;
    }
}
