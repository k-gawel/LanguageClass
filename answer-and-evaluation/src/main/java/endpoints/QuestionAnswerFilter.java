package endpoints;

import model.domain.*;
import org.jooq.codegen.TextWriter;

import java.util.List;

public class QuestionAnswerFilter {

    /**
     * @param accessor
     * @param answers
     * @return answers that are accessible for evaluator.
     */
    public List<QuestionAnswer> filter(ID<AppUser> accessor, List<QuestionAnswer> answers) {
        if(accessor.type().equals(Teacher.class))
            return answers;

        return answers.stream().filter(a -> a.student().equals(accessor)).toList();
    }

}
