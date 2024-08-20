package fr.hndgy.jworkflow.entity;

import lombok.Builder;
import lombok.Data;

@Data
public class Action {

    private String id;

    private ActionDefinition definition;

    @Builder
    public Action(String id, ActionDefinition definition) {
        this.id = id;
        this.definition = definition;
    }
}
