package language.contentandrepository.persistence.entity.textbook;

import language.contentandrepository.persistence.entity.IdentifiableEntity;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "textbook")
abstract class TextbookTable extends IdentifiableEntity {
}
