package org.tutor.materials.textbook.service.managers.implementation.textbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.model.domain.input.ContentModificationInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Chapter;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.service.events.events.TextbookContentEvent;
import org.tutor.materials.textbook.service.events.processors.PublishEvent;
import org.tutor.materials.textbook.service.shared.ContentManager;

import java.util.List;

@Service
public class TextbookContentManager  {

    private final ContentManager<Textbook, Chapter> contentManger;

    @Autowired
    public TextbookContentManager(ContentManager<Textbook, Chapter> contentManger) {
        this.contentManger = contentManger;
    }


    @PublishEvent(TextbookContentEvent.class)
    public List<ID<Chapter>> modifyContent(ID<? extends AppUser> accessor, ContentModificationInput<Textbook, Chapter> input) {
        return switch (input.action()) {
            case REMOVE -> contentManger.remove(input.parent(), input.child());
            case MOVE -> contentManger.move(input.parent(), input.child(), input.place());
            case ADD -> contentManger.add(input.parent(), input.child(), input.place());
        };
    }

}
