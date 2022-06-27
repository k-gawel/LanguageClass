package org.tutor.materials.shared;

import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.domain.*;
import org.tutor.materials.model.domain.interfaces.Identifiable;
import org.tutor.materials.model.entity.AbstractEntity;
import org.tutor.materials.model.entity.materialsource.textbook.TextbookEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.ChapterEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseAnswerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseContentEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.ExerciseEvaluationEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.QuestionAnswerEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.evaluation.QuestionEvaluationEntity;
import org.tutor.materials.model.entity.materialsource.textbook.chapter.chapterpart.exercise.question.questions.ChooseAWordContentEntity;

import java.io.IOException;
import java.util.Map;

@Service
public class EntityAndDomainWirer {

    private final Map<Class<? extends Identifiable>, Class<? extends AbstractEntity>> map = Map.of(
            Chapter.class, ChapterEntity.class,
            ChooseAWordQuestionContent.class, ChooseAWordContentEntity.class,
            ExerciseAnswer.class, ExerciseAnswerEntity.class,
            ExerciseContent.class, ExerciseContentEntity.class,
            ExerciseEvaluation.class, ExerciseEvaluationEntity.class,
            QuestionAnswer.class, QuestionAnswerEntity.class,
            QuestionEvaluation.class, QuestionEvaluationEntity.class,
            Textbook.class, TextbookEntity.class
            );

    private final String packageName = "org.tutor.materials.model.domain";

    @SuppressWarnings("UnstableApiUsage")
    public EntityAndDomainWirer() throws IOException {
        System.out.println("Creating wirer.");
        ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(c -> c.getPackageName().equalsIgnoreCase(packageName))
                .map(ClassPath.ClassInfo::load)
                .peek(c -> System.out.print(c.getSimpleName()))
                .filter(c -> !map.containsKey(c))
                .findAny()
                .ifPresent(c -> {
                    throw new BeanInitializationException("Class " + c.getSimpleName() + " has no assigned entity.");
                });
    }

    public Class<? extends AbstractEntity> fromDomain(Class<? extends Identifiable> domain) {
        return map.get(domain);
    }

}
