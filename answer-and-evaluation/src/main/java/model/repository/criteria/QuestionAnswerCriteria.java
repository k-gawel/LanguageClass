package model.repository.criteria;

import java.util.Date;
import java.util.List;
import java.util.Map;

public record QuestionAnswerCriteria(
        List<String> ids,
        List<String> questions,
        List<String> students,
        Date startDate,
        Date endDate,
        Integer limit,
        Integer offset
) implements Criteria {

}
