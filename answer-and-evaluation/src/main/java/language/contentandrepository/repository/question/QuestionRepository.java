package language.contentandrepository.repository.question;

import language.contentandrepository.model.domain.question.Question;
import language.contentandrepository.repository.ContentRepository;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class QuestionRepository implements ContentRepository<Question> {

    private final ChooseAWordQuestionRepository chooseAWordQuestionRepository;

    @Override
    public Optional<Question> findById(DomainID<Question> id) {
        return null;
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
    public boolean save(Question object) {
        return false;
    }

    @Override
    public void delete(Question object) {

    }

    @Override
    public boolean isPersisted(Question object) {
        return false;
    }

    @Override
    public boolean isPresent(Question object) {
        return false;
    }


}
