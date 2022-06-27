package org.tutor.materials.repository.generator;

import org.junit.jupiter.api.Test;
import org.tutor.materials.model.entity.OrderedContentContainerEntity;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueryGeneratorTest {

    @Test
    public void contentIdsQueryGenerator() {
        //given
        var entity = new OrderedContentContainerEntity() {
            @Override
            public List getContent() {
                return null;
            }

            @Override
            public void setContent(List content) {

            }

            @JoinTable(
                  name = "table_name",
                  joinColumns = @JoinColumn(name = "owner_uuid"),
                  inverseJoinColumns = @JoinColumn(name = "content_uuid")
          ) private Collection<Object> content;
        };
        //when
        var query = QueryGenerator.generateContentIdsQuery(entity.getClass());

        //then
        var expected = "SELECT cast(T.content_uuid as varchar) FROM table_name as T WHERE cast(T.owner_uuid as varchar) = ?1";
        assertEquals(expected, query);
    }

}