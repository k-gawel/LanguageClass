package language.contentandrepository.persistence.entity.textbook;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "exercise_content")
class ExerciseTable extends ChapterContentEntity {

}
