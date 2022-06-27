package org.tutor.materials.model.domain.interfaces;

import java.util.List;

public interface ContentContainer<Q extends Identifiable> {

    List<UUID<Q>> content();

}
