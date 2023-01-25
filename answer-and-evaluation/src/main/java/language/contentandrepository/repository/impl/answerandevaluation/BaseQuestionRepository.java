package language.contentandrepository.repository.impl.answerandevaluation;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.question.ChooseAWord;
import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.question.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Predicate;

@Repository(value = "questionRepository")
public class BaseQuestionRepository implements QuestionRepository {

    private final Map<Class<? extends Question>, BaseContentRepository> repositories;

    public BaseQuestionRepository(BaseChooseAWordQuestionRepository baseChooseAWordQuestionRepository) {
        this.repositories = Map.of(
                ChooseAWord.class, baseChooseAWordQuestionRepository
        );
    }

    public Question getById(String contentId) {
        return repositories.values().stream()
                .map(r -> r.findById(contentId))
                .filter(Optional::isPresent)
                .map(o -> (Question) o.get())
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Class<Question> provides() {
        return Question.class;
    }

    @Override
    public List<Question> find(Predicate<Question> predicate) {
        return repositories.values().stream()
                .flatMap(r -> r.find(predicate).stream())
                .toList();
    }

    @Override
    public Optional<Question> findById(DomainID<Question> id) {
        return repositories.get(id.type()).findById(id);
    }

    @Override
    public Optional<Question> findById(String id) {
        return repositories.values().stream().map(r -> (Question) r.getById(id)).findFirst();
    }

    @Override
    public List<Question> getByIds(List<DomainID<Question>> domainIDS) {
        return null;
    }

    @Override
    public DomainID<Question> generateId(String baseId) {
        return null;
    }

    @Override
    public boolean add(Question object) {
        return false;
    }

    @Override
    public void delete(Question object) {

    }

    @Override
    public boolean isPresent(Question object) {
        return false;
    }

}
