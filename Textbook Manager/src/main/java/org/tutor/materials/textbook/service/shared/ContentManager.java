package org.tutor.materials.textbook.service.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.exceptions.NotFoundException;
import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.entity.shared.AbstractEntity;
import org.tutor.materials.textbook.model.entity.shared.OrderedContentContainerEntity;
import org.tutor.materials.textbook.service.DAO;
import org.tutor.materials.textbook.shared.DomainToEntityWirer;

import java.util.List;

@Service
public final class ContentManager<P extends Domain, C extends Domain> {

    private final DAO dao;

    @Autowired
    public ContentManager(DAO dao) {
        this.dao = dao;
    }

    public List<ID<C>> add(ID<P> textbookID, ID<C> chapterID, Integer place) {
        var input = new Input<>(textbookID, chapterID, place);
        return execute(input, Execution::place, FailOn::onPresent);
    }

    public List<ID<C>> remove(ID<P> textbookID, ID<C> chapterID) {
        var input = new Input<>(textbookID, chapterID, null);
        return execute(input, Execution::remove, FailOn::onAbsent);
    }

    public List<ID<C>> move(ID<P> textbookID, ID<C> chapterID, Integer place) {
        var input = new Input<>(textbookID, chapterID, place);
        return execute(input, Execution::place, FailOn::onPresent);
    }

    private List<ID<C>> execute(Input<P, C> input,
                                Execution execution,
                                FailOn<P, C> validator
    ) {
        var parentEntity = getParent(input.parent());
        var childEntity = getChild(input.child());

        var newContent = getNewContentIds(input, execution, validator, parentEntity);
        updateEntity(parentEntity, childEntity.getClass(), newContent);

        return newContent.stream().map(i -> new ID<>(input.child().type(), i)).toList();
    }

    private OrderedContentContainerEntity getParent(ID<? extends Domain> parentId) {
        var parentEntity = DomainToEntityWirer.getEntityClass(parentId.type());
        return dao.findById(parentId.id(), parentEntity)
                .filter(p -> p instanceof OrderedContentContainerEntity)
                .map(p -> (OrderedContentContainerEntity) p)
                .orElseThrow(() -> new NotFoundException(parentId));
    }

    private AbstractEntity getChild(ID<C> child) {
        var childEntity = DomainToEntityWirer.getEntityClass(child.type());
        return dao.findById(child.id(), childEntity)
                .orElseThrow(() -> new NotFoundException(child));
    }

    private List<String> getNewContentIds(
                Input<P, C> input,
                Execution execution,
                FailOn<P, C> validator,
                OrderedContentContainerEntity parentEntity
    ) {
        var contentIds = dao.findChildrenIds(parentEntity);
        validator.accept(input.parent(), contentIds, input.child());
        return execution.apply(contentIds, input.child().id(), input.place());
    }


    //sets new content to parent entity and saves it in database
    private void updateEntity(OrderedContentContainerEntity parentEntity, Class<? extends AbstractEntity> childEntity, List<String> newContent) {
        var contentReferences = dao.findReferences(newContent, childEntity);
        parentEntity.setContent(contentReferences);
        dao.save(parentEntity);
    }


}
