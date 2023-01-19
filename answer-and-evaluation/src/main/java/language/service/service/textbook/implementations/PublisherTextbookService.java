package language.service.service.textbook.implementations;

import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.textbook.Chapter;
import language.contentandrepository.model.domain.textbook.Textbook;
import language.contentandrepository.repository.textbook.TextbookRepository;
import language.service.service.eventpublisher.DomainEvent;
import language.service.service.eventpublisher.PublishEvent;
import language.service.service.textbook.inputs.TextbookContentModifyInput;
import language.service.service.textbook.inputs.TextbookCreateInput;
import language.service.service.textbook.inputs.TextbookModifyAccessInput;
import language.service.service.textbook.services.TextbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherTextbookService implements TextbookService {

    private final Clock clock;
    private final TextbookRepository textbookRepository;


    @PublishEvent(DomainEvent.TEXTBOOK_CREATED)
    public Textbook createTextbook(TextbookCreateInput input) {
        var id = textbookRepository.generateId(input.title().replaceAll("\s+", "_"));
        var newTextbook = new Textbook(
                id,
                input.title(),
                new ArrayList<>(),
                input.author().id(),
                LocalDateTime.ofInstant(clock.instant(), clock.getZone())
        );
        textbookRepository.add(newTextbook);
        return newTextbook;
    }

    @PublishEvent(DomainEvent.TEXTBOOK_ACCESS_MODIFIED)
    public boolean changeAccess(TextbookModifyAccessInput input) {
        var users = input.access().allowedStudents();
        var student = input.student().id();
        var grant = input.grant();

        if(grant)
            return users.add(student);
        else
            return users.remove(student);
    }

    /**
     * @param input
     * @return updated list of chapter ids in order
     * adds, remove or changes place of provided chapter inside of containerId's chapters list
     */
    @PublishEvent(DomainEvent.TEXTBOOK_CONTENT_MODIFIED)
    public List<DomainID<Chapter>> modifyContent(TextbookContentModifyInput input) {
        Utils.modifyContent(input.textbook().chapters(), input.chapter(), input.newPlace());

        return input.textbook().chapters().stream().toList();
    }
}
