package language.service.service.question.implementations;

import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.model.domain.question.FillAWord;
import language.contentandrepository.repository.question.ChooseAWordQuestionRepository;
import language.contentandrepository.repository.question.FillAWordQuestionRepository;
import language.graphql.question.input.ChooseAWordInput;
import language.graphql.question.input.FillAWordInput;
import language.service.service.eventpublisher.DomainEvent;
import language.service.service.eventpublisher.PublishEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ChooseAWordQuestionRepository chooseAWordQuestionRepository;
    private final FillAWordQuestionRepository fillAWordQuestionRepository;

    @PublishEvent(DomainEvent.QUESTION_CREATED_CHOOSE_A_WORD)
    public ChooseAWord create(@Valid ChooseAWordInput input) {
        var id = chooseAWordQuestionRepository.generateId("choose a word question");

        var newQuestion = new ChooseAWord(
                id,
                input.content(),
                input.correctAnswers(),
                input.wordChoice()
        );

        chooseAWordQuestionRepository.add(newQuestion);

        return newQuestion;
    }

    @PublishEvent(DomainEvent.QUESTION_CREATED_FILL_A_WORD)
    public FillAWord create(@Valid FillAWordInput input) {
        var id = fillAWordQuestionRepository.generateId("fill a word question");

        var newQuestion = new FillAWord(
                id,
                input.content(),
                input.correctAnswers()
        );

        fillAWordQuestionRepository.add(newQuestion);

        return newQuestion;
    }

}
