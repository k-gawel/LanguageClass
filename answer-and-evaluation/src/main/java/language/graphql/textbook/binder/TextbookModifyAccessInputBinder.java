package language.graphql.textbook.binder;

import language.contentandrepository.repository.textbook.TextbookAccessRepository;
import language.contentandrepository.repository.textbook.TextbookRepository;
import language.contentandrepository.repository.user.StudentRepository;
import language.contentandrepository.repository.user.TeacherRepository;
import language.graphql.shared.InputBinder;
import language.graphql.textbook.input.TextbookAccessInput;
import language.service.service.textbook.inputs.TextbookModifyAccessInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextbookModifyAccessInputBinder implements InputBinder<TextbookAccessInput, TextbookModifyAccessInput> {

    private final StudentRepository studentRepository;
    private final TextbookRepository textbookRepository;
    private final TeacherRepository teacherRepository;
    private final TextbookAccessRepository textbookAccessRepository;

    @Override
    public TextbookModifyAccessInput bind(TextbookAccessInput source) {
        return new TextbookModifyAccessInput(
                studentRepository.findById(source.student()).orElse(null),
                textbookRepository.findById(source.textbook()).orElse(null),
                teacherRepository.findById(source.teacher()).orElse(null),
                textbookAccessRepository.findByTextbook(source.textbook()).orElse(null),
                source.access()
        );
    }

    @Override
    public Class<TextbookAccessInput> consumes() {
        return TextbookAccessInput.class;
    }

    @Override
    public Class<TextbookModifyAccessInput> generates() {
        return TextbookModifyAccessInput.class;
    }
}
